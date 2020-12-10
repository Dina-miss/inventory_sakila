package sakila.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sakila.controller.dto.AddressDto;
import sakila.exceptions.UnknownAddressException;
import sakila.exceptions.UnknownCountryException;
import sakila.model.Address;
import sakila.service.AddressService;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AddressController {
    private final AddressService service;

    @GetMapping("/Address")
    public Collection<AddressDto> listAddress() {
        return service.getAllAddress()
                .stream()
                .map(model -> AddressDto.builder()
                        .address(model.getAddress())
                        .address2(model.getAddress2())
                        .district(model.getDistrict())
                        .city(model.getCity())
                        .country(model.getCountry())
                        .postalCode(model.getPostalCode())
                        .phone(model.getPhone())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/Address")
    public void recordAddress(@RequestBody AddressDto addressDto) {
        try {
            service.recordAddress(new Address(
                    addressDto.getAddress(),
                    addressDto.getAddress2(),
                    addressDto.getDistrict(),
                    addressDto.getCity(),
                    addressDto.getCountry(),
                    addressDto.getPostalCode(),
                    addressDto.getPhone()
            ));
        }
        catch (UnknownCountryException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/Address")
    public void deleteAddress(@RequestBody AddressDto addressDto) {
        try {
            service.deleteAddress(new Address(
                    addressDto.getAddress(),
                    addressDto.getAddress2(),
                    addressDto.getDistrict(),
                    addressDto.getCity(),
                    addressDto.getCountry(),
                    addressDto.getPostalCode(),
                    addressDto.getPhone()
            ));
        }
        catch (UnknownAddressException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
