package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.entity.Contact;
import by.ipps.dao.service.ContactService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactController extends BaseEntityAbstractController<Contact, ContactService>
        implements BaseEntityController<Contact> {
    protected ContactController(ContactService contactService) {
        super(contactService);
    }
}
