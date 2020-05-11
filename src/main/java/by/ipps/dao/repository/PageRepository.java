package by.ipps.dao.repository;

import by.ipps.dao.entity.Sheet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends BaseEntityRepository<Sheet> {

  List<Sheet> findByStatusRAndStatusAndShowForClientInBar(String a, int i, boolean b);
}
