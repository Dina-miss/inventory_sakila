package sakila.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sakila.dao.repository.*;
import sakila.dao.entity.*;
import sakila.exceptions.*;
import sakila.model.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryDaoImpl implements InventoryDao {

    private final InventoryRepository inventoryRepository;
    private final FilmRepository filmRepository;
    private final StoreRepository storeRepository;

    @Override
    public Collection<Inventory> readAll() {
        return StreamSupport.stream(inventoryRepository.findAll().spliterator(), false)
                .map(entity -> new Inventory(
                        entity.getFilm().getTitle(),
                        entity.getStore().getStoreId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createInventory(Inventory inventory) throws UnknownFilmException, UnknownStoreException{
        InventoryEntity inventoryEntity;
        inventoryEntity = InventoryEntity.builder()
                .film(queryFilm(inventory.getFilm()))
                .store(queryStore(inventory.getStoreId()))
                .build();
        log.info("InventoryEntity: {}", inventoryEntity);
        try {
            inventoryRepository.save(inventoryEntity);
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    protected FilmEntity queryFilm(String film) throws UnknownFilmException {
        Optional<FilmEntity> filmEntity = filmRepository.findByTitle(film).stream()
                .filter(entity -> entity.getTitle().equals(film))
                .findFirst();
        if(!filmEntity.isPresent()) {
            throw new UnknownFilmException("Unknown Film ID: " + film);
        }
        return filmEntity.get();
    }

    protected StoreEntity queryStore(int storeId) throws UnknownStoreException {
        Optional<StoreEntity> storeEntity = storeRepository.findById(storeId).stream()
                .filter(entity -> entity.getStoreId() == (storeId))
                .findFirst();
        if (!storeEntity.isPresent()) {
            throw new UnknownStoreException("Unknown Store ID: " + storeId);
        }
        return storeEntity.get();
    }

    @Override
    public void deleteInventory(Inventory inventory) throws UnknownInventoryException, UnknownStoreException, UnknownFilmException {
        Optional<InventoryEntity> inventoryEntity = inventoryRepository.findByFilmAndStore(
                queryFilm(inventory.getFilm()),
                queryStore(inventory.getStoreId()))
                .stream()
                .findFirst();
        if(!inventoryEntity.isPresent()) {
            throw new UnknownInventoryException("Unknown Inventory: " + inventory.toString());
        }
        log.info("InventoryEntity: {}", inventoryEntity);
        try {
            inventoryRepository.delete(inventoryEntity.get());
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
