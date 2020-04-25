package by.ipps.dao.controller.base;

import by.ipps.dao.entity.BaseEntity;
import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.Logger;
import by.ipps.dao.entity.Sheet;
import by.ipps.dao.entity.UserPortal;
import by.ipps.dao.service.LoggerService;
import by.ipps.dao.service.base.BaseEntityService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Log4j2
@Transactional
public abstract class BaseEntityAbstractController<
        T extends BaseEntity, S extends BaseEntityService<T>>
    implements BaseEntityController<T> {

  protected static final String CREATE = "CREATE";
  protected static final String REMOVE = "REMOVE";
  protected static final String UPDATE = "UPDATE";

  protected final S baseEntityService;

  protected BaseEntityAbstractController(S s) {
    this.baseEntityService = s;
  }

  @Autowired protected LoggerService loggerService;
  @Autowired protected ModelMapper modelMapper;

  @Override
  public ResponseEntity<T> get(Long id, String language, Sheet sheet, Department department) {
    log.info(id);
    log.info(language);
    T entity = baseEntityService.findById(id);
    return new ResponseEntity<>(entity, entity != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<T> create(T entity, UserPortal userPortal) {
    T saved = baseEntityService.create(entity);
    if (saved != null) {
      loggerService.create(
          new Logger(userPortal, String.valueOf(entity.getClass()), entity.getId(), CREATE));
    }
    return new ResponseEntity<>(saved, saved != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
  }

  @Override
  public ResponseEntity<T> update(T entity, UserPortal userPortal) {
    T oldEntity = baseEntityService.findById(entity.getId());
    entity.setDateChangeStatusR(oldEntity.getDateChangeStatusR());
    entity.setDti(oldEntity.getDti());
    T saved = baseEntityService.update(entity);
    if (saved != null) {
      loggerService.create(
          new Logger(
              userPortal,
              String.valueOf(entity.getClass()),
              entity.getId(),
              UPDATE,
              oldEntity.toString()));
    }
    return new ResponseEntity<>(saved, saved != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
  }

  @Override
  public ResponseEntity<Boolean> remove(T id, UserPortal userPortal) {
    boolean flag = baseEntityService.delete(id);
    if (flag)
      loggerService.create(
          new Logger(userPortal, String.valueOf(id.getClass()), id.getId(), REMOVE));
    return new ResponseEntity<>(flag ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
  }

  //    @Transactional
  @Override
  public ResponseEntity<Page<T>> getAll(
      Pageable pageable, String language, Sheet sheet, Department department) {
    log.info(pageable.toString());
    log.info(language);
    Page<T> ts = baseEntityService.findPagingRecords(pageable);
    return new ResponseEntity<>(ts, ts != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
  }

  @Override
  public ResponseEntity<List<T>> getAll(Sheet sheet, Department department) {
    List<T> ts = baseEntityService.findAll();
    return new ResponseEntity<>(ts, ts != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
  }
}
