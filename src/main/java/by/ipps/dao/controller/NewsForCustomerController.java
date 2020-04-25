package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.entity.NewsForCustomer;
import by.ipps.dao.service.NewsForCustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newForCustomer")
public class NewsForCustomerController extends BaseEntityAbstractController<NewsForCustomer, NewsForCustomerService>
    implements BaseEntityController<NewsForCustomer> {

  protected NewsForCustomerController(
      NewsForCustomerService newsForCustomerService) {
    super(newsForCustomerService);
  }
}
