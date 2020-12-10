package sakila.dao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sakila.dao.entity.LanguageEntity;
import sakila.dao.repository.LanguageRepository;
import sakila.exceptions.UnknownLanguageException;
import sakila.model.Language;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@AllArgsConstructor
public class LanguageDaoImpl implements LanguageDao {

    private final LanguageRepository languageRepository;

    @Override
    public Collection<Language> readAll() {
        return StreamSupport.stream(languageRepository.findAll().spliterator(), false)
                .map(entity -> new Language(
                        entity.getName()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createLanguage(Language language) {
        LanguageEntity languageEntity = LanguageEntity.builder()
                .name(language.getName())
                .build();
        log.info("LanguageEntity: {}", languageEntity);
        try {
            languageRepository.save(languageEntity);
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void deleteLanguage(Language language) throws UnknownLanguageException {
        Optional<LanguageEntity> languageEntity = languageRepository.findByName(
                language.getName())
                .stream()
                .findFirst();
        if(!languageEntity.isPresent()) {
            throw new UnknownLanguageException("Unknown language: " + language.toString());
        }
        log.info("LanguageEntity: {}", languageEntity);
        try {
            languageRepository.delete(languageEntity.get());
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
