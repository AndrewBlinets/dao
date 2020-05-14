package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.Sheet;
import by.ipps.dao.entity.UserPortal;
import by.ipps.dao.entity.Vacancy;
import by.ipps.dao.service.VacancyService;
import by.ipps.dao.utils.view.ViewVacancy;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vacancy")
public class VacancyController extends BaseEntityAbstractController<Vacancy, VacancyService>
    implements BaseEntityController<Vacancy> {
  protected VacancyController(VacancyService vacancyService) {
    super(vacancyService);
  }

  @Override
  @JsonView(ViewVacancy.BaseClass.class)
  public ResponseEntity<Vacancy> get(Long id, String language, Sheet sheet, Department department) {
    return super.get(id, language, sheet, department);
  }

  @Override
  @JsonView(ViewVacancy.BaseClass.class)
  public ResponseEntity<Vacancy> create(Vacancy entity, UserPortal userPortal) {
    return super.create(entity, userPortal);
  }

  @Override
  @JsonView(ViewVacancy.BaseClass.class)
  public ResponseEntity<Vacancy> update(Vacancy entity, UserPortal userPortal) {
    return super.update(entity, userPortal);
  }

  @Override
  @JsonView(ViewVacancy.BaseClass.class)
  public ResponseEntity<Boolean> remove(Vacancy id, UserPortal userPortal) {
    return super.remove(id, userPortal);
  }

  @Override
  @JsonView(ViewVacancy.BaseClass.class)
  public ResponseEntity<Page<Vacancy>> getAll(Pageable pageable, String language, Sheet sheet, Department department) {
    return super.getAll(pageable, language, sheet, department);
  }

  @Override
  @JsonView(ViewVacancy.BaseClass.class)
  public ResponseEntity<List<Vacancy>> getAll(Sheet sheet, Department department) {
    return super.getAll(sheet, department);
  }
}
