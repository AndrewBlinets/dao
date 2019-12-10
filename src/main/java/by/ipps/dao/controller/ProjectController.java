package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.dto.project.PageProjectDto;
import by.ipps.dao.dto.project.ProjectDtoFull;
import by.ipps.dao.entity.Project;
import by.ipps.dao.service.ProjectService;
import by.ipps.dao.utils.constant.FilterName;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
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

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/client")
    public ResponseEntity<PageProjectDto> getAllForClient(
            @PageableDefault() Pageable pageable,
            @RequestParam(value = "language", required = false, defaultValue = "ru") String language) {
        entityManager.unwrap(Session.class).enableFilter(FilterName.LANGUAGE).setParameter("language", language);
        Page<Project> projects = service.findPagingRecordsForClient(pageable);
        PageProjectDto newsDto = mapper.map(projects, PageProjectDto.class);
        entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
        return new ResponseEntity<>(newsDto, projects != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @GetMapping("/client/{id}")
    public ResponseEntity<ProjectDtoFull> getByIdForClient(
            @PathVariable Long id,
            @RequestParam(value = "language", required = false, defaultValue = "ru") String language) {
        entityManager.unwrap(Session.class).enableFilter(FilterName.LANGUAGE).setParameter("language", language);
        Project project = service.findByIdForClient(id);
        if(project != null) {
            ProjectDtoFull newsF = mapper.map(project, ProjectDtoFull.class);
            entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
            return new ResponseEntity<>(newsF, HttpStatus.OK);
        } else {
            entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
