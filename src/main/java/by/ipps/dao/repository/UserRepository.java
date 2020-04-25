package by.ipps.dao.repository;

import by.ipps.dao.entity.UserPortal;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseEntityRepository<UserPortal> {
  Optional<UserPortal> findByLogin(String login);
}
