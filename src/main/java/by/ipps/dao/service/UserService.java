package by.ipps.dao.service;

import by.ipps.dao.entity.UserPortal;
import by.ipps.dao.service.base.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends BaseEntityService<UserPortal> {
    UserPortal getUserByLogin(String login);
}
