package by.ipps.dao.repository;

import by.ipps.dao.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends BaseEntityRepository<News> {
    List<News> findByStatusRAndLanguageVersions_codeLanguage(String rstatus, String code);

    Optional<News> findByIdAndStatusRAndAndDatePublicBefore(Long id, String rstatus, Date date);

    Page<News> findByStatusRAndAndDatePublicBefore(Pageable pageable, String a, Date date);
}
