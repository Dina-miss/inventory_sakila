package sakila.exceptions;

import lombok.Data;

@Data
public class UnknownCityException extends Exception {

    public UnknownCityException() {}

    public UnknownCityException(String message) {
        super(message);
    }
}
