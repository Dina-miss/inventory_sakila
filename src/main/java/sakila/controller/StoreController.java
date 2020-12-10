package sakila.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sakila.controller.dto.StoreDto;
import sakila.exceptions.UnknownAddressException;
import sakila.exceptions.UnknownStaffException;
import sakila.exceptions.UnknownStoreException;
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
        return service.getAllStore()
                .stream()
                .map(model -> StoreDto.builder()
                        .staffId(model.getStaffId())
                        .addressId(model.getAddressId())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/Store")
    public void recordStore(@RequestBody StoreDto storeDto) {
        try {
            service.recordStore(new Store(
                    storeDto.getStaffId(),
                    storeDto.getAddressId()
            ));
        }
        catch (UnknownStaffException | UnknownAddressException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/Store")
    public void deleteStore(@RequestBody StoreDto storeDto) {
        try {
            service.deleteStore(new Store(
                    storeDto.getStaffId(),
                    storeDto.getAddressId()
            ));
        }
        catch (UnknownAddressException | UnknownStoreException | UnknownStaffException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
