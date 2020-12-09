package sakila.dao.repository;

import org.springframework.data.repository.CrudRepository;
import sakila.dao.entity.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {
}
