package sakila.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Clob;
import java.time.Year;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FilmUpdatedDto {

    private String updateTitle;
    private Clob updateDescription;
    private Year updateReleaseYear;
    private String updateLanguage;
    private String updateOriginalLanguage;
    private int updateRentalDuration;
    private double updateRentalRate;
    private int updateLength;
    private double updateReplacementCost;
    private String updateRating;
    private String updateSpecialFeatures;

}
