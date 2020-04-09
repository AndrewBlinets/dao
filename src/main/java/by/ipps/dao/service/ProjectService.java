package by.ipps.dao.service;

import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.PageWithSection;
import by.ipps.dao.entity.Project;
import by.ipps.dao.service.base.BaseEntityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService extends BaseEntityService<Project> {

  Page<Project> findProjectPageBySectionAndDepartment(
      PageWithSection section, Department department, Pageable pageable);

  Project findByIdAndSectionAndDepartmentForClient(Long id, PageWithSection section, Department department);

  Project findByIdAndSectionAndDepartment(Long id, PageWithSection pageWithSection, Department department);

  Page<Project> findProjectPageBySectionAndDepartmentForAdmin(PageWithSection pageWithSection, Department department, Pageable pageable);
}
