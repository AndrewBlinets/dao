package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.custom.CustomPage;
import by.ipps.dao.dto.sheet.PageDto;
import by.ipps.dao.dto.sheet.PageDtoFull;
import by.ipps.dao.dto.sheet.PageForList;
import by.ipps.dao.entity.Block;
import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.Section;
import by.ipps.dao.entity.Sheet;
import by.ipps.dao.entity.UserPortal;
import by.ipps.dao.service.PageService;
import by.ipps.dao.utils.addFun.BaseParams;
import by.ipps.dao.utils.constant.FilterName;
import by.ipps.dao.utils.view.ViewPage;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page")
public class SheetController extends BaseEntityAbstractController<Sheet, PageService>
    implements BaseEntityController<Sheet> {

  private BaseParams baseParams;
  private ModelMapper mapper;
  @PersistenceContext private EntityManager entityManager;

  protected SheetController(PageService pageService, BaseParams baseParams, ModelMapper mapper) {
    super(pageService);
    this.baseParams = baseParams;
    this.mapper = mapper;
  }

  @Override
  @JsonView(ViewPage.AdminClass.class)
  public ResponseEntity<Sheet> create(@Valid Sheet create, UserPortal userPortal) {
    return super.create(create, userPortal);
  }

  @JsonView(ViewPage.AdminClass.class)
  @Override
  public ResponseEntity<Sheet> get(Long id, String language, Sheet sheet, Department department) {
    return super.get(id, language, sheet, department);
  }

  @Override
  @JsonView(ViewPage.AdminClass.class)
  public ResponseEntity<Sheet> update(@Valid Sheet entity, UserPortal userPortal) {
    setBaseParams(entity);
    return super.update(entity, userPortal);
  }

  private void setBaseParams(Sheet entity) {
    Sheet dataBaseVersion = baseEntityService.findById(entity.getId());
    baseParams.setParams(entity, dataBaseVersion);
    for (Section section : entity.getSections())
      for (Section sectionBase : dataBaseVersion.getSections()) {
        if (sectionBase.getId() == section.getId()) {
          baseParams.setParams(section, sectionBase);
          for (Block block : section.getBlocks())
            for (Block blockBase : sectionBase.getBlocks())
              if (block.getId() == blockBase.getId()) {
                baseParams.setParams(block, blockBase);
              }
        }
      }
  }

  @Override
  public ResponseEntity<Page<Sheet>> getAll(
      Pageable pageable, String language, Sheet sheet, Department department) {
    return new ResponseEntity<>(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
  }

  @Override
  public ResponseEntity<List<Sheet>> getAll(Sheet sheet, Department department) {
    return new ResponseEntity<>(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
  }

  @GetMapping(value = "/admin")
  public ResponseEntity<CustomPage<PageForList>> getAllForAdmin(
      @PageableDefault() Pageable pageable,
      @RequestParam(value = "language", required = false, defaultValue = "ru") String language) {
    entityManager
        .unwrap(Session.class)
        .enableFilter(FilterName.LANGUAGE)
        .setParameter("language", language);
    Page<Sheet> page = baseEntityService.findPagingRecords(pageable);
    java.lang.reflect.Type targetListType = new TypeToken<CustomPage<PageForList>>() {}.getType();
    CustomPage<PageForList> pages = mapper.map(page, targetListType);
    entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
    return new ResponseEntity<>(pages, HttpStatus.OK);
  }

  @GetMapping(value = "/admin/{id}")
  public ResponseEntity<PageDto> getAllForAdmin(
      @PathVariable("id") long id,
      @RequestParam(value = "language", required = false, defaultValue = "ru") String language) {
    entityManager
        .unwrap(Session.class)
        .enableFilter(FilterName.LANGUAGE)
        .setParameter("language", language);
    Sheet pageFromBD = baseEntityService.findById(id);
    PageDto page = mapper.map(pageFromBD, PageDto.class);
    entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
    return new ResponseEntity<>(page, HttpStatus.OK);
  }

  @GetMapping(value = "/client")
  public ResponseEntity<List<PageForList>> getAllForClinet(
      @RequestParam(value = "language", required = false, defaultValue = "ru") String language) {
    entityManager
        .unwrap(Session.class)
        .enableFilter(FilterName.LANGUAGE)
        .setParameter("language", language);
    List<Sheet> page = baseEntityService.findAllForClient();
    java.lang.reflect.Type targetListType = new TypeToken<List<PageForList>>() {}.getType();
    List<PageForList> list = mapper.map(page, targetListType);
    entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping(value = "/client/{id}")
  public ResponseEntity<PageDtoFull> getAllForClient(
      @PathVariable("id") long id,
      @RequestParam(value = "language", required = false, defaultValue = "ru") String language) {
    entityManager
        .unwrap(Session.class)
        .enableFilter(FilterName.LANGUAGE)
        .setParameter("language", language);
    entityManager.unwrap(Session.class).enableFilter(FilterName.STATUS).setParameter("status", 1);
    Sheet pageFromBD = baseEntityService.findByIdForClient(id);
    PageDtoFull page = mapper.map(pageFromBD, PageDtoFull.class);
    entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
    entityManager.unwrap(Session.class).disableFilter(FilterName.STATUS);
    return new ResponseEntity<>(page, HttpStatus.OK);
  }
}
