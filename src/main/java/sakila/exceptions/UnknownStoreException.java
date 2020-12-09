package sakila.exceptions;

import lombok.Data;
import sakila.model.Store;

@Data
public class UnknownStoreException extends Exception {

    private Store store;

    public UnknownStoreException(Store store) {
        this.store = store;
    }

    public UnknownStoreException(Store store, String messsage) {
        super(messsage);
        this.store = store;
    }

    public UnknownStoreException(String message) {
        super(message);
    }
}
