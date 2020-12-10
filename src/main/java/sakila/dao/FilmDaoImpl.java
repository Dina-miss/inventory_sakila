package sakila.dao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sakila.dao.entity.FilmEntity;
import sakila.dao.entity.LanguageEntity;
import sakila.dao.repository.FilmRepository;
import sakila.dao.repository.LanguageRepository;
import sakila.exceptions.UnknownFilmException;
import sakila.exceptions.UnknownLanguageException;
import sakila.model.Film;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@AllArgsConstructor
public class FilmDaoImpl implements FilmDao {

    private final FilmRepository filmRepository;
    private final LanguageRepository languageRepository;

    @Override
    public Collection<Film> readAll() {
        return StreamSupport.stream(filmRepository.findAll().spliterator(), false)
                .map(entity -> new Film(
                        entity.getTitle(),
                        entity.getDescription(),
                        entity.getReleaseYear(),
                        entity.getRentalDuration(),
                        entity.getRentalRate(),
                        entity.getLength(),
                        entity.getReplacementCost(),
                        entity.getSpecialFeatures()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createFilm(Film film) {
        FilmEntity filmEntity = FilmEntity.builder()
                .title(film.getTitle())
                .description(film.getDescription())
                .releaseYear(film.getReleaseYear())
                .rentalDuration(film.getRentalDuration())
                .rentalRate(film.getRentalRate())
                .length(film.getLength())
                .replacementCost(film.getReplacementCost())
                .specialFeatures(film.getSpecialFeatures())
                .build();
        log.info("FilmEntity: {}", filmEntity);
        try {
            filmRepository.save(filmEntity);
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    @Override
    public void deleteFilm(Film film) throws UnknownFilmException {
        Optional<FilmEntity> filmEntity = filmRepository.findByTitleAndDescriptionAndReleaseYearAndRentalDurationAndRentalRateAndLengthAndReplacementCostAndSpecialFeatures(
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getLength(),
                film.getReplacementCost(),
                film.getSpecialFeatures())
                .stream()
                .findFirst();
        if (!filmEntity.isPresent()) {
            throw new UnknownFilmException("Unknown film: " + film.toString());
        }
        log.info("FilmEntity: {}", filmEntity);
        try {
            filmRepository.delete(filmEntity.get());
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
