package sakila.dao;

import sakila.exceptions.*;
import sakila.model.Store;

import java.util.Collection;

public interface StoreDao {

    Collection<Store> readAll();
    void createStore(Store store) throws UnknownStaffException, UnknownAddressException, UnknownCityException, UnknownCountryException;
    void deleteStore(Store store) throws UnknownStoreException, UnknownStaffException, UnknownAddressException, UnknownCityException, UnknownCountryException;
    void updateDatabase(Store original, Store updated) throws UnknownStoreException, UnknownStaffException, UnknownAddressException, UnknownCityException, UnknownCountryException;

}
