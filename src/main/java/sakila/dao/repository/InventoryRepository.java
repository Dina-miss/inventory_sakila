package sakila.dao.repository;

import org.springframework.data.repository.CrudRepository;
import sakila.dao.entity.InventoryEntity;

import java.util.Collection;

public interface InventoryRepository extends CrudRepository<InventoryEntity, Integer> {
    Collection<InventoryEntity> findByFilmAndStore(String film, int storeId);
}
