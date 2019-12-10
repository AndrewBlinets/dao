package by.ipps.dao.service.impl;

import by.ipps.dao.entity.News;
import by.ipps.dao.repository.NewsRepository;
import by.ipps.dao.service.NewsService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl extends BaseEntityServiceImpl<News, NewsRepository> implements NewsService {

    private final NewsRepository repository;

    public NewsServiceImpl(NewsRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<News> getNewsByClient(String code){
        return repository.findByStatusRAndLanguageVersions_codeLanguage("A", code);
    }

    @Override
    public News findByIdForClient(Long id) {
        return repository.findByIdAndStatusRAndAndDatePublicBefore(id, "A", new Date()).orElse(null);
    }

    @Override
    public Page<News> findPagingRecordsForClient(Pageable pageable) {
        return repository.findByStatusRAndAndDatePublicBefore(pageable, "A", new Date());
    }

}
