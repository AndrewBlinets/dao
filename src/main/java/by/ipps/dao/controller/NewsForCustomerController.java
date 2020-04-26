package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.NewsForCustomer;
import by.ipps.dao.entity.Project;
import by.ipps.dao.entity.Sheet;
import by.ipps.dao.service.NewsForCustomerService;
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
@RequestMapping("/newForCustomer")
public class NewsForCustomerController
    extends BaseEntityAbstractController<NewsForCustomer, NewsForCustomerService>
    implements BaseEntityController<NewsForCustomer> {

  protected NewsForCustomerController(NewsForCustomerService newsForCustomerService) {
    super(newsForCustomerService);
  }

  @Override
  public ResponseEntity<Page<NewsForCustomer>> getAll(
      Pageable pageable, String language, Sheet sheet, Department department) {
    return new ResponseEntity<>(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
  }

  @Override
  public ResponseEntity<List<NewsForCustomer>> getAll(Sheet sheet, Department department) {
    return new ResponseEntity<>(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
  }

  @GetMapping("/byIdProjectPage/{project}")
  @ResponseBody
  public ResponseEntity<Page<NewsForCustomer>> getNewsByProiject(
      @PageableDefault()
          @SortDefault.SortDefaults({
            @SortDefault(sort = "id", direction = Sort.Direction.ASC),
          })
          Pageable pageable,
      @PathVariable Project project) {
    Page<NewsForCustomer> newsForCustomerPage =
        this.baseEntityService.findAllByProject(project, pageable);
    return new ResponseEntity<>(
        newsForCustomerPage, newsForCustomerPage != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/byIdProject/{project}")
  @ResponseBody
  public ResponseEntity<List<NewsForCustomer>> getNewsByProiject(@PathVariable Project project) {
    List<NewsForCustomer> newsForCustomers = this.baseEntityService.findAllByProject(project);
    return new ResponseEntity<>(
        newsForCustomers, newsForCustomers != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
  }
}
