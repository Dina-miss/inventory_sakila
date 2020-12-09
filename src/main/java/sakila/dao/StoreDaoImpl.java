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
import sakila.exceptions.*;
import sakila.model.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@AllArgsConstructor
public class StoreDaoImpl implements StoreDao {

    private final StoreRepository storeRepository;
    private final StaffRepository staffRepository;
    private final AddressRepository addressRepository;

    @Override
    public Collection<Store> readAll() {
        return StreamSupport.stream(storeRepository.findAll().spliterator(), false)
                .map(entity -> new Store(
                        entity.getStaff().getUsername(),
                        entity.getAddress().getId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createStore(Store store) throws UnknownStaffException, UnknownAddressException, UnknownCityException, UnknownCountryException {
        StoreEntity storeEntity;

        storeEntity = StoreEntity.builder()
                .staff(queryStaff(store.getManagerStaffId()))
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

    protected StaffEntity queryStaff(String staff) throws UnknownStaffException {
        Optional<StaffEntity> staffEntity = staffRepository.findByUsername(staff).stream()
                .filter(entity -> entity.getUsername() == staff)
                .findFirst();
        if(!staffEntity.isPresent()) {
            throw new UnknownStaffException("Unknown Staff: " + staff);
        }
        return staffEntity.get();
    }

    protected AddressEntity queryAddress(int addressId) throws UnknownAddressException {
        Optional<AddressEntity> addressEntity = addressRepository.findById(addressId).stream()
                .filter(entity -> entity.getId() == addressId)
                .findFirst();
        if(!addressEntity.isPresent()) {
            throw new UnknownAddressException("Unknown Address ID: " + addressId);
        }
        return addressEntity.get();
    }

    @Override
    public void deleteStore(Store store) throws UnknownStoreException, UnknownStaffException, UnknownAddressException, UnknownCityException, UnknownCountryException {
        Optional<StoreEntity> storeEntity = storeRepository.findByStaffAndAddress(
                queryStaff(store.getManagerStaffId()),
                queryAddress(store.getAddressId()))
                .stream()
                .findFirst();
        if(!storeEntity.isPresent()) {
            throw new UnknownStoreException("Unknown Store: " + store.toString());
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
    public void updateDatabase(Store original, Store updated) throws UnknownStoreException, UnknownStaffException, UnknownAddressException, UnknownCityException, UnknownCountryException {
        Optional<StoreEntity> storeEntity = storeRepository.findByStaffAndAddress(
                queryStaff(original.getManagerStaffId()),
                queryAddress(original.getAddressId()))
                .stream()
                .findFirst();
        if(!storeEntity.isPresent()) {
            throw new UnknownStoreException("Unknown Store: " + original.toString());
        }

        log.info("Original: " + storeEntity.get().toString());
        storeEntity.get().setStaff(queryStaff(updated.getManagerStaffId()));
        storeEntity.get().setAddress(queryAddress(updated.getAddressId()));

        try {
            storeRepository.save(storeEntity.get());
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
