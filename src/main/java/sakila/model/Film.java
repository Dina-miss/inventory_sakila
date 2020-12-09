package sakila.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.sql.Clob;
import java.time.Year;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Film {

    private String title;
    private Clob description;
    private Year releaseYear;
    private String language;
    private String originalLanguage;
    private int rentalDuration;
    private double rentalRate;
    private int length;
    private double replacementCost;
    private String rating;
    private String specialFeatures;

}
