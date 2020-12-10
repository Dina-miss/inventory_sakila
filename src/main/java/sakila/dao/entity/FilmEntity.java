package sakila.dao.entity;

import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@javax.persistence.Entity
@javax.persistence.Table(name = "film", schema = "sakila")
public class FilmEntity {

    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Column(name = "film_id")
    private int id;

    @javax.persistence.Column(name = "title")
    private String title;

    @javax.persistence.Column(name = "description")
    private String description;

    @javax.persistence.Column(name = "release_year")
    private int releaseYear;

    @javax.persistence.ManyToOne
    @javax.persistence.JoinColumn(name = "language_id")
    private LanguageEntity language;

    @javax.persistence.ManyToOne
    @javax.persistence.JoinColumn(name = "original_language_id")
    private LanguageEntity originalLanguage;

    @javax.persistence.Column(name = "rental_duration")
    private int rentalDuration;

    @javax.persistence.Column(name = "rental_rate")
    private double rentalRate;

    @javax.persistence.Column(name = "length")
    private int length;

    @javax.persistence.Column(name = "replacement_cost")
    private double replacementCost;

    @javax.persistence.Column(name = "rating")
    private String rating;

    @javax.persistence.Column(name = "special_features")
    private String specialFeatures;

    @javax.persistence.Column(name = "last_update")
    private Timestamp lastUpdate;

}
