package sakila.dao;

import sakila.exceptions.UnknownAddressException;
import sakila.exceptions.UnknownStaffException;
import sakila.exceptions.UnknownStoreException;
import sakila.model.Store;

import java.util.Collection;

public interface StoreDao {
    Collection<Store> readAll();
    void createStore(Store store) throws UnknownStaffException, UnknownAddressException;
    void deleteStore(Store store) throws UnknownStoreException, UnknownStaffException, UnknownAddressException;
    void updateDatabase(Store original, Store updated) throws UnknownStoreException, UnknownStaffException, UnknownAddressException;
}
