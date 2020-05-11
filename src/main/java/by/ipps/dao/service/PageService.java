package by.ipps.dao.service;

import by.ipps.dao.entity.Sheet;
import by.ipps.dao.service.base.BaseEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PageService extends BaseEntityService<Sheet> {
  List<Sheet> findAllForClient();

  Sheet findByIdForClient(long id);
}
