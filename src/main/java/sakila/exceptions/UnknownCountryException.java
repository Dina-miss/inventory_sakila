package sakila.exceptions;

import lombok.Data;

@Data
public class UnknownCountryException extends Exception {

    public UnknownCountryException() {}

    public UnknownCountryException(String message) {
        super(message);
    }
}
