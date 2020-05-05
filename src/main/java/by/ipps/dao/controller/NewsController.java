package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.custom.CustomPage;
import by.ipps.dao.dto.news.NewsDto;
import by.ipps.dao.dto.news.NewsDtoAdmin;
import by.ipps.dao.dto.news.NewsDtoFull;
import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.News;
import by.ipps.dao.entity.NewsLanguageVersion;
import by.ipps.dao.entity.Sheet;
import by.ipps.dao.entity.*;
import by.ipps.dao.service.NewsService;
import by.ipps.dao.utils.constant.FilterName;
import by.ipps.dao.utils.view.ViewNews;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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
@RequestMapping("/news")
public class NewsController extends BaseEntityAbstractController<News, NewsService>
    implements BaseEntityController<News> {

  private final NewsService service;
  private ModelMapper mapper;
  @PersistenceContext private EntityManager entityManager;

  protected NewsController(NewsService newsService, ModelMapper mapper) {
    super(newsService);
    this.service = newsService;
    this.mapper = mapper;
  }

  @GetMapping("/client")
  public ResponseEntity<CustomPage<NewsDto>> getAllForClient(
      @PageableDefault() Pageable pageable,
      @RequestParam(value = "language", required = false, defaultValue = "ru") String language,
      @RequestParam(value = "section", required = false) Sheet sheet,
      @RequestParam(value = "department", required = false) Department department) {
    entityManager
        .unwrap(Session.class)
        .enableFilter(FilterName.LANGUAGE)
        .setParameter("language", language);
    Page<News> news =
        service.findNewsPageBySectionAndDepartmentForClient(sheet, department, pageable);
    java.lang.reflect.Type targetListType = new TypeToken<CustomPage<NewsDto>>() {}.getType();
    CustomPage<NewsDto> newsDto = mapper.map(news, targetListType);
    entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
    return new ResponseEntity<>(newsDto, HttpStatus.OK);
  }

  @Transactional
  @GetMapping("/client/{id}")
  public ResponseEntity<NewsDtoFull> getByIdForClient(
      @PathVariable Long id,
      @RequestParam(value = "language", required = false, defaultValue = "ru") String language,
      @RequestParam(value = "section", required = false) Sheet sheet,
      @RequestParam(value = "department", required = false) Department department) {
    entityManager
        .unwrap(Session.class)
        .enableFilter(FilterName.LANGUAGE)
        .setParameter("language", language);
    News news = service.findByIdAndSectionAndDepartmentForClient(id, sheet, department);
    if (news != null) {
      NewsDtoFull newsF = mapper.map(news, NewsDtoFull.class);
      entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
      return new ResponseEntity<>(newsF, HttpStatus.OK);
    } else {
      entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/admin")
  public ResponseEntity<CustomPage<NewsDtoAdmin>> getAllForAdmin(
      @PageableDefault() Pageable pageable,
      @RequestParam(value = "language", required = false, defaultValue = "ru") String language,
      @RequestParam(value = "section", required = false) Sheet sheet,
      @RequestParam(value = "department", required = false) Department department) {
    entityManager
        .unwrap(Session.class)
        .enableFilter(FilterName.LANGUAGE)
        .setParameter("language", language);
    Page<News> news =
        service.findNewsPageBySectionAndDepartmentForAdmin(sheet, department, pageable);
    java.lang.reflect.Type targetListType = new TypeToken<CustomPage<NewsDtoAdmin>>() {}.getType();
    CustomPage<NewsDtoAdmin> newsDto = mapper.map(news, targetListType);
    entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
    return new ResponseEntity<>(newsDto, HttpStatus.OK);
  }

  @Override
  @JsonView({ViewNews.AdminNewsClass.class})
  public ResponseEntity<Page<News>> getAll(
      Pageable pageable, String language, Sheet sheet, Department department) {
    Page<News> newsPage = service.findNewsPageBypageAndDepartment(sheet, department, pageable);
    return new ResponseEntity<>(newsPage, HttpStatus.OK);
  }

  @Override
  @JsonView({ViewNews.AdminNewsClass.class})
  public ResponseEntity<News> get(Long id, String language, Sheet sheet, Department department) {
    News news = service.findByIdAndSectionAndDepartment(id, sheet, department);
    if (news != null && news.getLanguageVersions().size() > 3) {}

    return new ResponseEntity<>(news, news != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
  }

  @Override
  @JsonView(ViewNews.AdminNewsClass.class)
  public ResponseEntity<News> create(News entity, UserPortal userPortal) {
    entity.getLanguageVersions().addAll(substringField(entity.getLanguageVersions()));
    return super.create(entity, userPortal);
  }

  private List<NewsLanguageVersion> substringField(List<NewsLanguageVersion> languageVersions) {
    List<NewsLanguageVersion> addEntity = new ArrayList<>();
    for (NewsLanguageVersion newsLanguageVersion : languageVersions) {
      NewsLanguageVersion newNewsLanguageVersion = new NewsLanguageVersion();
      boolean flagChange = false;
      if (newsLanguageVersion.getContent().length() > 10000) {
        newNewsLanguageVersion.setContent(newsLanguageVersion.getContent().substring(10000));
        newsLanguageVersion.setContent(newsLanguageVersion.getContent().substring(0, 10000));
        flagChange = true;
      }
      if (flagChange) {
        newNewsLanguageVersion.setCodeLanguage(newsLanguageVersion.getCodeLanguage());
        addEntity.add(newNewsLanguageVersion);
      }
    }
    return addEntity;
  }

  @Override
  @JsonView(ViewNews.AdminNewsClass.class)
  public ResponseEntity<News> update(News entity, UserPortal userPortal) {
    News dataBaseVersion = service.findById(entity.getId());
    entity.setStatusR(dataBaseVersion.getStatusR());
    entity.setDateChangeStatusR(dataBaseVersion.getDateChangeStatusR());
    entity.setDti(dataBaseVersion.getDti());
    for (NewsLanguageVersion languageVersion : entity.getLanguageVersions()) {
      for (NewsLanguageVersion languageVersionBase : dataBaseVersion.getLanguageVersions()) {
        if (languageVersionBase.getId() == languageVersion.getId()) {
          languageVersion.setDti(languageVersionBase.getDti());
          languageVersion.setDateChangeStatusR(languageVersionBase.getDateChangeStatusR());
        }
      }
    }
    return super.update(entity, userPortal);
  }
}
