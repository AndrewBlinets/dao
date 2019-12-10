package by.ipps.dao.repository;

import by.ipps.dao.entity.UserPortal;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseEntityRepository<UserPortal> {
    Optional<UserPortal> findByLogin(String login);
}
