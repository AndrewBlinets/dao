package by.ipps.dao.repository;

import by.ipps.dao.entity.PageWithSection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends BaseEntityRepository<PageWithSection> {

  List<PageWithSection> findByStatusRAndStatusAndShowForClientInBar(String a, int i, boolean b);
}
