package by.ipps.dao.utils.addFun;

import by.ipps.dao.entity.BaseEntity;
import org.springframework.stereotype.Component;

@Component
public class BaseParams {

  public void setParams(BaseEntity entity, BaseEntity dataBaseVersion) {
    entity.setStatusR(dataBaseVersion.getStatusR());
    entity.setDateChangeStatusR(dataBaseVersion.getDateChangeStatusR());
    entity.setDti(dataBaseVersion.getDti());
  }
}
