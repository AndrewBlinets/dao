package by.ipps.dao.service;

import by.ipps.dao.entity.Customer;
import by.ipps.dao.entity.NewsForCustomer;
import by.ipps.dao.entity.Project;
import by.ipps.dao.repository.BaseEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsForCustomerRepository extends BaseEntityRepository<NewsForCustomer> {

  Page<NewsForCustomer> findAllByProjectAndStatusR(
      Project project, String statusR, Pageable pageable);

  List<NewsForCustomer> findAllByProjectAndStatusR(Project project, String a);

  @Query(
      value =
          "select news "
              + "from NewsForCustomer news "
              + "left join news.project project "
              + "left join project.customers customerProject "
              + "left join news.sheet sheet "
              + "left join sheet.customers customerSheet "
              + "where project.statusR = 'A' and "
              + "sheet.statusR = 'A' and "
              + "customerProject in :customer and "
              + "customerSheet in :customer")
  Page<NewsForCustomer> findNewsCustomer(Pageable pageable, Customer customer);
}
