package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Exhibitions;
import by.ipps.dao.repository.ExhibitionsRepository;
import by.ipps.dao.service.ExhibitionsService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ExhibitionsServiceImpl extends BaseEntityServiceImpl<Exhibitions, ExhibitionsRepository>
        implements ExhibitionsService {
    public ExhibitionsServiceImpl(ExhibitionsRepository repository) {
        super(repository);
    }
}
