package sakila.dao.repository;

import org.springframework.data.repository.CrudRepository;
import sakila.dao.entity.StaffEntity;

import java.util.Collection;
import java.util.Optional;

public interface StaffRepository extends CrudRepository<StaffEntity, Integer> {
    Optional<StaffEntity> findById(Integer integer);
    Optional<StaffEntity> findByUsername(String username);
}
