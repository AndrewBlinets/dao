package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Language;
import by.ipps.dao.repository.LanguageRepository;
import by.ipps.dao.service.LanguageService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl extends BaseEntityServiceImpl<Language, LanguageRepository> implements LanguageService {

    public LanguageServiceImpl(LanguageRepository repository) {
        super(repository);
    }

}
