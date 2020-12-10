package sakila.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Clob;
import java.time.Year;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto {

    private String title;
    private String description;
    private int releaseYear;
    private String language;
    private int rentalDuration;
    private double rentalRate;
    private int length;
    private double replacementCost;
    private String specialFeatures;

}
