package sakila.dao.repository;

import org.springframework.data.repository.CrudRepository;
import sakila.dao.entity.FilmEntity;
import sakila.dao.entity.LanguageEntity;

import java.sql.Clob;
import java.time.Year;
import java.util.Collection;

public interface FilmRepository extends CrudRepository<FilmEntity, Integer> {
    Collection<FilmEntity> findByFilmId(int filmId);
    Collection<FilmEntity> findByTitle(String title);
    Collection<FilmEntity> findByTitleAndDescriptionAndReleaseYearAndLanguageAndOriginalLanguageAndRentalDurationAndRentalRateAndLengthAndReplacementCostAndRatingAndSpecialFeatures(
            String title, Clob description, Year releaseYear, LanguageEntity language, LanguageEntity originalLanguage, int rentalDuration,
            double rentalRate, int length, double replacementCost, String rating, String specialFeatures);
}
