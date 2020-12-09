package sakila.exceptions;

import lombok.Data;
import sakila.model.Inventory;

@Data
public class UnknownInventoryException extends Exception {

    private Inventory inventory;

    public UnknownInventoryException(Inventory inventory) {
        this.inventory = inventory;
    }

    public UnknownInventoryException(String message, Inventory inventory) {
        super(message);
        this.inventory = inventory;
    }

    public UnknownInventoryException(String message) {
        super(message);
    }
}
