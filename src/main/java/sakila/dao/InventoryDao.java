package sakila.dao;

import sakila.exceptions.UnknownFilmException;
import sakila.exceptions.UnknownInventoryException;
import sakila.exceptions.UnknownStoreException;
import sakila.model.Inventory;

import java.util.Collection;

public interface InventoryDao {
    Collection<Inventory> readAll();
    void createInventory(Inventory inventory) throws UnknownFilmException, UnknownStoreException;
    void deleteInventory(Inventory inventory) throws UnknownInventoryException, UnknownStoreException, UnknownFilmException;
}
