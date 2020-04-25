package by.ipps.dao.service;

import by.ipps.dao.entity.Customer;
import by.ipps.dao.service.base.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService extends BaseEntityService<Customer> {
  Customer getCustomerByLogin(String login);
}
