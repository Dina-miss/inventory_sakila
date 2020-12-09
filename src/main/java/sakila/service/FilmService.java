package sakila.service;

import sakila.exceptions.UnknownFilmException;
import sakila.exceptions.UnknownLanguageException;
import sakila.model.Film;

import java.util.Collection;

public interface FilmService {

    Collection<Film> getAllFilm();

    void recordFilm(Film film) throws UnknownLanguageException;
    void deleteFilm(Film film) throws UnknownFilmException, UnknownLanguageException;

    void updateDatabase(Film original, Film updated) throws UnknownFilmException, UnknownLanguageException;

}
