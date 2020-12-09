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
                        entity.getLanguage().getName(),
                        entity.getOriginalLanguage().getName(),
                        entity.getRentalDuration(),
                        entity.getRentalRate(),
                        entity.getLength(),
                        entity.getReplacementCost(),
                        entity.getRating(),
                        entity.getSpecialFeatures()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createFilm(Film film) throws UnknownLanguageException {
        FilmEntity filmEntity = FilmEntity.builder()
                .title(film.getTitle())
                .description(film.getDescription())
                .releaseYear(film.getReleaseYear())
                .language(queryLanguage(film.getLanguage()))
                .originalLanguage(queryLanguage(film.getOriginalLanguage()))
                .rentalDuration(film.getRentalDuration())
                .rentalRate(film.getRentalRate())
                .length(film.getLength())
                .replacementCost(film.getReplacementCost())
                .rating(film.getRating())
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

    protected LanguageEntity queryLanguage(String language) throws UnknownLanguageException {
        Optional<LanguageEntity> languageEntity = languageRepository.findByName(language);
        if(!languageEntity.isPresent()) {
            throw new UnknownLanguageException("Unknown language: " + language);
        }
        return languageEntity.get();
    }

    @Override
    public void deleteFilm(Film film) throws UnknownFilmException, UnknownLanguageException {
        Optional<FilmEntity> filmEntity = filmRepository.findByTitleAndDescriptionAndReleaseYearAndLanguageAndOriginalLanguageAndRentalDurationAndRentalRateAndLengthAndReplacementCostAndRatingAndSpecialFeatures(
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                queryLanguage(film.getLanguage()),
                queryLanguage(film.getOriginalLanguage()),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getLength(),
                film.getReplacementCost(),
                film.getRating(),
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

    @Override
    public void updateDatabase(Film original, Film updated) throws UnknownFilmException, UnknownLanguageException {
        Optional<FilmEntity> filmEntity = filmRepository.findByTitleAndDescriptionAndReleaseYearAndLanguageAndOriginalLanguageAndRentalDurationAndRentalRateAndLengthAndReplacementCostAndRatingAndSpecialFeatures(
                original.getTitle(),
                original.getDescription(),
                original.getReleaseYear(),
                queryLanguage(original.getLanguage()),
                queryLanguage(original.getOriginalLanguage()),
                original.getRentalDuration(),
                original.getRentalRate(),
                original.getLength(),
                original.getReplacementCost(),
                original.getRating(),
                original.getSpecialFeatures())
                .stream()
                .findFirst();
        if(!filmEntity.isPresent()) {
            throw new UnknownFilmException("Unknown film: " + original.toString());
        }
        log.info("Original: " + filmEntity.get().toString());

        filmEntity.get().setTitle(updated.getTitle());
        filmEntity.get().setDescription(updated.getDescription());
        filmEntity.get().setReleaseYear(updated.getReleaseYear());
        filmEntity.get().setLanguage(queryLanguage(updated.getLanguage()));
        filmEntity.get().setOriginalLanguage(queryLanguage(updated.getOriginalLanguage()));
        filmEntity.get().setRentalDuration(updated.getRentalDuration());
        filmEntity.get().setRentalRate(updated.getRentalRate());
        filmEntity.get().setLength(updated.getLength());
        filmEntity.get().setReplacementCost(updated.getReplacementCost());
        filmEntity.get().setRating(updated.getRating());
        filmEntity.get().setSpecialFeatures(updated.getSpecialFeatures());
        log.info("Updated: " + filmEntity.get().toString());

        try {
            filmRepository.save(filmEntity.get());
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
