package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewContact;
import by.ipps.dao.utils.view.ViewCustomer;
import by.ipps.dao.utils.view.ViewDepartment;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity implements Serializable {

  @Column(nullable = false, length = 60)
  protected String login;

  @Column(nullable = false)
  protected String hashPassword;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  protected Date dateLastChangePassword;

  @Column(nullable = false, length = 60)
  @JsonView({
    ViewDepartment.FullInformationClassDepartment.class,
    ViewCustomer.BaseClass.class
  })
  protected String name;

  @JsonView({
    ViewDepartment.FullInformationClassDepartment.class,
    ViewCustomer.BaseClass.class
  })
  @Column(nullable = false, length = 60)
  protected String surName;

  @JsonView({
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

  @ManyToOne
  @JoinColumn(name = "org_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Org org;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(
      name = "role_customer",
      joinColumns = @JoinColumn(name = "id_customer", nullable = false, updatable = false),
      inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)})
  private Set<Role> roles;

  @ManyToMany
  @JoinTable(
      name = "customer_projects",
      joinColumns = @JoinColumn(name = "customer_id", nullable = false, updatable = false),
      inverseJoinColumns = {@JoinColumn(name = "projects_id", nullable = false, updatable = false)})
  @Where(clause = "statusr = 'A' and public_for_customer = true")
  @OrderBy("id")
  private List<Project> projects;

  @ManyToMany
  @JoinTable(
      name = "customer_favoriteProject",
      joinColumns = @JoinColumn(name = "customer_id", nullable = false, updatable = false),
      inverseJoinColumns = {@JoinColumn(name = "projects_id", nullable = false, updatable = false)})
  @Where(clause = "statusr = 'A' and public_for_customer = true")
  private List<Project> favoriteProject;

  @ManyToMany
  @JoinTable(
      name = "customer_sheet",
      joinColumns = @JoinColumn(name = "customer_id", nullable = false, updatable = false),
      inverseJoinColumns = {@JoinColumn(name = "sheet_id", nullable = false, updatable = false)})
  @Where(clause = "statusr = 'A'")
  private List<Sheet> sheets;
}
