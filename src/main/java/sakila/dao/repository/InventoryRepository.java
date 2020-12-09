package sakila.dao.repository;

import org.springframework.data.repository.CrudRepository;
import sakila.dao.entity.FilmEntity;
import sakila.dao.entity.InventoryEntity;
import sakila.dao.entity.StoreEntity;

import java.util.Collection;

public interface InventoryRepository extends CrudRepository<InventoryEntity, Integer> {
    Collection<InventoryEntity> findByFilmAndStore(FilmEntity film, StoreEntity storeId);
}
