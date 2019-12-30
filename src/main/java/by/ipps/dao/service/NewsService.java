package by.ipps.dao.service;

import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.News;
import by.ipps.dao.entity.Section;
import by.ipps.dao.service.base.BaseEntityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface NewsService extends BaseEntityService<News> {
    Page<News> findNewsPageBySectionAndDepartment(Section section, Department department, Pageable pageable);
    News findByIdAndSectionAndDepartment(Long id, Section section, Department department);
    Page<News> findNewsPageBySectionAndDepartmentForClient(Section section, Department department, Pageable pageable);
    News findByIdAndSectionAndDepartmentForClient(Long id, Section section, Department department);

    Page<News> findNewsPageBySectionAndDepartmentForAdmin(Section section, Department department, Pageable pageable);
}
