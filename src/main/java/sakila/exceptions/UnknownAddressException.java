package sakila.exceptions;

import lombok.Data;
import sakila.model.Address;

@Data
public class UnknownAddressException extends Exception {

    private Address address;

    public UnknownAddressException(Address address) {
        this.address = address;
    }

    public UnknownAddressException(String message, Address address) {
        super(message);
        this.address = address;
    }

    public UnknownAddressException(String message) {
        super(message);
    }

}
