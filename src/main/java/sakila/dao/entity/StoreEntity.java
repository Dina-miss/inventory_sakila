package sakila.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ServiceLoader;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "store", schema = "sakila")
public class StoreEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "store_id")
    private int storeId;

    @ManyToOne
    @JoinColumn(name = "manager_staff_id")
    private StaffEntity staffId;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

}
