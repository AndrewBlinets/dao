package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.custom.CustomPage;
import by.ipps.dao.dto.project.ProjectDto;
import by.ipps.dao.dto.project.ProjectDtoFull;
import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.Project;
import by.ipps.dao.entity.Section;
import by.ipps.dao.service.ProjectService;
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
@RequestMapping("/project")
public class ProjectController extends BaseEntityAbstractController<Project, ProjectService>
    implements BaseEntityController<Project> {

  private ModelMapper mapper;
  private ProjectService service;

  protected ProjectController(ProjectService projectService, ModelMapper mapper) {
    super(projectService);
    this.mapper = mapper;
    this.service = projectService;
  }

  @PersistenceContext private EntityManager entityManager;

  @GetMapping("/client")
  public ResponseEntity<CustomPage<ProjectDto>> getAllForClient(
      @PageableDefault() Pageable pageable,
      @RequestParam(value = "language", required = false, defaultValue = "ru") String language,
      @RequestParam(value = "section", required = false) Section section,
      @RequestParam(value = "department", required = false) Department department) {
    entityManager
        .unwrap(Session.class)
        .enableFilter(FilterName.LANGUAGE)
        .setParameter("language", language);
    Page<Project> news = service.findNewsPageBySectionAndDepartment(section, department, pageable);
    java.lang.reflect.Type targetListType = new TypeToken<CustomPage<ProjectDto>>() {}.getType();
    CustomPage<ProjectDto> projectDto = mapper.map(news, targetListType);
    entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
    return new ResponseEntity<>(projectDto, HttpStatus.OK);
  }

  @Transactional
  @GetMapping("/client/{id}")
  public ResponseEntity<ProjectDtoFull> getByIdForClient(
      @PathVariable Long id,
      @RequestParam(value = "language", required = false, defaultValue = "ru") String language,
      @RequestParam(value = "section", required = false) Section section,
      @RequestParam(value = "department", required = false) Department department) {
    entityManager
        .unwrap(Session.class)
        .enableFilter(FilterName.LANGUAGE)
        .setParameter("language", language);
    Project project = service.findByIdAndSectionAndDepartment(id, section, department);
    if (project != null) {
      ProjectDtoFull projectDtoFull = mapper.map(project, ProjectDtoFull.class);
      entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
      return new ResponseEntity<>(projectDtoFull, HttpStatus.OK);
    } else {
      entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity<Page<Project>> getAll(
      Pageable pageable, String language, Section section, Department department) {
    Page<Project> newsPage =
        service.findNewsPageBySectionAndDepartment(section, department, pageable);
    return new ResponseEntity<>(newsPage, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Project> get(
      Long id, String language, Section section, Department department) {
    Project news = service.findByIdAndSectionAndDepartment(id, section, department);
    return new ResponseEntity<>(news, news != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
  }
}
