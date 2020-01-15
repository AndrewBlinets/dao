package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.Section;
import by.ipps.dao.service.DepartmentService;
import by.ipps.dao.utils.view.ViewDepartment;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController
    extends BaseEntityAbstractController<Department, DepartmentService>
    implements BaseEntityController<Department> {

  protected DepartmentController(DepartmentService departmentService) {
    super(departmentService);
  }

//  @Override
//  public ResponseEntity<Department> create(@Valid Department create) {
//    return super.create(create);
//  }

  @JsonView(ViewDepartment.BaseClassDepartment.class)
  @Override
  public ResponseEntity<Department> get(
      Long id, String language, Section section, Department department) {
    return super.get(id, language, section, department);
  }

  //    @Override
  //    public ResponseEntity<Department> update(@Valid Department entity) {
  //        return null;
  //    }

  @Override
  @JsonView(ViewDepartment.BaseClassDepartment.class)
  public ResponseEntity<Page<Department>> getAll(
      Pageable pageable, String language, Section section, Department department) {
    return super.getAll(pageable, language, section, department);
  }

  @Override
  @JsonView(ViewDepartment.BaseClassDepartment.class)
  public ResponseEntity<List<Department>> getAll(Section section, Department department) {
    return super.getAll(section, department);
  }
}
