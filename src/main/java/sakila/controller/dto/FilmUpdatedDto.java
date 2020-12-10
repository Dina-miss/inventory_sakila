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
public class FilmUpdatedDto extends FilmDto {

    private String updateTitle;
    private String updateDescription;
    private int updateReleaseYear;
    private String updateLanguage;
    private int updateRentalDuration;
    private double updateRentalRate;
    private int updateLength;
    private double updateReplacementCost;
    private String updateSpecialFeatures;

}
