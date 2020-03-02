package by.ipps.dao.service;

import by.ipps.dao.entity.Section;
import by.ipps.dao.service.base.BaseEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SectionService extends BaseEntityService<Section> {
  List<Section> findByIdPage(long id);
}
