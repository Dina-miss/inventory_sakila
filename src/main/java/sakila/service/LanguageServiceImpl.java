package sakila.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sakila.dao.LanguageDao;
import sakila.exceptions.UnknownLanguageException;
import sakila.model.Language;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageDao languageDao;

    @Override
    public Collection<Language> getAllLanguage() {
        return languageDao.readAll();
    }

    @Override
    public void recordLanguage(Language language) {
        languageDao.createLanguage(language);
    }

    @Override
    public void deleteLanguage(Language language) throws UnknownLanguageException {
        languageDao.deleteLanguage(language);
    }
}
