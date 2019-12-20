package by.ipps.dao.repository;

import by.ipps.dao.entity.News;
import by.ipps.dao.entity.Section;
import by.ipps.dao.utils.anatation.NullMeans;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static by.ipps.dao.utils.anatation.NullBehavior.IGNORED;

@Repository
public interface NewsRepository extends BaseEntityRepository<News> {
    List<News> findByStatusRAndLanguageVersions_codeLanguage(String rstatus, String code);

    Optional<News> findByIdAndStatusRAndAndDatePublicBeforeAndSection(Long id, String rstatus, Date date,
                                                                      Section section);

    Page<News> findByStatusRAndAndDatePublicBefore(Pageable pageable, String a, Date date);

    Page<News> findByStatusRAndAndDatePublicBeforeAndSection(Pageable pageable, String a, Date date, Section section);


    Optional<News> findByIdAndSection(Long id, Section section);
}
