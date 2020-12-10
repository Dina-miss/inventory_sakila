package sakila.dao.repository;

import org.springframework.data.repository.CrudRepository;
import sakila.dao.entity.LanguageEntity;

import java.util.Collection;

public interface LanguageRepository extends CrudRepository<LanguageEntity, Integer> {
    Collection<LanguageEntity> findByName(String name);
}
