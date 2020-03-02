package by.ipps.dao.service;

import by.ipps.dao.entity.PageWithSection;
import by.ipps.dao.service.base.BaseEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageService extends BaseEntityService<PageWithSection> {
  List<PageWithSection> findAllForClient();

  PageWithSection findByIdForClient(long id);
}
