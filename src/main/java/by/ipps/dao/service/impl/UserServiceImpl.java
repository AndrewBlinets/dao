package by.ipps.dao.service.impl;

import by.ipps.dao.entity.UserPortal;
import by.ipps.dao.repository.UserRepository;
import by.ipps.dao.service.UserService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseEntityServiceImpl<UserPortal, UserRepository> implements UserService {

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public UserPortal getUserByLogin(String login) {
        return repository.findByLogin(login).orElse(null);
    }
}
