package by.ipps.dao.repository;

import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.Project;
import by.ipps.dao.entity.Sheet;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends BaseEntityRepository<Project> {
  @Query(
      value =
          "SELECT p from Project p where p.statusR = 'A' and (p.page = :page or :page is null) and"
              + " (p.department = :department or :department is null) and p.status = 1")
  Page<Project> findProjectPageBypageAndDepartment(
      @Param("page") Sheet sheet, @Param("department") Department department, Pageable pageable);

  @Query(
      value =
          "select p from Project p where p.statusR = 'A' and p.id = :id and"
              + " (p.page = :page or :page is null) and"
              + " (p.department = :department or :department is null)  and p.status = 1")
  Optional<Project> findByIdAndSectionAndDepartmentForClient(
      @Param("id") Long id, @Param("page") Sheet sheet, @Param("department") Department department);

  @Query(
      value =
          "SELECT n from Project n where n.statusR = 'A' and (n.page = :page or :page is null) and"
              + " (n.department = :department or :department is null)")
  Page<Project> findProjectPageBypageAndDepartmentForAdmin(
      @Param("page") Sheet sheet, @Param("department") Department department, Pageable pageable);

  @Query(
      value =
          "select p from Project p where p.statusR = 'A' and p.id = :id and"
              + " (p.page = :page or :page is null) and"
              + " (p.department = :department or :department is null)")
  Optional<Project> findByIdAndSectionAndDepartment(
      @Param("id") Long id, @Param("page") Sheet sheet, @Param("department") Department department);
}
