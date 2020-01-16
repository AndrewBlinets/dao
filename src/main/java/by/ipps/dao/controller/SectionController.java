package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.Section;
import by.ipps.dao.service.SectionService;
import by.ipps.dao.utils.view.ViewSection;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/section")
public class SectionController extends BaseEntityAbstractController<Section, SectionService>
    implements BaseEntityController<Section> {

  protected SectionController(SectionService sectionService) {
    super(sectionService);
  }

  @JsonView(ViewSection.BaseClassSection.class)
  @Override
  public ResponseEntity<Section> create(@Valid Section create) {
    return super.create(create);
  }

  @JsonView(ViewSection.BaseClassSection.class)
  @Override
  public ResponseEntity<Section> get(
      Long id, String language, Section section, Department department) {
    return super.get(id, language, section, department);
  }

  @JsonView(ViewSection.BaseClassSection.class)
  @Override
  public ResponseEntity<Section> update(@Valid Section entity) {
    return super.update(entity);
  }

  @Override
  @JsonView(ViewSection.BaseClassSection.class)
  public ResponseEntity<Page<Section>> getAll(
      Pageable pageable, String language, Section section, Department department) {
    return super.getAll(pageable, language, section, department);
  }

  @Override
  @JsonView(ViewSection.BaseClassSection.class)
  public ResponseEntity<List<Section>> getAll(Section section, Department department) {
    return super.getAll(section, department);
  }
}
