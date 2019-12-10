package by.ipps.dao.service.base;

import by.ipps.dao.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseEntityService<T extends BaseEntity> {

    T create(T t);
    Page<T> findPagingRecords(Pageable page);
    T findById(Long id);
    T update(T t);
    boolean delete(T t);
    List<T> findAll();
}
