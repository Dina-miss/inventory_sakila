package sakila.service;

import sakila.exceptions.UnknownAddressException;
import sakila.exceptions.UnknownStaffException;
import sakila.exceptions.UnknownStoreException;
import sakila.model.Store;

import java.util.Collection;

public interface StoreService {
    Collection<Store> getAllStore();

    void recordStore(Store store) throws UnknownStaffException, UnknownAddressException;
    void deleteStore(Store store) throws UnknownStoreException, UnknownStaffException, UnknownAddressException;

}
