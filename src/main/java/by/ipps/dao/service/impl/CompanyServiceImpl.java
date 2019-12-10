package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Company;
import by.ipps.dao.repository.CompanyRepository;
import by.ipps.dao.service.CompanyService;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    public CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    private CompanyRepository repository;

    @Override
    public Company getActualInfo() {
        return repository.findByStatusR("A").orElse(null);
    }

    @Override
    public Company setActualInfo(Company company) {
        return repository.save(company);
    }
}
