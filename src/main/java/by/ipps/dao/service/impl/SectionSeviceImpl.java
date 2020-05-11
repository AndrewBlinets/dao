package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Section;
import by.ipps.dao.repository.SectionRepository;
import by.ipps.dao.service.SectionService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionSeviceImpl extends BaseEntityServiceImpl<Section, SectionRepository>
    implements SectionService {
  public SectionSeviceImpl(SectionRepository repository) {
    super(repository);
  }

  @Override
  public List<Section> findByIdPage(long id) {
    return null; // repository.findSectionByIdPage(id);
  }
}
