package sakila.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sakila.dao.FilmDao;
import sakila.exceptions.UnknownFilmException;
import sakila.exceptions.UnknownLanguageException;
import sakila.model.Film;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmDao filmDao;

    @Override
    public Collection<Film> getAllFilm() {
        return filmDao.readAll();
    }

    @Override
    public void recordFilm(Film film) throws UnknownLanguageException {
        filmDao.createFilm(film);
    }

    @Override
    public void deleteFilm(Film film) throws UnknownFilmException, UnknownLanguageException {
        filmDao.deleteFilm(film);
    }

    @Override
    public void updateDatabase(Film oldFilm, Film newFilm) throws UnknownFilmException, UnknownLanguageException {
        filmDao.updateDatabase(oldFilm, newFilm);
    }
}
