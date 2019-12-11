package by.ipps.dao.controller.base;

import by.ipps.dao.entity.BaseEntity;
import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.Section;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


public interface BaseEntityController<T extends BaseEntity> {

    @PostMapping
    ResponseEntity<T> create(@RequestBody @Valid T entity);

    @GetMapping(value = "/{id}")
    ResponseEntity<T> get(@PathVariable Long id,
                          @RequestParam(value = "language", required = false, defaultValue = "ru") String language,
                          @RequestParam(value = "section", required = false, defaultValue = "1") Section section,
                          @RequestParam(value = "department", required = false) Department department);

    @PutMapping
    ResponseEntity<T> update(@RequestBody @Valid T entity);

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Boolean> remove(@PathVariable Long id);


    @GetMapping
    ResponseEntity<Page<T>> getAll(
            @PageableDefault()
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC),
            }) Pageable pageable,
            @RequestParam(value = "language", required = false, defaultValue = "ru") String language);

    @GetMapping(value = "/all")
    @ResponseBody
    ResponseEntity<List<T>> getAll();
}
