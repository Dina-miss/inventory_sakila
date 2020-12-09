package sakila.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Clob;
import java.sql.Timestamp;
import java.time.Year;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "film", schema = "sakila")
public class FilmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private int filmId;

    @Column
    private String title;

    @Column
    @Lob
    private Clob description;

    @Column(name = "release_year")
    private Year releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private LanguageEntity language;

    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private LanguageEntity originalLanguage;

    @Column(name = "rental_duration")
    private int rentalDuration;

    @Column(name = "rental_rate")
    private double rentalRate;

    @Column
    private int length;

    @Column(name = "replacement_cost")
    private double replacementCost;

    @Column
    private String rating;

    @Column(name = "special_features")
    private String specialFeatures;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

}
