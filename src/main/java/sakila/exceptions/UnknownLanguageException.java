package sakila.exceptions;

import lombok.Data;

@Data
public class UnknownLanguageException extends Exception {

    public UnknownLanguageException() {}

    public UnknownLanguageException(String message) {
        super(message);
    }
}
