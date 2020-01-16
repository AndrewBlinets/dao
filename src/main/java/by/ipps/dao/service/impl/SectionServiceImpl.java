package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Section;
import by.ipps.dao.repository.SectionRepository;
import by.ipps.dao.service.SectionService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl extends BaseEntityServiceImpl<Section, SectionRepository>
    implements SectionService {
  public SectionServiceImpl(SectionRepository repository) {
    super(repository);
  }
}
