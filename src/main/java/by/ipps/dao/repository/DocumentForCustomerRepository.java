package by.ipps.dao.repository;

import by.ipps.dao.entity.Customer;
import by.ipps.dao.entity.DocumentForCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentForCustomerRepository extends BaseEntityRepository<DocumentForCustomer> {
  @Query(
      value =
          "select document "
              + "from DocumentForCustomer document "
              + "left join document.project project "
              + "left join project.customers customerProject "
              + "left join document.sheet sheet "
              + "left join sheet.customers customerSheet "
              + "where project.statusR = 'A' and "
              + "sheet.statusR = 'A' and "
              + "customerProject in :customer and "
              + "customerSheet in :customer")
  Page<DocumentForCustomer> findDocumentByCustomer(Pageable pageable, Customer customer);
}
