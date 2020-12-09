package sakila.exceptions;

import lombok.Data;
import sakila.model.Film;

@Data
public class UnknownFilmException extends Exception {
    private Film film;

    public UnknownFilmException(Film film) {
        this.film = film;
    }

    public UnknownFilmException(String message, Film film) {
        super(message);
        this.film = film;
    }

    public UnknownFilmException(String message) {
        super(message);
    }
}
