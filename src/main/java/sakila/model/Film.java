package sakila.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Film {

    private String title;
    private String description;
    private int releaseYear;
    private int rentalDuration;
    private double rentalRate;
    private int length;
    private double replacementCost;
    private String specialFeatures;

}
