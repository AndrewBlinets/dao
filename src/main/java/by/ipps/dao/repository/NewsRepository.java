package by.ipps.dao.repository;

import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.News;
import by.ipps.dao.entity.Sheet;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends BaseEntityRepository<News> {
  @Query(
      value =
          "select n from News n where n.statusR = 'A' and n.id = :id and"
              + " (n.page = :section or :section is null) and (n.department = :department or :department is null)")
  Optional<News> findByIdAndSectionAndDepartment(
      @Param("id") Long id,
      @Param("section") Sheet section,
      @Param("department") Department department);

  @Query(
      value =
          "SELECT n from News n where n.statusR = 'A' and (n.page = :page or :page is null) and"
              + " (n.department = :department or :department is null)")
  Page<News> findNewsPageBySectionAndDepartment(
      @Param("page") Sheet sheet, @Param("department") Department department, Pageable pageable);

  @Query(
      value =
          "select n from News n where n.statusR = 'A' and n.id = :id and"
              + " (n.page = :page or :page is null) and (n.department = :department or :department is null) "
              + "and  n.datePublic < current_timestamp and n.status = 1")
  Optional<News> findByIdAndSectionAndDepartmentForClient(
      @Param("id") Long id, @Param("page") Sheet sheet, @Param("department") Department department);

  @Query(
      value =
          "SELECT n from News n where n.statusR = 'A' and (n.page = :page or :page is null) and"
              + " (n.department = :department or :department is null) and  n.datePublic < current_timestamp and n.status = 1 ")
  Page<News> findNewsPageBypageAndDepartmentForClient(
      @Param("page") Sheet sheet, @Param("department") Department department, Pageable pageable);

  @Query(
      value =
          "SELECT n from News n where n.statusR = 'A' and (n.page = :page or :page is null) and"
              + " (n.department = :department or :department is null)")
  Page<News> findNewsPageBySectionAndDepartmentForAdmin(
      @Param("page") Sheet sheet, @Param("department") Department department, Pageable pageable);
}
