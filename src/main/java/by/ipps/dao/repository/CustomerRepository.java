package by.ipps.dao.repository;

import by.ipps.dao.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends BaseEntityRepository<Customer> {
  Optional<Customer> findCustomerByLoginAndStatusR(String login, String statusR);
}
