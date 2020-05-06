package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.dto.CustomerDto;
import by.ipps.dao.dto.CustomerDtoFull;
import by.ipps.dao.dto.FavoriteProject;
import by.ipps.dao.dto.UserProfail;
import by.ipps.dao.entity.Customer;
import by.ipps.dao.entity.Project;
import by.ipps.dao.service.CustomerService;
import by.ipps.dao.service.ProjectService;
import java.util.List;
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

@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseEntityAbstractController<Customer, CustomerService>
    implements BaseEntityController<Customer> {

  private ProjectService projectService;

  public CustomerController(CustomerService customerService, ProjectService projectService) {
    super(customerService);
    this.projectService = projectService;
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

  //    @JsonView(ViewCustomer.BaseClass.class)
  @PostMapping(value = "/getInfoAboutCustomer")
  @ResponseBody
  public ResponseEntity<CustomerDtoFull> getInfoAboutCustomer(@RequestBody String login) {
    Customer customer = baseEntityService.getCustomerByLogin(login);
    if (customer != null) {
      CustomerDtoFull customerDto = this.modelMapper.map(customer, CustomerDtoFull.class);
      return new ResponseEntity<>(customerDto, HttpStatus.OK);
    } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping(value = "/favoriteProject")
  @ResponseBody
  public ResponseEntity<List<Project>> getFavoriteProject(
      @RequestParam("customer") Customer customer) {
    return customer != null
        ? new ResponseEntity<>(customer.getFavoriteProject(), HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping(value = "/favoriteProject")
  @ResponseBody
  public ResponseEntity<Project> addFavoriteProject(@RequestBody FavoriteProject favoriteProject) {
    try {
      Project project = projectService.findByIdAndPublicForCustomer(favoriteProject.getProject());
      Customer customer = this.baseEntityService.findById(favoriteProject.getCustomer());
      customer.getFavoriteProject().add(project);
      this.baseEntityService.update(customer);
      return new ResponseEntity<>(project, HttpStatus.OK);
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
      return new ResponseEntity<>(project, HttpStatus.OK);
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
