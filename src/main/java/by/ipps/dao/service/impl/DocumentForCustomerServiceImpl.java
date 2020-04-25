package by.ipps.dao.service.impl;

import by.ipps.dao.entity.DocumentForCustomer;
import by.ipps.dao.repository.DocumentForCustomerRepository;
import by.ipps.dao.service.DocumentForCustomerService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DocumentForCustomerServiceImpl
    extends BaseEntityServiceImpl<DocumentForCustomer, DocumentForCustomerRepository>
    implements DocumentForCustomerService {

  public DocumentForCustomerServiceImpl(DocumentForCustomerRepository repository) {
    super(repository);
  }
}
