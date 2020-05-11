package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Customer;
import by.ipps.dao.entity.DocumentForCustomer;
import by.ipps.dao.repository.DocumentForCustomerRepository;
import by.ipps.dao.service.DocumentForCustomerService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DocumentForCustomerServiceImpl
    extends BaseEntityServiceImpl<DocumentForCustomer, DocumentForCustomerRepository>
    implements DocumentForCustomerService {

  public DocumentForCustomerServiceImpl(DocumentForCustomerRepository repository) {
    super(repository);
  }

  @Override
  public Page<DocumentForCustomer> getDocumentForCustomerByIdCustomer(
      Pageable pageable, Customer customer) {
    return this.repository.findDocumentByCustomer(pageable, customer);
  }
}
