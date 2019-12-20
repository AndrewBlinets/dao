package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Contact;
import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.Section;
import by.ipps.dao.repository.ContactRepository;
import by.ipps.dao.service.ContactService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl extends BaseEntityServiceImpl<Contact, ContactRepository> implements ContactService {
    public ContactServiceImpl(ContactRepository repository) {
        super(repository);
    }
}
