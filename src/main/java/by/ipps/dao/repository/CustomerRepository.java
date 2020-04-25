package by.ipps.dao.repository;

import by.ipps.dao.entity.Customer;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends BaseEntityRepository<Customer> {
  Optional<Customer> findCustomerByLoginAndStatusR(String login, String statusR);
}
