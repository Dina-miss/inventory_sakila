package sakila.service;

import sakila.exceptions.UnknownLanguageException;
import sakila.model.Language;

import java.util.Collection;

public interface LanguageService {
    Collection<Language> getAllLanguage();

    void recordLanguage(Language language);
    void deleteLanguage(Language language) throws UnknownLanguageException;

}
