package by.ipps.dao.service;

import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.News;
import by.ipps.dao.entity.Sheet;
import by.ipps.dao.service.base.BaseEntityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface NewsService extends BaseEntityService<News> {
  Page<News> findNewsPageBypageAndDepartment(Sheet sheet, Department department, Pageable pageable);

  News findByIdAndSectionAndDepartment(Long id, Sheet sheet, Department department);

  Page<News> findNewsPageBySectionAndDepartmentForClient(
      Sheet sheet, Department department, Pageable pageable);

  News findByIdAndSectionAndDepartmentForClient(Long id, Sheet sheet, Department department);

  Page<News> findNewsPageBySectionAndDepartmentForAdmin(
      Sheet sheet, Department department, Pageable pageable);
}
