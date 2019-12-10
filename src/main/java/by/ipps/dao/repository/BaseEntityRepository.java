package by.ipps.dao.repository;

import by.ipps.dao.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseEntityRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

    Optional<T> findByIdAndStatusR(Long id, String r);
    <T extends BaseEntity> Page<T> findByStatusR(Pageable page, String a);
    <T extends BaseEntity> List<T> findByStatusR(String a);
}
