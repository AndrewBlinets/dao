package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.News;
import by.ipps.dao.entity.Sheet;
import by.ipps.dao.repository.NewsRepository;
import by.ipps.dao.service.NewsService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl extends BaseEntityServiceImpl<News, NewsRepository>
    implements NewsService {

  private final NewsRepository repository;

  public NewsServiceImpl(NewsRepository repository) {
    super(repository);
    this.repository = repository;
  }

  @Override
  public Page<News> findNewsPageBypageAndDepartment(
      Sheet sheet, Department department, Pageable pageable) {
    return repository.findNewsPageBySectionAndDepartment(sheet, department, pageable);
  }

  @Override
  public News findByIdAndSectionAndDepartment(Long id, Sheet sheet, Department department) {
    return repository.findByIdAndSectionAndDepartment(id, sheet, department).orElse(null);
  }

  @Override
  public Page<News> findNewsPageBySectionAndDepartmentForClient(
      Sheet sheet, Department department, Pageable pageable) {
    return repository.findNewsPageBypageAndDepartmentForClient(sheet, department, pageable);
  }

  @Override
  public News findByIdAndSectionAndDepartmentForClient(
      Long id, Sheet sheet, Department department) {
    return repository.findByIdAndSectionAndDepartment(id, sheet, department).orElse(null);
  }

  @Override
  public Page<News> findNewsPageBySectionAndDepartmentForAdmin(
      Sheet sheet, Department department, Pageable pageable) {
    return repository.findNewsPageBySectionAndDepartmentForAdmin(sheet, department, pageable);
  }
}
