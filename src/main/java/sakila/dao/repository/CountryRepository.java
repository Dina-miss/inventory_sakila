package sakila.dao.repository;

import org.springframework.data.repository.CrudRepository;
import sakila.dao.entity.CountryEntity;

import java.util.Optional;

public interface CountryRepository extends CrudRepository<CountryEntity, Integer> {
    Optional<CountryEntity> findById(Integer integer);
    Optional<CountryEntity> findByName(String name);
}
