package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Department;
import by.ipps.dao.repository.DepartmentRepository;
import by.ipps.dao.service.DepartmentService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends BaseEntityServiceImpl<Department, DepartmentRepository>
        implements DepartmentService {

    public DepartmentServiceImpl(DepartmentRepository repository) {
        super(repository);
    }
}
