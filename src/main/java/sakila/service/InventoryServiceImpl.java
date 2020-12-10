package sakila.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sakila.dao.InventoryDao;
import sakila.exceptions.*;
import sakila.model.Inventory;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryDao inventoryDao;

    @Override
    public Collection<Inventory> getAllInventory() {
        return inventoryDao.readAll();
    }

    @Override
    public Collection<Inventory> getInventoryInFilm(String film) {
        return inventoryDao.readAll().stream()
                .filter(inventory -> film.equals(inventory.getFilm()))
                .collect(Collectors.toList());
    }

    @Override
    public void recordInventory(Inventory inventory) throws UnknownStoreException, UnknownFilmException, UnknownStaffException, UnknownAddressException, UnknownCityException, UnknownCountryException, UnknownLanguageException {
        inventoryDao.createInventory(inventory);
    }

    @Override
    public void deleteInventory(Inventory inventory) throws UnknownInventoryException, UnknownStoreException, UnknownFilmException, UnknownStaffException, UnknownAddressException, UnknownCityException, UnknownCountryException, UnknownLanguageException {
        inventoryDao.deleteInventory(inventory);
    }


}
