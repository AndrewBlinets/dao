package by.ipps.dao.repository;

import by.ipps.dao.entity.Customer;
import by.ipps.dao.entity.DocumentForCustomer;
import by.ipps.dao.entity.Project;
import by.ipps.dao.entity.Sheet;
import java.util.List;
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

  @Query(
      value =
          "select document "
              + "from DocumentForCustomer document "
              + "left join document.project project "
              + "left join project.customers customerProject "
              + "where project.statusR = 'A' and "
              + "customerProject in :customer and "
              + "project = :project")
  Page<DocumentForCustomer> findDocumentByProject(Pageable pageable, Customer customer, Project project);

  @Query(
      value =
          "select document "
              + "from DocumentForCustomer document "
              + "left join document.project project "
              + "left join project.customers customerProject "
              + "where project.statusR = 'A' and "
              + "customerProject in :customer and "
              + "project = :project")
  List<DocumentForCustomer> findDocumentByProject(Customer customer, Project project);

  @Query(
      value =
          "select document "
              + "from DocumentForCustomer document "
              + "left join document.sheet sheet "
              + "left join sheet.customers customerSheet "
              + "where sheet.statusR = 'A' and "
              + "customerSheet in :customer "
              + "and sheet = :sheet")
  Page<DocumentForCustomer> findDocumentBySheet(Pageable pageable, Customer customer, Sheet sheet);

  @Query(
      value =
          "select document "
              + "from DocumentForCustomer document "
              + "left join document.sheet sheet "
              + "left join sheet.customers customerSheet "
              + "where sheet.statusR = 'A' and "
              + "customerSheet in :customer "
              + "and sheet = :sheet")
  List<DocumentForCustomer> findDocumentBySheet(Customer customer, Sheet sheet);
}
