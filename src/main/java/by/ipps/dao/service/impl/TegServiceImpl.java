package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Teg;
import by.ipps.dao.repository.TegRepository;
import by.ipps.dao.service.TegService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TegServiceImpl extends BaseEntityServiceImpl<Teg, TegRepository> implements TegService {

    public TegServiceImpl(TegRepository repository) {
        super(repository);
    }
}
