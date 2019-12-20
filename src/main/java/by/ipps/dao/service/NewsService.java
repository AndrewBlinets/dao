package by.ipps.dao.service;

import by.ipps.dao.entity.News;
import by.ipps.dao.entity.Section;
import by.ipps.dao.service.base.BaseEntityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface NewsService extends BaseEntityService<News> {
    News findByIdForClient(Long id, Section section);
    Page<News> findPagingRecordsForClient(Pageable pageable, Section section);
    News findById(Long id, Section section);
}
