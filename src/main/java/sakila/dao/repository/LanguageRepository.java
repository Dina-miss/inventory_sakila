package sakila.dao.repository;

import org.springframework.data.repository.CrudRepository;
import sakila.dao.entity.LanguageEntity;

import java.util.Optional;

public interface LanguageRepository extends CrudRepository<LanguageEntity, Integer> {
    @Override
    Optional<LanguageEntity> findById(Integer integer);

    Optional<LanguageEntity> findByName(String name);
}
