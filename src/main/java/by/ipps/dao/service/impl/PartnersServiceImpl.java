package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Partners;
import by.ipps.dao.repository.PartnersRepository;
import by.ipps.dao.service.PartnersService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PartnersServiceImpl extends BaseEntityServiceImpl<Partners, PartnersRepository> implements PartnersService {
    public PartnersServiceImpl(PartnersRepository repository) {
        super(repository);
    }
}
