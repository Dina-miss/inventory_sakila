package sakila.dao;

import sakila.exceptions.UnknownAddressException;
import sakila.exceptions.UnknownCountryException;
import sakila.model.Address;

import java.util.Collection;

public interface AddressDao {
    Collection<Address> readAll();
    void createAddress(Address address) throws UnknownCountryException;
    void deleteAddress(Address address) throws UnknownAddressException;
}
