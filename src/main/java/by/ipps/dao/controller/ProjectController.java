package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.custom.CustomPage;
import by.ipps.dao.dto.ProjectDtoForCustomer;
import by.ipps.dao.dto.ProjectDtoForCustomerOne;
import by.ipps.dao.dto.project.ProjectDto;
import by.ipps.dao.dto.project.ProjectDtoAdmin;
import by.ipps.dao.dto.project.ProjectDtoFull;
import by.ipps.dao.entity.Customer;
import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.Project;
import by.ipps.dao.entity.ProjectLanguageVersion;
import by.ipps.dao.entity.Sheet;
import by.ipps.dao.entity.UserPortal;
import by.ipps.dao.service.ProjectService;
import by.ipps.dao.utils.constant.FilterName;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController extends BaseEntityAbstractController<Project, ProjectService>
    implements BaseEntityController<Project> {

  private ModelMapper mapper;
  private ProjectService service;
  @PersistenceContext private EntityManager entityManager;

  protected ProjectController(ProjectService projectService, ModelMapper mapper) {
    super(projectService);
    this.mapper = mapper;
    this.service = projectService;
  }

  @GetMapping("/client")
  public ResponseEntity<CustomPage<ProjectDto>> getAllForClient(
      @PageableDefault() Pageable pageable,
      @RequestParam(value = "language", required = false, defaultValue = "ru") String language,
      @RequestParam(value = "section", required = false) Sheet sheet,
      @RequestParam(value = "department", required = false) Department department) {
    entityManager
        .unwrap(Session.class)
        .enableFilter(FilterName.LANGUAGE)
        .setParameter("language", language);
    Page<Project> projects =
        service.findProjectPageBySectionAndDepartment(sheet, department, pageable);
    java.lang.reflect.Type targetListType = new TypeToken<CustomPage<ProjectDto>>() {}.getType();
    CustomPage<ProjectDto> projectDto = mapper.map(projects, targetListType);
    entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
    return new ResponseEntity<>(projectDto, HttpStatus.OK);
  }

  @Transactional
  @GetMapping("/client/{id}")
  public ResponseEntity<ProjectDtoFull> getByIdForClient(
      @PathVariable Long id,
      @RequestParam(value = "language", required = false, defaultValue = "ru") String language,
      @RequestParam(value = "section", required = false) Sheet sheet,
      @RequestParam(value = "department", required = false) Department department) {
    entityManager
        .unwrap(Session.class)
        .enableFilter(FilterName.LANGUAGE)
        .setParameter("language", language);
    Project project = service.findByIdAndSectionAndDepartmentForClient(id, sheet, department);
    if (project != null) {
      ProjectDtoFull projectDtoFull = mapper.map(project, ProjectDtoFull.class);
      entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
      return new ResponseEntity<>(projectDtoFull, HttpStatus.OK);
    } else {
      entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/admin")
  public ResponseEntity<CustomPage<ProjectDtoAdmin>> getAllForAdmin(
      @PageableDefault() Pageable pageable,
      @RequestParam(value = "language", required = false, defaultValue = "ru") String language,
      @RequestParam(value = "section", required = false) Sheet sheet,
      @RequestParam(value = "department", required = false) Department department) {
    entityManager
        .unwrap(Session.class)
        .enableFilter(FilterName.LANGUAGE)
        .setParameter("language", language);
    Page<Project> news =
        service.findProjectPageBySectionAndDepartmentForAdmin(sheet, department, pageable);
    java.lang.reflect.Type targetListType =
        new TypeToken<CustomPage<ProjectDtoAdmin>>() {}.getType();
    CustomPage<ProjectDtoAdmin> newsDto = mapper.map(news, targetListType);
    entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
    return new ResponseEntity<>(newsDto, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Page<Project>> getAll(
      Pageable pageable, String language, Sheet sheet, Department department) {
    Page<Project> newsPage =
        service.findProjectPageBySectionAndDepartment(sheet, department, pageable);
    return new ResponseEntity<>(newsPage, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Project> get(Long id, String language, Sheet sheet, Department department) {
    Project news = service.findByIdAndSectionAndDepartment(id, sheet, department);
    return new ResponseEntity<>(news, news != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<Project> update(Project entity, UserPortal userPortal) {
    Project dataBaseVersion = service.findById(entity.getId());
    entity.setStatusR(dataBaseVersion.getStatusR());
    entity.setDateChangeStatusR(dataBaseVersion.getDateChangeStatusR());
    entity.setDti(dataBaseVersion.getDti());
    for (ProjectLanguageVersion languageVersion : entity.getLanguageVersions()) {
      for (ProjectLanguageVersion languageVersionBase : dataBaseVersion.getLanguageVersions()) {
        if (languageVersionBase.getId() == languageVersion.getId()) {
          languageVersion.setDti(languageVersionBase.getDti());
          languageVersion.setDateChangeStatusR(languageVersionBase.getDateChangeStatusR());
        }
      }
    }
    return super.update(entity, userPortal);
  }

  @GetMapping(value = "/projectForCustomerByIdCustomer/{customer}")
  @ResponseBody
  public ResponseEntity<List<ProjectDtoForCustomer>> getProjectForCustomerByIdCustomer(
      @PathVariable Customer customer) {
    List<Project> projects = customer.getProjects();
    List<Project> favoriteProjects = customer.getFavoriteProject();
    java.lang.reflect.Type targetListType =
        new TypeToken<List<ProjectDtoForCustomer>>() {}.getType();
    List<ProjectDtoForCustomer> projectsDto = mapper.map(projects, targetListType);
    for(Project project : projects){
      if(favoriteProjects.contains(project)){
        for (ProjectDtoForCustomer projectDtoForCustomer : projectsDto){
          if(projectDtoForCustomer.getId() == project.getId()){
            projectDtoForCustomer.setFavorite(true);
            break;
          }
        }
      }
    }
    return projectsDto.isEmpty()
        ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
        : new ResponseEntity<>(projectsDto, HttpStatus.OK);
  }

  @GetMapping(value = "/projectForCustomerById/{customer}/{project}")
  @ResponseBody
  public ResponseEntity<ProjectDtoForCustomerOne> getProjectForCustomerById(
      @PathVariable Customer customer, @PathVariable Project project) {
    List<Project> projects = customer.getProjects();
    if(projects.contains(project)) {
      ProjectDtoForCustomerOne projectsDto = mapper.map(projects, ProjectDtoForCustomerOne.class);
      return new ResponseEntity<>(projectsDto, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
