package by.ipps.dao.service;

import by.ipps.dao.entity.Customer;
import by.ipps.dao.entity.DocumentForCustomer;
import by.ipps.dao.service.base.BaseEntityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface DocumentForCustomerService extends BaseEntityService<DocumentForCustomer> {
  Page<DocumentForCustomer> getDocumentForCustomerByIdCustomer(
      Pageable pageable, Customer customer);
}
