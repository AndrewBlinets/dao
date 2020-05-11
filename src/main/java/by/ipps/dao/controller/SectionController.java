package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.entity.*;
import by.ipps.dao.service.PageService;
import by.ipps.dao.service.SectionService;
import by.ipps.dao.utils.addFun.BaseParams;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/section")
public class SectionController extends BaseEntityAbstractController<Section, SectionService>
    implements BaseEntityController<Section> {

  @PersistenceContext private EntityManager entityManager;
  private ModelMapper mapper;
  private BaseParams baseParams;
  private PageService pageService;

  protected SectionController(
      SectionService sectionService,
      ModelMapper mapper,
      BaseParams baseParams,
      PageService pageService) {
    super(sectionService);
    this.mapper = mapper;
    this.baseParams = baseParams;
    this.pageService = pageService;
  }

  @PutMapping(value = "update/{id}")
  public ResponseEntity<Section> update(
      @RequestBody @Valid Section entity,
      @RequestParam(value = "user") UserPortal userPortal,
      @PathVariable("id") long id) {
    Section dataBaseVersion = baseEntityService.findById(entity.getId());
    baseParams.setParams(entity, dataBaseVersion);
    for (SectionLanguageVersion languageVersion : entity.getLanguageVersions())
      for (SectionLanguageVersion baselanguageVersion : dataBaseVersion.getLanguageVersions()) {
        if (languageVersion.getId() == baselanguageVersion.getId()) {
          baseParams.setParams(languageVersion, baselanguageVersion);
        }
      }
    for (Block block : entity.getBlocks())
      for (Block blockBase : dataBaseVersion.getBlocks())
        if (block.getId() == blockBase.getId()) {
          for (BlockLanguageVersion languageVersion : block.getLanguageVersions())
            for (BlockLanguageVersion baselanguageVersion : blockBase.getLanguageVersions()) {
              if (languageVersion.getId() == baselanguageVersion.getId()) {
                baseParams.setParams(languageVersion, baselanguageVersion);
              }
            }
          baseParams.setParams(block, blockBase);
        }
    Section section = super.update(entity, userPortal).getBody();
    return new ResponseEntity<>(section, HttpStatus.OK);
  }

  @PostMapping(value = "create/{id}")
  public ResponseEntity<Section> create(
      @RequestBody @Valid Section entity,
      @RequestParam(value = "user") UserPortal userPortal,
      @PathVariable("id") long id) {
    Section section = super.create(entity, userPortal).getBody();
    Sheet sheet = pageService.findById(id);
    if (sheet.getSections() == null) sheet.setSections(new ArrayList<>());
    sheet.getSections().add(section);
    pageService.update(sheet);
    return new ResponseEntity<>(section, HttpStatus.OK);
  }

  public ResponseEntity<Section> update(Section entity, UserPortal userPortal) {
    Section dataBaseVersion = baseEntityService.findById(entity.getId());
    baseParams.setParams(entity, dataBaseVersion);
    for (SectionLanguageVersion languageVersion : entity.getLanguageVersions())
      for (SectionLanguageVersion baselanguageVersion : dataBaseVersion.getLanguageVersions()) {
        if (languageVersion.getId() == baselanguageVersion.getId()) {
          baseParams.setParams(languageVersion, baselanguageVersion);
        }
      }
    for (Block block : entity.getBlocks())
      for (Block blockBase : dataBaseVersion.getBlocks())
        if (block.getId() == blockBase.getId()) {
          for (BlockLanguageVersion languageVersion : block.getLanguageVersions())
            for (BlockLanguageVersion baselanguageVersion : blockBase.getLanguageVersions()) {
              if (languageVersion.getId() == baselanguageVersion.getId()) {
                baseParams.setParams(languageVersion, baselanguageVersion);
              }
            }
          baseParams.setParams(block, blockBase);
        }
    return super.update(entity, userPortal);
  }

  @Override
  public ResponseEntity<Boolean> remove(Section id, UserPortal userPortal) {
    return super.remove(id, userPortal);
  }

  @Override
  public ResponseEntity<Page<Section>> getAll(
      Pageable pageable, String language, Sheet sheet, Department department) {
    return new ResponseEntity<>(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
  }

  @Override
  public ResponseEntity<List<Section>> getAll(Sheet sheet, Department department) {
    return new ResponseEntity<>(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
  }

  //  @GetMapping(value = "/getByIdPage/{id}")
  //  @ResponseBody
  //  public ResponseEntity<List<SectionDtoList>> getSectionByIdPage(@PathVariable long id) {
  //    entityManager
  //        .unwrap(Session.class)
  //        .enableFilter(FilterName.LANGUAGE)
  //        .setParameter("language", "ru");
  //    List<Section> sections = baseEntityService.findByIdPage(id);
  //    java.lang.reflect.Type targetListType = new TypeToken<List<SectionDtoList>>() {}.getType();
  //    List<SectionDtoList> sectionDtoLists = mapper.map(sections, targetListType);
  //    entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
  //    return new ResponseEntity<>(sectionDtoLists, HttpStatus.OK);
  //  }
}
