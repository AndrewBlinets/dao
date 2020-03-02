package by.ipps.dao.service;

import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.News;
import by.ipps.dao.entity.PageWithSection;
import by.ipps.dao.service.base.BaseEntityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface NewsService extends BaseEntityService<News> {
  Page<News> findNewsPageBypageAndDepartment(
      PageWithSection pageWithSection, Department department, Pageable pageable);

  News findByIdAndSectionAndDepartment(
      Long id, PageWithSection pageWithSection, Department department);

  Page<News> findNewsPageBySectionAndDepartmentForClient(
      PageWithSection pageWithSection, Department department, Pageable pageable);

  News findByIdAndSectionAndDepartmentForClient(
      Long id, PageWithSection pageWithSection, Department department);

  Page<News> findNewsPageBySectionAndDepartmentForAdmin(
      PageWithSection pageWithSection, Department department, Pageable pageable);
}
