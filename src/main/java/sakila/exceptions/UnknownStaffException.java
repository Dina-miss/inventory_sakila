package sakila.exceptions;

public class UnknownStaffException extends Exception {

    public UnknownStaffException() { }

    public UnknownStaffException(String message) {
        super(message);
    }
}
