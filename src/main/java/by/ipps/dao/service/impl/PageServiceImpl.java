package by.ipps.dao.service.impl;

import by.ipps.dao.entity.PageWithSection;
import by.ipps.dao.repository.PageRepository;
import by.ipps.dao.service.PageService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageServiceImpl extends BaseEntityServiceImpl<PageWithSection, PageRepository>
    implements PageService {

  private PageRepository repository;

  public PageServiceImpl(PageRepository repository) {
    super(repository);
    this.repository = repository;
  }

  @Override
  public List<PageWithSection> findAllForClient() {
    return repository.findByStatusRAndStatusAndShowForClientInBar("A", 1, true);
  }

  @Override
  public PageWithSection findByIdForClient(long id) {
    return repository.findByIdAndStatusR(id, "A").orElse(null);
  }
}
