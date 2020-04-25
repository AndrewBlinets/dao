package by.ipps.dao.controller;

import by.ipps.dao.entity.Company;
import by.ipps.dao.service.CompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

  private CompanyService companyService;

  public CompanyController(CompanyService companyService) {
    this.companyService = companyService;
  }

  @GetMapping
  @ResponseBody
  public Company getInformationAboutCompany() {
    return companyService.getActualInfo();
  }

  @PostMapping
  @ResponseBody
  public Company setInformationAboutCompany(@RequestBody Company company) {
    return companyService.setActualInfo(company);
  }
}
