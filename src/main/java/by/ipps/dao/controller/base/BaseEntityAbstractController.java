package by.ipps.dao.controller.base;

import by.ipps.dao.entity.BaseEntity;
import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.Section;
import by.ipps.dao.service.base.BaseEntityService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;

@Log4j2
public abstract class BaseEntityAbstractController<T extends BaseEntity, S extends BaseEntityService<T>>
        implements BaseEntityController<T> {

    protected final S baseEntityServuce;

    protected BaseEntityAbstractController(S s) {
        this.baseEntityServuce = s;
    }

    @Override
    public ResponseEntity<T> get(Long id, String language, Section section, Department department) {
        log.info(id);
        log.info(language);
        T entity = baseEntityServuce.findById(id);
        return new ResponseEntity<>(entity, entity != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<T> create(T entity) {
        T saved = baseEntityServuce.create(entity);
        return new ResponseEntity<>(saved, saved != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<T> update(T entity) {
        T saved = baseEntityServuce.update(entity);
        return new ResponseEntity<>(saved, saved != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Boolean> remove(Long id) {
        boolean flag = baseEntityServuce.delete(baseEntityServuce.findById(id));
        return new ResponseEntity<>(flag ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @Override
    public ResponseEntity<Page<T>> getAll(Pageable pageable, String language) {
        log.info(pageable.toString());
        log.info(language);
        Page<T> ts = baseEntityServuce.findPagingRecords(pageable);
        return new ResponseEntity<>(ts, ts != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<T>> getAll(){
        List<T> ts = baseEntityServuce.findAll();
        return new ResponseEntity<>(ts, ts != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }


}
