package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.Sheet;
import by.ipps.dao.entity.UserPortal;
import by.ipps.dao.service.DepartmentService;
import by.ipps.dao.utils.view.ViewDepartment;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController
    extends BaseEntityAbstractController<Department, DepartmentService>
    implements BaseEntityController<Department> {

  protected DepartmentController(DepartmentService departmentService) {
    super(departmentService);
  }

  @Override
  @JsonView(ViewDepartment.FullInformationClassDepartment.class)
  public ResponseEntity<Department> create(@Valid Department create, UserPortal userPortal) {
    return super.create(create, userPortal);
  }

  @JsonView(ViewDepartment.FullInformationClassDepartment.class)
  @Override
  public ResponseEntity<Department> get(
      Long id, String language, Sheet sheet, Department department) {
    return super.get(id, language, sheet, department);
  }

  @Override
  @JsonView(ViewDepartment.FullInformationClassDepartment.class)
  public ResponseEntity<Department> update(@Valid Department entity, UserPortal userPortal) {
    return super.update(entity, userPortal);
  }

  @Override
  @JsonView(ViewDepartment.BaseClassDepartment.class)
  public ResponseEntity<Page<Department>> getAll(
      Pageable pageable, String language, Sheet sheet, Department department) {
    return super.getAll(pageable, language, sheet, department);
  }

  @Override
  @JsonView(ViewDepartment.BaseClassDepartment.class)
  public ResponseEntity<List<Department>> getAll(Sheet sheet, Department department) {
    return super.getAll(sheet, department);
  }
}
