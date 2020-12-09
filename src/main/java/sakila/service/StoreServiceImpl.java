package sakila.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sakila.dao.StoreDao;
import sakila.exceptions.*;
import sakila.model.Store;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreDao storeDao;

    @Override
    public Collection<Store> readAllStore() {
        return storeDao.readAll();
    }

    @Override
    public void recordStore(Store store) throws UnknownStaffException, UnknownAddressException, UnknownCityException, UnknownCountryException {
        storeDao.createStore(store);
    }

    @Override
    public void deleteStore(Store store) throws UnknownStoreException, UnknownStaffException, UnknownAddressException, UnknownCityException, UnknownCountryException {
        storeDao.deleteStore(store);
    }

    @Override
    public void updateDatabase(Store oldStore, Store newStore) throws UnknownStoreException, UnknownStaffException, UnknownAddressException, UnknownCityException, UnknownCountryException {
        storeDao.updateDatabase(oldStore, newStore);
    }
}
