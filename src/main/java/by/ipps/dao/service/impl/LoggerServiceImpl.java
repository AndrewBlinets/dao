package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Logger;
import by.ipps.dao.repository.LoggerRepository;
import by.ipps.dao.service.LoggerService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class LoggerServiceImpl extends BaseEntityServiceImpl<Logger, LoggerRepository> implements LoggerService {

    public LoggerServiceImpl(LoggerRepository repository) {
        super(repository);
    }
}
