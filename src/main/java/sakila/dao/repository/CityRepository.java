package sakila.dao.repository;

import org.springframework.data.repository.CrudRepository;
import sakila.dao.entity.CityEntity;

import java.util.Collection;

public interface CityRepository extends CrudRepository<CityEntity, Integer> {

    Collection<CityEntity> findByName(String name);

}
