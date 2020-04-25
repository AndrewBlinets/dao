package by.ipps.dao.service;

import by.ipps.dao.entity.Sheet;
import by.ipps.dao.service.base.BaseEntityService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface PageService extends BaseEntityService<Sheet> {
  List<Sheet> findAllForClient();

  Sheet findByIdForClient(long id);
}
