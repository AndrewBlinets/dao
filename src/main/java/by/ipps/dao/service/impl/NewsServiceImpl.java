package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.News;
import by.ipps.dao.entity.Section;
import by.ipps.dao.repository.NewsRepository;
import by.ipps.dao.service.NewsService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl extends BaseEntityServiceImpl<News, NewsRepository> implements NewsService {

    private final NewsRepository repository;

    public NewsServiceImpl(NewsRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Page<News> findNewsPageBySectionAndDepartment(Section section, Department department, Pageable pageable) {
        return repository.findNewsPageBySectionAndDepartment(section, department, pageable);
    }

    @Override
    public News findByIdAndSectionAndDepartment(Long id, Section section, Department department) {
        return repository.findByIdAndSectionAndDepartment(id, section, department).orElse(null);
    }

    @Override
    public Page<News> findNewsPageBySectionAndDepartmentForClient(Section section, Department department, Pageable pageable) {
        return repository.findNewsPageBySectionAndDepartmentForClient(section, department, pageable);
    }

    @Override
    public News findByIdAndSectionAndDepartmentForClient(Long id, Section section, Department department) {
        return repository.findByIdAndSectionAndDepartmentForClient(id, section, department).orElse(null);
    }
}
