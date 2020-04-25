package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.dto.CustomerDto;
import by.ipps.dao.dto.CustomerDtoFull;
import by.ipps.dao.entity.Customer;
import by.ipps.dao.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseEntityAbstractController<Customer, CustomerService>
    implements BaseEntityController<Customer> {

  public CustomerController(CustomerService customerService) {
    super(customerService);
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
}
