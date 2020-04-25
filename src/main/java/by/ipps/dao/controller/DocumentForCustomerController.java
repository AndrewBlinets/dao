package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.entity.DocumentForCustomer;
import by.ipps.dao.service.DocumentForCustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documentForCustomer")
public class DocumentForCustomerController
    extends BaseEntityAbstractController<DocumentForCustomer, DocumentForCustomerService>
    implements BaseEntityController<DocumentForCustomer> {

  protected DocumentForCustomerController(DocumentForCustomerService documentForCustomerService) {
    super(documentForCustomerService);
  }
}
