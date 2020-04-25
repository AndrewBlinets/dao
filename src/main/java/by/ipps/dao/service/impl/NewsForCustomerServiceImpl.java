package by.ipps.dao.service.impl;

import by.ipps.dao.entity.NewsForCustomer;
import by.ipps.dao.service.NewsForCustomerRepository;
import by.ipps.dao.service.NewsForCustomerService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class NewsForCustomerServiceImpl extends BaseEntityServiceImpl<NewsForCustomer, NewsForCustomerRepository>
    implements NewsForCustomerService {

  public NewsForCustomerServiceImpl(NewsForCustomerRepository repository) {
    super(repository);
  }
}
