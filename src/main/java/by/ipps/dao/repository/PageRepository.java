package by.ipps.dao.repository;

import by.ipps.dao.entity.Sheet;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends BaseEntityRepository<Sheet> {

  List<Sheet> findByStatusRAndStatusAndShowForClientInBar(String a, int i, boolean b);
}
