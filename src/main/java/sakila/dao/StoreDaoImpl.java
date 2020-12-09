package sakila.dao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sakila.dao.entity.AddressEntity;
import sakila.dao.entity.StaffEntity;
import sakila.dao.entity.StoreEntity;
import sakila.dao.repository.AddressRepository;
import sakila.dao.repository.StaffRepository;
import sakila.dao.repository.StoreRepository;
import sakila.exceptions.UnknownAddressException;
import sakila.exceptions.UnknownFilmException;
import sakila.exceptions.UnknownStaffException;
import sakila.exceptions.UnknownStoreException;
import sakila.model.Store;

import java.lang.management.OperatingSystemMXBean;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@AllArgsConstructor
public class StoreDaoImpl implements StoreDao {

    private final StoreRepository storeRepository;
    private final StaffRepository staffRepository;
    private final AddressRepository addressRepository;

    @Override
    public Collection<Store> readAll() {
        return StreamSupport.stream(storeRepository.findAll().spliterator(), false)
                .map(entity -> new Store(
                        entity.getStaffId().getId(),
                        entity.getAddress().getId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createStore(Store store) throws UnknownStaffException, UnknownAddressException {
        StoreEntity storeEntity = StoreEntity.builder()
                .staffId(queryStaff(store.getStaffId()))
                .address(queryAddress(store.getAddressId()))
                .build();
        log.info("StoreEntity: {}", storeEntity);
        try {
            storeRepository.save(storeEntity);
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    protected StaffEntity queryStaff(int staffId) throws UnknownStaffException {
        Optional<StaffEntity> staffEntity = staffRepository.findById(staffId);
        if (!staffEntity.isPresent()) {
            throw new UnknownStaffException("Unknown staff: " + staffId);
        }
        return staffEntity.get();
    }

    protected AddressEntity queryAddress(int addressId) throws UnknownAddressException {
        Optional<AddressEntity> addressEntity = addressRepository.findById(addressId);
        if(!addressEntity.isPresent()) {
            throw new UnknownAddressException("Unknown address: " + addressId);
        }
        return addressEntity.get();
    }

    @Override
    public void deleteStore(Store store) throws UnknownStoreException, UnknownStaffException, UnknownAddressException {
        Optional<StoreEntity> storeEntity = storeRepository.findByStaffIdAndAddress(
                queryStaff(store.getStaffId()),
                queryAddress(store.getAddressId()))
                .stream()
                .findFirst();
        if (!storeEntity.isPresent()) {
            throw new UnknownStoreException("Unknown store: " + store.toString());
        }
        log.info("StoreEntity: {}", storeEntity);
        try {
            storeRepository.delete(storeEntity.get());
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateDatabase(Store original, Store updated) throws UnknownStoreException, UnknownStaffException, UnknownAddressException {
        Optional<StoreEntity> storeEntity = storeRepository.findByStaffIdAndAddress(
                queryStaff(original.getStaffId()),
                queryAddress(original.getAddressId()))
                .stream()
                .findFirst();
        if (!storeEntity.isPresent()) {
            throw new UnknownStoreException("Unknown store: " + original.toString());
        }
        log.info("Original: " + storeEntity.get().toString());

        storeEntity.get().setStaffId(queryStaff(updated.getStaffId()));
        storeEntity.get().setAddress(queryAddress(updated.getAddressId()));
        log.info("Updated: " + storeEntity.get().toString());

        try {
            storeRepository.save(storeEntity.get());
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
