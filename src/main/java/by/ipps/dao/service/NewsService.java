package by.ipps.dao.service;

import by.ipps.dao.entity.News;
import by.ipps.dao.service.base.BaseEntityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsService extends BaseEntityService<News> {
    public List<News> getNewsByClient(String code);

    News findByIdForClient(Long id);

    Page<News> findPagingRecordsForClient(Pageable pageable);
}
