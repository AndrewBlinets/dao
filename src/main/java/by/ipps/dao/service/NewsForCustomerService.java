package by.ipps.dao.service;

import by.ipps.dao.entity.NewsForCustomer;
import by.ipps.dao.entity.Project;
import by.ipps.dao.service.base.BaseEntityService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface NewsForCustomerService extends BaseEntityService<NewsForCustomer> {

  Page<NewsForCustomer> findAllByProject(Project project, Pageable pageable);

  List<NewsForCustomer> findAllByProject(Project project);
}
