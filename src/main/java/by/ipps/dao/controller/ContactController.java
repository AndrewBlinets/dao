package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.entity.Contact;
import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.Sheet;
import by.ipps.dao.entity.UserPortal;
import by.ipps.dao.service.ContactService;
import by.ipps.dao.utils.view.ViewContact;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController  extends BaseEntityAbstractController<Contact, ContactService>
        implements BaseEntityController<Contact> {


  protected ContactController(ContactService contactService) {
    super(contactService);
  }

  @Override
  @JsonView(ViewContact.BaseClass.class)
  public ResponseEntity<Contact> get(Long id, String language, Sheet sheet, Department department) {
    return super.get(id, language, sheet, department);
  }

  @Override
  public ResponseEntity<Contact> create(Contact entity, UserPortal userPortal) {
    return super.create(entity, userPortal);
  }

  @Override
  @JsonView(ViewContact.BaseClass.class)
  public ResponseEntity<Contact> update(Contact entity, UserPortal userPortal) {
    return super.update(entity, userPortal);
  }

  @Override
  public ResponseEntity<Boolean> remove(Contact id, UserPortal userPortal) {
    return super.remove(id, userPortal);
  }

  @Override
  @JsonView(ViewContact.BaseClass.class)
  public ResponseEntity<Page<Contact>> getAll(Pageable pageable, String language, Sheet sheet, Department department) {
    return super.getAll(pageable, language, sheet, department);
  }

  @Override
  @JsonView(ViewContact.BaseClass.class)
  public ResponseEntity<List<Contact>> getAll(Sheet sheet, Department department) {
    return super.getAll(sheet, department);
  }
}
