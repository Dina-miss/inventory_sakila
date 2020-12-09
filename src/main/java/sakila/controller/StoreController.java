package sakila.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sakila.controller.dto.StoreDto;
import sakila.controller.dto.StoreUpdatedDto;
import sakila.exceptions.*;
import sakila.model.Store;
import sakila.service.StoreService;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService service;

    @GetMapping("/Store")
    public Collection<StoreDto> listStore() {
        return service.readAllStore()
                .stream()
                .map(model -> StoreDto.builder()
                        .username(model.getManagerStaffId())
                        .addressId(model.getAddressId())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/Store")
    public void recordStore(@RequestBody StoreDto storeDto) {
        try {
            service.recordStore(new Store(
                    storeDto.getUsername(),
                    storeDto.getAddressId()
            ));
        }
        catch (UnknownStaffException | UnknownAddressException | UnknownCityException | UnknownCountryException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/Store")
    public void deleteStore(@RequestBody StoreDto storeDto) {
        try {
            service.deleteStore(new Store(
                    storeDto.getUsername(),
                    storeDto.getAddressId()
            ));
        }
        catch (UnknownStoreException | UnknownStaffException | UnknownAddressException | UnknownCityException | UnknownCountryException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/Store")
    public void updateStore(@RequestBody StoreUpdatedDto storeUpdatedDto) {
        try {
            service.updateDatabase(new Store(
                            storeUpdatedDto.getUsername(),
                            storeUpdatedDto.getAddressId()
                    ),
                    new Store(
                            storeUpdatedDto.getUpdateUsername(),
                            storeUpdatedDto.getUpdateAddressId()
                    ));
        }
        catch (UnknownStoreException | UnknownStaffException | UnknownAddressException | UnknownCityException | UnknownCountryException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
