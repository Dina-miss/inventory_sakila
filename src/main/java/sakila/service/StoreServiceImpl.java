package sakila.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sakila.dao.StoreDao;
import sakila.exceptions.UnknownAddressException;
import sakila.exceptions.UnknownStaffException;
import sakila.exceptions.UnknownStoreException;
import sakila.model.Store;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreDao storeDao;

    @Override
    public Collection<Store> getAllStore() {
        return storeDao.readAll();
    }

    @Override
    public void recordStore(Store store) throws UnknownStaffException, UnknownAddressException {
        storeDao.createStore(store);
    }

    @Override
    public void deleteStore(Store store) throws UnknownStoreException, UnknownStaffException, UnknownAddressException {
        storeDao.deleteStore(store);
    }

    @Override
    public void updateDatabase(Store oldStore, Store newStore) throws UnknownStoreException, UnknownStaffException, UnknownAddressException {
        storeDao.updateDatabase(oldStore, newStore);
    }

}
