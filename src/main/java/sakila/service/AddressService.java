package sakila.service;

import sakila.exceptions.UnknownAddressException;
import sakila.exceptions.UnknownCountryException;
import sakila.model.Address;

import java.util.Collection;

public interface AddressService {
    Collection<Address> getAllAddress();
    Collection<Address> getAddressInCity(String city);

    void recordAddress(Address address) throws UnknownCountryException;
    void deleteAddress(Address address) throws UnknownAddressException;

}
