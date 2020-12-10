package sakila.dao;

import sakila.exceptions.UnknownAddressException;
import sakila.exceptions.UnknownLanguageException;
import sakila.exceptions.UnknownStaffException;
import sakila.exceptions.UnknownStoreException;
import sakila.model.Language;

import java.util.Collection;

public interface LanguageDao {
    Collection<Language> readAll();
    void createLanguage(Language language);
    void deleteLanguage(Language language) throws UnknownLanguageException;
}
