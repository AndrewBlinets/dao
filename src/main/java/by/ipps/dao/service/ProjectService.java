package by.ipps.dao.service;

import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.Project;
import by.ipps.dao.entity.Sheet;
import by.ipps.dao.service.base.BaseEntityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService extends BaseEntityService<Project> {

  Page<Project> findProjectPageBySectionAndDepartment(
      Sheet section, Department department, Pageable pageable);

  Project findByIdAndSectionAndDepartmentForClient(Long id, Sheet section, Department department);

  Project findByIdAndSectionAndDepartment(Long id, Sheet sheet, Department department);

  Page<Project> findProjectPageBySectionAndDepartmentForAdmin(
      Sheet sheet, Department department, Pageable pageable);

  Project findByIdAndPublicForCustomer(long project);
}
