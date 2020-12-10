package sakila.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sakila.controller.dto.FilmDto;
import sakila.controller.dto.FilmUpdatedDto;
import sakila.exceptions.*;
import sakila.model.Film;
import sakila.service.FilmService;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FilmController {

    private final FilmService service;

    @GetMapping("/Film")
    public Collection<FilmDto> listFilm() {
        return service.getAllFilm()
                .stream()
                .map(model -> FilmDto.builder()
                        .title(model.getTitle())
                        .description(model.getDescription())
                        .releaseYear(model.getReleaseYear())
                        .language(model.getLanguage())
                        .rentalDuration(model.getRentalDuration())
                        .rentalRate(model.getRentalRate())
                        .length(model.getLength())
                        .replacementCost(model.getReplacementCost())
                        .specialFeatures(model.getSpecialFeatures())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/Film")
    public void recordFilm(@RequestBody FilmDto filmDto) {
        try {
            service.recordFilm(new Film(
                    filmDto.getTitle(),
                    filmDto.getDescription(),
                    filmDto.getReleaseYear(),
                    filmDto.getLanguage(),
                    filmDto.getRentalDuration(),
                    filmDto.getRentalRate(),
                    filmDto.getLength(),
                    filmDto.getReplacementCost(),
                    filmDto.getSpecialFeatures()
            ));
        }
        catch (UnknownLanguageException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/Film")
    public void deleteFilm(@RequestBody FilmDto filmDto) {
        try {
            service.deleteFilm(new Film(
                    filmDto.getTitle(),
                    filmDto.getDescription(),
                    filmDto.getReleaseYear(),
                    filmDto.getLanguage(),
                    filmDto.getRentalDuration(),
                    filmDto.getRentalRate(),
                    filmDto.getLength(),
                    filmDto.getReplacementCost(),
                    filmDto.getSpecialFeatures()
            ));
        }
        catch (UnknownFilmException | UnknownLanguageException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/Film")
    public void updateFilm(@RequestBody FilmUpdatedDto filmUpdatedDto) {
        try {
            service.updateDatabase(new Film(
                    filmUpdatedDto.getTitle(),
                    filmUpdatedDto.getDescription(),
                    filmUpdatedDto.getReleaseYear(),
                    filmUpdatedDto.getLanguage(),
                    filmUpdatedDto.getRentalDuration(),
                    filmUpdatedDto.getRentalRate(),
                    filmUpdatedDto.getLength(),
                    filmUpdatedDto.getReplacementCost(),
                    filmUpdatedDto.getSpecialFeatures()
                    ),
                    new  Film(
                            filmUpdatedDto.getUpdateTitle(),
                            filmUpdatedDto.getUpdateDescription(),
                            filmUpdatedDto.getUpdateReleaseYear(),
                            filmUpdatedDto.getUpdateLanguage(),
                            filmUpdatedDto.getUpdateRentalDuration(),
                            filmUpdatedDto.getUpdateRentalRate(),
                            filmUpdatedDto.getUpdateLength(),
                            filmUpdatedDto.getUpdateReplacementCost(),
                            filmUpdatedDto.getUpdateSpecialFeatures()
                    ));
        }
        catch (UnknownFilmException | UnknownLanguageException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
