package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.Section;
import by.ipps.dao.service.DepartmentService;
import by.ipps.dao.utils.view.ViewContact;
import by.ipps.dao.utils.view.ViewDepartment;
import com.fasterxml.jackson.annotation.JsonView;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController extends BaseEntityAbstractController<Department, DepartmentService>
        implements BaseEntityController<Department> {

    private ModelMapper modelMapper;

    protected DepartmentController(DepartmentService departmentService, ModelMapper modelMapper) {
        super(departmentService);
        this.modelMapper = modelMapper;
    }

//    @Override
//    public ResponseEntity<Department> create(@Valid Department entity) {
//        return null;
//    }
//
    @JsonView(ViewDepartment.BaseClassDepartment.class)
    @Override
    public ResponseEntity<Department> get(Long id, String language, Section section, Department department) {
        Department entity = baseEntityServuce.findById(id);
        return new ResponseEntity<>(entity, entity != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

//    @Override
//    public ResponseEntity<Department> update(@Valid Department entity) {
//        return null;
//    }

//    @Override
//    public ResponseEntity<Boolean> remove(Long id) {
//        return null;
//    }


    @Override
    public ResponseEntity<Page<Department>> getAll(Pageable pageable, String language, Section section, Department department) {
        return super.getAll(pageable, language, section, department);
        //        return new ResponseEntity<>(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
    }

    @Override
    @JsonView(ViewDepartment.BaseClassDepartment.class)
    public ResponseEntity<List<Department>> getAll(Section section, Department department) {
        return super.getAll(section, department);
    }


//    @GetMapping(value = "/admin/{id}")
//    @ResponseBody
//    public ResponseEntity<DepartmentDTO> get(@PathVariable Long id) {
//        Department entity = baseEntityServuce.findById(id, section, department);
//        if (entity != null) {
//            return new ResponseEntity<>(modelMapper.map(entity, DepartmentDTO.class), HttpStatus.OK);
//        } else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @Transactional
//    @GetMapping(value = "/admin")
//    @ResponseBody
//    public ResponseEntity<CustomPage<DepartmentDTO>> getAll(
//            @PageableDefault()
//            @SortDefault.SortDefaults({
//                    @SortDefault(sort = "id", direction = Sort.Direction.ASC),
//            }) Pageable pageable) {
//        Page<Department> ts = baseEntityServuce.findPagingRecords(section, department, pageable);
//        if (ts != null) {
//            java.lang.reflect.Type targetListType = new TypeToken<CustomPage<DepartmentDTO>>() {
//            }.getType();
//            CustomPage<DepartmentDTO> customPage = modelMapper.map(ts, targetListType);
//            return new ResponseEntity<>(customPage, HttpStatus.OK);
//        } else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PostMapping(value = "/admin")
//    @ResponseBody
//    @Transactional
//    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid Department department) {
//        Department saved = baseEntityServuce.create(department);
//        if (saved != null) {
//            return new ResponseEntity<>(modelMapper.map(saved, DepartmentDTO.class), HttpStatus.OK);
//        } else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PutMapping(value = "/admin")
//    @ResponseBody
//    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody@Valid Department entity) {
//        Department saved = baseEntityServuce.update(entity);
//        if (saved != null) {
//            return new ResponseEntity<>(modelMapper.map(saved, DepartmentDTO.class), HttpStatus.OK);
//        } else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping(value = "/admin/{id}")
//    @ResponseBody
//    public ResponseEntity<Boolean> removeDepartment(@PathVariable Long id) {
//        boolean flag = baseEntityServuce.delete(baseEntityServuce.findById(id, section, department));
//        return new ResponseEntity<>(flag ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
//    }

}
