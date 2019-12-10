package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Vacancy;
import by.ipps.dao.repository.VacancyRepository;
import by.ipps.dao.service.VacancyService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class VacancyServiceImpl extends BaseEntityServiceImpl<Vacancy, VacancyRepository> implements VacancyService {
    public VacancyServiceImpl(VacancyRepository repository) {
        super(repository);
    }
}
