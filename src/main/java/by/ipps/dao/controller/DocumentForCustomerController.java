package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.entity.Customer;
import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.DocumentForCustomer;
import by.ipps.dao.entity.Project;
import by.ipps.dao.entity.Sheet;
import by.ipps.dao.entity.UserPortal;
import by.ipps.dao.service.DocumentForCustomerService;
import by.ipps.dao.utils.view.ViewDocumentForCustomer;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documentForCustomer")
public class DocumentForCustomerController
    extends BaseEntityAbstractController<DocumentForCustomer, DocumentForCustomerService>
    implements BaseEntityController<DocumentForCustomer> {

  protected DocumentForCustomerController(DocumentForCustomerService documentForCustomerService) {
    super(documentForCustomerService);
  }

  @Override
  @JsonView(ViewDocumentForCustomer.BaseClass.class)
  public ResponseEntity<DocumentForCustomer> get(
      Long id, String language, Sheet sheet, Department department) {
    return super.get(id, language, sheet, department);
  }

  @Override
  @JsonView(ViewDocumentForCustomer.FileClass.class)
  public ResponseEntity<DocumentForCustomer> create(
      DocumentForCustomer entity, UserPortal userPortal) {
    return super.create(entity, userPortal);
  }

  @Override
  @JsonView(ViewDocumentForCustomer.FileClass.class)
  public ResponseEntity<DocumentForCustomer> update(
      DocumentForCustomer entity, UserPortal userPortal) {
    return super.update(entity, userPortal);
  }

  @Override
  @JsonView(ViewDocumentForCustomer.FileClass.class)
  public ResponseEntity<Page<DocumentForCustomer>> getAll(
      Pageable pageable, String language, Sheet sheet, Department department) {
    return super.getAll(pageable, language, sheet, department);
  }

  @Override
  @JsonView(ViewDocumentForCustomer.FileClass.class)
  public ResponseEntity<List<DocumentForCustomer>> getAll(Sheet sheet, Department department) {
    return super.getAll(sheet, department);
  }

  @GetMapping("/byCustomerPage/{customer}")
  @ResponseBody
  @JsonView(ViewDocumentForCustomer.FileClass.class)
  public ResponseEntity<Page<DocumentForCustomer>> getPageByIdCustomer(
      @PageableDefault()
          @SortDefault.SortDefaults({
            @SortDefault(sort = "id", direction = Sort.Direction.ASC),
          })
          Pageable pageable,
      @PathVariable Customer customer) {
    if (customer == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    if (!customer.getStatusR().equals("A")) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    Page<DocumentForCustomer> page =
        this.baseEntityService.getDocumentForCustomerByIdCustomer(pageable, customer);
    return page.getContent().isEmpty()
        ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
        : new ResponseEntity<>(page, HttpStatus.OK);
  }

  @GetMapping("/byCustomer/{customer}")
  @ResponseBody
  @JsonView(ViewDocumentForCustomer.FileClass.class)
  public ResponseEntity<List<DocumentForCustomer>> getAllByIdCustomer(
      @PathVariable Customer customer) {
    if (customer == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    List<DocumentForCustomer> documentForCustomers = new ArrayList<>();
    for (Project project : customer.getProjects()) {
      documentForCustomers.addAll(project.getDocumentForCustomers());
    }
    for (Sheet sheet : customer.getSheets()) {
      documentForCustomers.addAll(sheet.getDocumentForCustomers());
    }
    return new ResponseEntity<>(documentForCustomers, HttpStatus.OK);
  }

  @GetMapping("/byProjectPage/{customer}/{project}")
  @ResponseBody
  @JsonView(ViewDocumentForCustomer.FileClass.class)
  public ResponseEntity<Page<DocumentForCustomer>> getPageByIdProject(
      @PageableDefault()
          @SortDefault.SortDefaults({
            @SortDefault(sort = "id", direction = Sort.Direction.ASC),
          })
          Pageable pageable,
      @PathVariable Customer customer,
      @PathVariable Project project) {
    if (customer == null || project == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    if (!customer.getStatusR().equals("A") || !project.getStatusR().equals("A"))
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    Page<DocumentForCustomer> page =
        this.baseEntityService.getDocumentForCustomerByIdProject(pageable, customer, project);
    return page.getContent().isEmpty()
        ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
        : new ResponseEntity<>(page, HttpStatus.OK);
  }

  @GetMapping("/byProject/{customer}/{project}")
  @ResponseBody
  @JsonView(ViewDocumentForCustomer.FileClass.class)
  public ResponseEntity<List<DocumentForCustomer>> getAllByIdProject(
      @PathVariable Customer customer, @PathVariable Project project) {
    if (customer == null || project == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    if (!customer.getStatusR().equals("A") || !project.getStatusR().equals("A"))
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    if (!customer.getProjects().contains(project))
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    List<DocumentForCustomer> documentForCustomers =
        this.baseEntityService.getDocumentForCustomerByIdProject(customer, project);
    return documentForCustomers.isEmpty()
        ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
        : new ResponseEntity<>(documentForCustomers, HttpStatus.OK);
  }

  @GetMapping("/bySheetPage/{customer}/{sheet}")
  @ResponseBody
  @JsonView(ViewDocumentForCustomer.FileClass.class)
  public ResponseEntity<Page<DocumentForCustomer>> getPageByIdSheet(
      @PageableDefault()
          @SortDefault.SortDefaults({
            @SortDefault(sort = "id", direction = Sort.Direction.ASC),
          })
          Pageable pageable,
      @PathVariable Customer customer, @PathVariable Sheet sheet) {
    if (customer == null || sheet == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    if (!customer.getStatusR().equals("A") || !sheet.getStatusR().equals("A"))
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    Page<DocumentForCustomer> page =
        this.baseEntityService.getDocumentForCustomerByIdSheet(pageable, customer, sheet);
    return page.getContent().isEmpty()
        ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
        : new ResponseEntity<>(page, HttpStatus.OK);
  }

  @GetMapping("/bySheet/{customer}/{sheet}")
  @ResponseBody
  @JsonView(ViewDocumentForCustomer.FileClass.class)
  public ResponseEntity<List<DocumentForCustomer>> getAllByIdSheet(
      @PathVariable Customer customer, @PathVariable Sheet sheet) {
    if (customer == null || sheet == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    if (!customer.getStatusR().equals("A") || !sheet.getStatusR().equals("A"))
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    if (!customer.getSheets().contains(sheet))
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    List<DocumentForCustomer> documentForCustomers =
        this.baseEntityService.getDocumentForCustomerByIdSheet(customer, sheet);
    return documentForCustomers.isEmpty()
        ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
        : new ResponseEntity<>(documentForCustomers, HttpStatus.OK);
  }
}
