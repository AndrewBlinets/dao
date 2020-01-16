package by.ipps.dao.repository;

import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.Project;
import by.ipps.dao.entity.Section;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends BaseEntityRepository<Project> {
  @Query(
      value =
          "SELECT p from Project p where p.statusR = 'A' and (p.section = :section or :section is null) and"
              + " (p.department = :department or :department is null)")
  Page<Project> findNewsPageBySectionAndDepartment(
      @Param("section") Section section,
      @Param("department") Department department,
      Pageable pageable);

  @Query(
      value =
          "select p from Project p where p.statusR = 'A' and p.id = :id and"
              + " (p.section = :section or :section is null) and"
              + " (p.department = :department or :department is null)")
  Optional<Project> findByIdAndSectionAndDepartment(
      @Param("id") Long id,
      @Param("section") Section section,
      @Param("department") Department department);
}
