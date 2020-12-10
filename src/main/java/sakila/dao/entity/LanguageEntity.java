package sakila.dao.entity;

import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@javax.persistence.Entity
@javax.persistence.Table(name = "language", schema = "sakila")
public class LanguageEntity {

    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Column(name = "language_id")
    private int id;

    @javax.persistence.Column(name = "language")
    private String name;

    @javax.persistence.Column(name = "last_update")
    private Timestamp lastUpdate;

}
