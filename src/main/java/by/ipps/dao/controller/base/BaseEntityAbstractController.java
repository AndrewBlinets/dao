package by.ipps.dao.controller.base;

import by.ipps.dao.entity.*;
import by.ipps.dao.service.LoggerService;
import by.ipps.dao.service.base.BaseEntityService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;

@Log4j2
@Transactional
public abstract class BaseEntityAbstractController<
        T extends BaseEntity, S extends BaseEntityService<T>>
    implements BaseEntityController<T> {

  protected static final String CREATE = "CREATE";
  protected static final String REMOVE = "REMOVE";
  protected static final String UPDATE = "UPDATE";

  protected final S baseEntityServuce;

  protected BaseEntityAbstractController(S s) {
    this.baseEntityServuce = s;
  }

  @Autowired protected LoggerService loggerService;
  @Autowired protected ModelMapper modelMapper;

  @Override
  public ResponseEntity<T> get(Long id, String language, Section section, Department department) {
    log.info(id);
    log.info(language);
    T entity = baseEntityServuce.findById(id);
    return new ResponseEntity<>(entity, entity != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<T> create(T entity, UserPortal userPortal) {
    T saved = baseEntityServuce.create(entity);
    if (saved != null) {
      loggerService.create(
          new Logger(userPortal, String.valueOf(entity.getClass()), entity.getId(), CREATE));
    }
    return new ResponseEntity<>(saved, saved != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
  }

  @Override
  public ResponseEntity<T> update(T entity, UserPortal userPortal) {
    T oldEntity = baseEntityServuce.findById(entity.getId());
    entity.setDateChangeStatusR(oldEntity.getDateChangeStatusR());
    entity.setDti(oldEntity.getDti());
    T saved = baseEntityServuce.update(entity);
    if (saved != null) {
      loggerService.create(
          new Logger(userPortal, String.valueOf(entity.getClass()), entity.getId(), UPDATE, oldEntity.toString()));
    }
    return new ResponseEntity<>(saved, saved != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
  }

  @Override
  public ResponseEntity<Boolean> remove(T id, UserPortal userPortal) {
    boolean flag = baseEntityServuce.delete(id);
    if (flag)
      loggerService.create(
          new Logger(userPortal, String.valueOf(id.getClass()), id.getId(), REMOVE));
    return new ResponseEntity<>(flag ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
  }

  //    @Transactional
  @Override
  public ResponseEntity<Page<T>> getAll(
      Pageable pageable, String language, Section section, Department department) {
    log.info(pageable.toString());
    log.info(language);
    Page<T> ts = baseEntityServuce.findPagingRecords(pageable);
    return new ResponseEntity<>(ts, ts != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
  }

  @Override
  public ResponseEntity<List<T>> getAll(Section section, Department department) {
    List<T> ts = baseEntityServuce.findAll();
    return new ResponseEntity<>(ts, ts != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
  }
}
