package sakila.service;

import sakila.exceptions.*;
import sakila.model.Inventory;

import java.util.Collection;

public interface InventoryService {

    Collection<Inventory> getAllInventory();
    Collection<Inventory> getInventoryInFilm(String film);

    void recordInventory(Inventory inventory) throws UnknownStoreException, UnknownFilmException, UnknownStaffException, UnknownAddressException, UnknownCityException, UnknownCountryException, UnknownLanguageException;
    void deleteInventory(Inventory inventory) throws UnknownInventoryException, UnknownStoreException, UnknownFilmException, UnknownStaffException, UnknownAddressException, UnknownCityException, UnknownCountryException, UnknownLanguageException;

}
