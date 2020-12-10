package sakila.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sakila.controller.dto.LanguageDto;
import sakila.controller.dto.StoreDto;
import sakila.exceptions.UnknownAddressException;
import sakila.exceptions.UnknownLanguageException;
import sakila.exceptions.UnknownStaffException;
import sakila.exceptions.UnknownStoreException;
import sakila.model.Language;
import sakila.model.Store;
import sakila.service.LanguageService;
import sakila.service.StoreService;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService service;

    @GetMapping("/Language")
    public Collection<LanguageDto> listLanguage() {
        return service.getAllLanguage()
                .stream()
                .map(model -> LanguageDto.builder()
                        .language(model.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/Language")
    public void recordStore(@RequestBody LanguageDto languageDto) {
        try {
            service.recordLanguage(new Language(
                    languageDto.getLanguage()
            ));
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/Language")
    public void deleteStore(@RequestBody LanguageDto languageDto) {
        try {
            service.deleteLanguage(new Language(
                    languageDto.getLanguage()
            ));
        }
        catch (UnknownLanguageException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
