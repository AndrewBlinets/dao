package by.ipps.dao.service;

import by.ipps.dao.entity.NewsForCustomer;
import by.ipps.dao.entity.Project;
import by.ipps.dao.repository.BaseEntityRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsForCustomerRepository extends BaseEntityRepository<NewsForCustomer> {

  Page<NewsForCustomer> findAllByProjectAndStatusR(
      Project project, String statusR, Pageable pageable);

  List<NewsForCustomer> findAllByProjectAndStatusR(Project project, String a);
}
