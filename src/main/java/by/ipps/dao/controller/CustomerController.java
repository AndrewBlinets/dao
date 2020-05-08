package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.custom.CustomPage;
import by.ipps.dao.dto.*;
import by.ipps.dao.dto.project.ProjectDtoAdmin;
import by.ipps.dao.entity.Customer;
import by.ipps.dao.entity.Project;
import by.ipps.dao.service.CustomerService;
import by.ipps.dao.service.ProjectService;
import java.util.List;

import by.ipps.dao.utils.constant.FilterName;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseEntityAbstractController<Customer, CustomerService>
    implements BaseEntityController<Customer> {

  private ProjectService projectService;

  public CustomerController(CustomerService customerService, ProjectService projectService, ModelMapper mapper) {
    super(customerService);
    this.projectService = projectService;
    this.mapper = mapper;
  }

  @PostMapping(value = "/authenticate")
  @ResponseBody
  public ResponseEntity<CustomerDto> authenticateCustomer(@RequestBody String login) {
    Customer customer = baseEntityService.getCustomerByLogin(login);
    if (customer != null) {
      CustomerDto customerDto = this.modelMapper.map(customer, CustomerDto.class);
      return new ResponseEntity<>(customerDto, HttpStatus.OK);
    } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping(value = "/getInfoAboutCustomer")
  @ResponseBody
  public ResponseEntity<CustomerDtoFull> getInfoAboutCustomer(@RequestBody String login) {
    Customer customer = baseEntityService.getCustomerByLogin(login);
    if (customer != null) {
      CustomerDtoFull customerDto = this.modelMapper.map(customer, CustomerDtoFull.class);
      return new ResponseEntity<>(customerDto, HttpStatus.OK);
    } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PersistenceContext
  private EntityManager entityManager;
  private ModelMapper mapper;

  @GetMapping(value = "/favoriteProject")
  @ResponseBody
  public ResponseEntity<List<ProjectDtoForCustomerOne>> getFavoriteProject(
      @RequestParam("customer") Customer customer) {
    if (customer != null) {
      entityManager
          .unwrap(Session.class)
          .enableFilter(FilterName.LANGUAGE)
          .setParameter("language", "ru");
      List<Project> projects = customer.getFavoriteProject();
      java.lang.reflect.Type targetListType =
          new TypeToken<List<ProjectDtoForCustomerOne>>() {}.getType();
      List<ProjectDtoForCustomerOne> projectDto = mapper.map(projects, targetListType);
      for (ProjectDtoForCustomerOne project : projectDto){
        project.setFavorites(true);
      }
      entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
      return new ResponseEntity<>(projectDto, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(value = "/favoriteProject")
  @ResponseBody
  public ResponseEntity<ProjectDtoForCustomerOne> addFavoriteProject(@RequestBody FavoriteProject favoriteProject) {
    try {
      Project project = projectService.findByIdAndPublicForCustomer(favoriteProject.getProject());
      Customer customer = this.baseEntityService.findById(favoriteProject.getCustomer());
      if (customer.getProjects().contains(project)
          && !customer.getFavoriteProject().contains(project)) {
        customer.getFavoriteProject().add(project);
        this.baseEntityService.update(customer);
      }
      entityManager
              .unwrap(Session.class)
              .enableFilter(FilterName.LANGUAGE)
              .setParameter("language", "ru");
      ProjectDtoForCustomerOne projectDto = mapper.map(project, ProjectDtoForCustomerOne.class);
      entityManager.unwrap(Session.class).disableFilter(FilterName.LANGUAGE);
      projectDto.setFavorites(true);
      return new ResponseEntity<>(projectDto, HttpStatus.OK);
    } catch (NullPointerException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping(value = "/favoriteProject/{project}")
  @ResponseBody
  public ResponseEntity deleteFavoriteProjefct(
      @RequestParam("customer") Customer customer, @PathVariable Project project) {
    try {
      customer.getFavoriteProject().remove(project);
      this.baseEntityService.update(customer);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NullPointerException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping(value = "/customerProfail")
  @ResponseBody
  public ResponseEntity<CustomerDtoFull> updateCustomer(@RequestBody UserProfail userProfail) {
    Customer customer = baseEntityService.findById(userProfail.getId());
    if (customer != null) {
      customer.setSurName(userProfail.getUsername());
      customer.setHashPassword(userProfail.getNewPassword());
      customer.setEmail(userProfail.getEmail());
      baseEntityService.update(customer);
      CustomerDtoFull customerDto = this.modelMapper.map(customer, CustomerDtoFull.class);
      return new ResponseEntity<>(customerDto, HttpStatus.OK);
    } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
