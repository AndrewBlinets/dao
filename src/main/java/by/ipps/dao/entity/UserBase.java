package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewContact;
import by.ipps.dao.utils.view.ViewCustomer;
import by.ipps.dao.utils.view.ViewDepartment;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public class UserBase extends BaseEntity {

  @Column(nullable = false, length = 60)
  protected String login;

  @Column(nullable = false)
  protected String hashPassword;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  protected Date dateLastChangePassword;

  @Column(nullable = false, length = 60)
  @JsonView({
    ViewContact.BaseClass.class,
    ViewDepartment.FullInformationClassDepartment.class,
    ViewCustomer.BaseClass.class
  })
  protected String name;

  @JsonView({
    ViewContact.BaseClass.class,
    ViewDepartment.FullInformationClassDepartment.class,
    ViewCustomer.BaseClass.class
  })
  @Column(nullable = false, length = 60)
  protected String surName;

  @JsonView({
    ViewContact.BaseClass.class,
    ViewDepartment.FullInformationClassDepartment.class,
    ViewCustomer.BaseClass.class
  })
  @Column(nullable = false, length = 60)
  protected String patronicName;

  @Column protected Boolean enabled;
  @Column protected Boolean block;

  @JsonView(ViewCustomer.BaseClass.class)
  @Column(length = 100)
  protected String email;
}
