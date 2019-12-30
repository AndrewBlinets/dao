package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.custom.CustomPage;
import by.ipps.dao.dto.news.NewsDto;
import by.ipps.dao.dto.news.NewsDtoAdmin;
import by.ipps.dao.dto.news.NewsDtoFull;
import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.News;
import by.ipps.dao.entity.Section;
import by.ipps.dao.service.NewsService;
import by.ipps.dao.utils.constant.FilterName;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/news")
public class NewsController extends BaseEntityAbstractController<News, NewsService>
        implements BaseEntityController<News> {

    private final NewsService service;
    private ModelMapper mapper;

    protected NewsController(NewsService newsService, ModelMapper mapper) {
        super(newsService);
        this.service = newsService;
        this.mapper = mapper;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/client")
    public ResponseEntity<CustomPage<NewsDto>> getAllForClient(
            @PageableDefault() Pageable pageable,
            @RequestParam(value = "language", required = false, defaultValue = "ru") String language,
            @RequestParam(value = "section", required = false) Section section,
            @RequestParam(value = "department", required = false) Department department) {
        entityManager.unwrap(Session.class).enableFilter(FilterName.LANGUAGE).setParameter("language", language);
        Page<News> news = service.findNewsPageBySectionAndDepartmentForClient(section, department, pageable);
        java.lang.reflect.Type targetListType = new TypeToken<CustomPage<NewsDto>>() {
        }.getType();
        CustomPage<NewsDto> newsDto = mapper.map(news, targetListType);
        entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
        return new ResponseEntity<>(newsDto, HttpStatus.OK );
    }

    @Transactional
    @GetMapping("/client/{id}")
    public ResponseEntity<NewsDtoFull> getByIdForClient(
            @PathVariable Long id,
            @RequestParam(value = "language", required = false, defaultValue = "ru") String language,
            @RequestParam(value = "section", required = false) Section section,
            @RequestParam(value = "department", required = false) Department department) {
        entityManager.unwrap(Session.class).enableFilter(FilterName.LANGUAGE).setParameter("language", language);
        News news = service.findByIdAndSectionAndDepartmentForClient(id, section, department);
        if(news != null) {
            NewsDtoFull newsF = mapper.map(news, NewsDtoFull.class);
            entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
            return new ResponseEntity<>(newsF, HttpStatus.OK);
        } else {
            entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }@GetMapping("/admin")
    public ResponseEntity<CustomPage<NewsDtoAdmin>> getAllForAdmin(
            @PageableDefault() Pageable pageable,
            @RequestParam(value = "language", required = false, defaultValue = "ru") String language,
            @RequestParam(value = "section", required = false) Section section,
            @RequestParam(value = "department", required = false) Department department) {
        entityManager.unwrap(Session.class).enableFilter(FilterName.LANGUAGE).setParameter("language", language);
        Page<News> news = service.findNewsPageBySectionAndDepartmentForAdmin(section, department, pageable);
        java.lang.reflect.Type targetListType = new TypeToken<CustomPage<NewsDtoAdmin>>() {
        }.getType();
        CustomPage<NewsDtoAdmin> newsDto = mapper.map(news, targetListType);
        entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
        return new ResponseEntity<>(newsDto, HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Page<News>> getAll(
            Pageable pageable, String language, Section section, Department department) {
        Page<News> newsPage = service.findNewsPageBySectionAndDepartment(section, department, pageable);
        return new ResponseEntity<>(newsPage, HttpStatus.OK );
    }

    @Override
    public ResponseEntity<News> get(Long id, String language, Section section, Department department) {
        News news = service.findByIdAndSectionAndDepartment(id, section, department);
        return new ResponseEntity<>(news, news != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
