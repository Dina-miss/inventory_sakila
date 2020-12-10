package sakila.dao.repository;

import org.springframework.data.repository.CrudRepository;
import sakila.dao.entity.AddressEntity;
import sakila.dao.entity.StaffEntity;
import sakila.dao.entity.StoreEntity;

import java.util.Collection;

public interface StoreRepository extends CrudRepository<StoreEntity, Integer> {
    Collection<StoreEntity> findByStaffIdAndAddress(StaffEntity staffId, AddressEntity address);
}
