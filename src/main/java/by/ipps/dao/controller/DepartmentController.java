package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.custom.CustomPage;
import by.ipps.dao.dto.department.DepartmentDTO;
import by.ipps.dao.entity.Department;
import by.ipps.dao.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/department")
public class DepartmentController extends BaseEntityAbstractController<Department, DepartmentService>
        implements BaseEntityController<Department> {

    private ModelMapper modelMapper;

    protected DepartmentController(DepartmentService departmentService, ModelMapper modelMapper) {
        super(departmentService);
        this.modelMapper = modelMapper;
    }


    @GetMapping(value = "/admin/{id}")
    @ResponseBody
    public ResponseEntity<DepartmentDTO> get(@PathVariable Long id) {
        Department entity = baseEntityServuce.findById(id);
        if (entity != null) {
            return new ResponseEntity<>(modelMapper.map(entity, DepartmentDTO.class), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    @GetMapping(value = "/admin")
    @ResponseBody
    public ResponseEntity<CustomPage<DepartmentDTO>> getAll(
            @PageableDefault()
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC),
            }) Pageable pageable) {
        Page<Department> ts = baseEntityServuce.findPagingRecords(pageable);
        if (ts != null) {
            java.lang.reflect.Type targetListType = new TypeToken<CustomPage<DepartmentDTO>>() {
            }.getType();
            CustomPage<DepartmentDTO> customPage = modelMapper.map(ts, targetListType);
            return new ResponseEntity<>(customPage, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/admin")
    @ResponseBody
    @Transactional
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid Department department) {
        Department saved = baseEntityServuce.create(department);
        if (saved != null) {
            return new ResponseEntity<>(modelMapper.map(saved, DepartmentDTO.class), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/admin")
    @ResponseBody
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody@Valid Department entity) {
        Department saved = baseEntityServuce.update(entity);
        if (saved != null) {
            return new ResponseEntity<>(modelMapper.map(saved, DepartmentDTO.class), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/admin/{id}")
    @ResponseBody
    public ResponseEntity<Boolean> removeDepartment(@PathVariable Long id) {
        boolean flag = baseEntityServuce.delete(baseEntityServuce.findById(id));
        return new ResponseEntity<>(flag ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

}
