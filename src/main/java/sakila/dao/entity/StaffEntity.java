package sakila.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "staff", schema = "sakila")
public class StaffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity addressId;

    @Column
    @Lob
    private Blob picture;

    @Column
    private String email;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreEntity storeId;

    @Column
    private int active;

    @Column
    private  String username;

    @Column
    private String password;

    @Column(name = "last_update")
    private Timestamp lastUpdate;
}