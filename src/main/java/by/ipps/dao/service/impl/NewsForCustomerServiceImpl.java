package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Customer;
import by.ipps.dao.entity.NewsForCustomer;
import by.ipps.dao.entity.Project;
import by.ipps.dao.service.NewsForCustomerRepository;
import by.ipps.dao.service.NewsForCustomerService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsForCustomerServiceImpl
    extends BaseEntityServiceImpl<NewsForCustomer, NewsForCustomerRepository>
    implements NewsForCustomerService {

  public NewsForCustomerServiceImpl(NewsForCustomerRepository repository) {
    super(repository);
  }

  @Override
  public Page<NewsForCustomer> findAllByProject(Project project, Pageable pageable) {
    return this.repository.findAllByProjectAndStatusR(project, "A", pageable);
  }

  @Override
  public List<NewsForCustomer> findAllByProject(Project project) {
    return this.repository.findAllByProjectAndStatusR(project, "A");
  }

  @Override
  public Page<NewsForCustomer> getNewsForCustomerByIdCustomer(
      Pageable pageable, Customer customer) {
    return this.repository.findNewsCustomer(pageable, customer);
  }
}
