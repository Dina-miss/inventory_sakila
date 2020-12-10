package sakila.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sakila.controller.dto.InventoryDto;
import sakila.exceptions.*;
import sakila.model.Inventory;
import sakila.service.InventoryService;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService service;

    @GetMapping("/Inventory")
    public Collection<InventoryDto> listInventory() {
        return service.getAllInventory()
                .stream()
                .map(model -> InventoryDto.builder()
                        .film(model.getFilm())
                        .storeId(model.getStoreId())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/Inventory")
    public void recordInventory(@RequestBody InventoryDto inventoryDto) {
        try {
            service.recordInventory(new Inventory(
                    inventoryDto.getFilm(),
                    inventoryDto.getStoreId()
            ));
        }
        catch (UnknownFilmException | UnknownStoreException | UnknownStaffException |
                UnknownAddressException | UnknownCityException | UnknownCountryException |
                UnknownLanguageException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/Inventory")
    public void deleteInventory(@RequestBody InventoryDto inventoryDto) {
        try {
            service.deleteInventory(new Inventory(
                    inventoryDto.getFilm(),
                    inventoryDto.getStoreId()
            ));
        }
        catch (UnknownInventoryException | UnknownStoreException | UnknownFilmException |
                UnknownStaffException | UnknownAddressException | UnknownCityException |
                UnknownCountryException | UnknownLanguageException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
