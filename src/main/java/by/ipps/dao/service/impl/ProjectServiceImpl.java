package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.PageWithSection;
import by.ipps.dao.entity.Project;
import by.ipps.dao.repository.ProjectRepository;
import by.ipps.dao.service.ProjectService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl extends BaseEntityServiceImpl<Project, ProjectRepository>
    implements ProjectService {

  private ProjectRepository repository;

  public ProjectServiceImpl(ProjectRepository repository) {
    super(repository);
    this.repository = repository;
  }

  @Override
  public Page<Project> findProjectPageBySectionAndDepartment(
      PageWithSection section, Department department, Pageable pageable) {
    return repository.findProjectPageBypageAndDepartment(section, department, pageable);
  }

  @Override
  public Page<Project> findProjectPageBySectionAndDepartmentForAdmin(
      PageWithSection section, Department department, Pageable pageable) {
    return repository.findProjectPageBypageAndDepartmentForAdmin(section, department, pageable);
  }

  @Override
  public Project findByIdAndSectionAndDepartmentForClient(
      Long id, PageWithSection section, Department department) {
    return repository.findByIdAndSectionAndDepartmentForClient(id, section, department).orElse(null);
  }

  @Override
  public Project findByIdAndSectionAndDepartment(
      Long id, PageWithSection section, Department department) {
    return repository.findByIdAndSectionAndDepartment(id, section, department).orElse(null);
  }
}
