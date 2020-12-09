package sakila.dao;

import sakila.exceptions.UnknownFilmException;
import sakila.exceptions.UnknownLanguageException;
import sakila.model.Film;

import java.util.Collection;

public interface FilmDao {

    Collection<Film> readAll();
    void createFilm(Film film) throws UnknownLanguageException;
    void deleteFilm(Film film) throws UnknownFilmException, UnknownLanguageException;
    void updateDatabase(Film original, Film updated) throws UnknownFilmException, UnknownLanguageException;

}
