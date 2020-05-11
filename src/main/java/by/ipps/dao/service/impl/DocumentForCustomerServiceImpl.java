package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Customer;
import by.ipps.dao.entity.DocumentForCustomer;
import by.ipps.dao.entity.Project;
import by.ipps.dao.entity.Sheet;
import by.ipps.dao.repository.DocumentForCustomerRepository;
import by.ipps.dao.service.DocumentForCustomerService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import java.util.List;
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

  @Override
  public Page<DocumentForCustomer> getDocumentForCustomerByIdProject(Pageable pageable,
      Customer customer, Project project) {
    return this.repository.findDocumentByProject(pageable, customer, project);
  }

  @Override
  public List<DocumentForCustomer> getDocumentForCustomerByIdProject(Customer customer,
      Project project) {
    return this.repository.findDocumentByProject(customer, project);
  }

  @Override
  public Page<DocumentForCustomer> getDocumentForCustomerByIdSheet(Pageable pageable,
      Customer customer, Sheet sheet) {
    return this.repository.findDocumentBySheet(pageable, customer, sheet);
  }

  @Override
  public List<DocumentForCustomer> getDocumentForCustomerByIdSheet(Customer customer, Sheet sheet) {
    return this.repository.findDocumentBySheet(customer, sheet);
  }
}
