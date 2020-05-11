package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewCustomer;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Org extends BaseEntity implements Serializable {

  @JsonView(ViewCustomer.BaseClass.class)
  @Column(length = 12)
  private String unp;

  @JsonView(ViewCustomer.BaseClass.class)
  @Column(length = 1000)
  private String name;

  @JsonView(ViewCustomer.BaseClass.class)
  @Column(length = 100)
  private String shortName;

  @JsonView(ViewCustomer.BaseClass.class)
  @Column
  private String officialSite;

  @JsonView(ViewCustomer.BaseClass.class)
  @Column(length = 1000)
  private String addr;

  @JsonView(ViewCustomer.BaseClass.class)
  @Column(length = 100)
  private String email;
}
