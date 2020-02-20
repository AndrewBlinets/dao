package by.ipps.dao.controller;

import by.ipps.dao.entity.Department;
import by.ipps.dao.service.DepartmentService;
import by.ipps.dao.utils.view.ViewContact;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

  private DepartmentService departmentService;

  public ContactController(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  @JsonView(ViewContact.BaseClass.class)
  @GetMapping
  @ResponseBody
  public List<Department> getAllContact() {
      return departmentService.findAll();
  }
}
