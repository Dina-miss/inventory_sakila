package sakila.dao;

import sakila.exceptions.UnknownLanguageException;
import sakila.model.Language;

import java.util.Collection;

public interface LanguageDao {
    Collection<Language> readAll();
    void createLanguage(Language language);
    void deleteLanguage(Language language) throws UnknownLanguageException;
}
