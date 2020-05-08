package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewContact;
import by.ipps.dao.utils.view.ViewCustomer;
import by.ipps.dao.utils.view.ViewDepartment;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserPortal extends BaseEntity implements Serializable {

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

  @JsonView(ViewContact.BaseClass.class)
  @Column
  private String phone;

  @JsonView(ViewContact.BaseClass.class)
  @Column(length = 3)
  private String room;

  @ManyToMany
  @JoinTable(
      name = "department_user",
      joinColumns = @JoinColumn(name = "id_user", nullable = false, updatable = false),
      inverseJoinColumns = {
        @JoinColumn(name = "department_id", nullable = false, updatable = false)
      })
  private Set<Department> departments;

  @JsonView(ViewContact.BaseClass.class)
  @ManyToMany
  @JoinTable(
      name = "position_user",
      joinColumns = @JoinColumn(name = "id_user", nullable = false, updatable = false),
      inverseJoinColumns = {@JoinColumn(name = "position_id", nullable = false, updatable = false)})
  //    @Where(clause = "statusR = 'A' and position.id = ")
  // todo fix many position for contact
  private Set<Position> positions;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(
      name = "role_user",
      joinColumns = @JoinColumn(name = "id_user", nullable = false, updatable = false),
      inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)})
  private Set<Role> roles;

  @OneToMany(mappedBy = "user")
  private List<Logger> logers;

  @JsonView(ViewContact.BaseClass.class)
  @OneToOne(mappedBy = "userPortal")
  private Contact contact;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append(super.toString());
    sb.append(", \"login\" : \"").append(login).append('\"');
    sb.append(", \"hashPassword\" : \"").append(hashPassword).append('\"');
    sb.append(", \"dateLastChangePassword\" : \"").append(dateLastChangePassword).append('\"');
    sb.append(", \"name\" : \"").append(name).append('\"');
    sb.append(", \"surName\" : \"").append(surName).append('\"');
    sb.append(", \"patronicName\" : \"").append(patronicName).append('\"');
    sb.append(", \"email\" : \"").append(email).append('\"');
    sb.append(", \"phone\" : \"").append(phone).append('\"');
    sb.append(", \"room\" : \"").append(room).append('\"');
    sb.append(", \"enabled\" : \"").append(enabled).append('\"');
    sb.append(", \"block\" : \"").append(block).append('\"');
    sb.append(", \"departments\": [");
    if (departments != null) {
      for (Department department : departments) {
        sb.append(" {\"id\" : \"").append(department.getId()).append("\"},");
      }
    }
    sb.append("]");
    sb.append(", \"positions\": [");
    if (positions != null) {
      for (Position position : positions) {
        sb.append(" {\"id\" : \"").append(position.getId()).append("\"},");
      }
    }
    sb.append("]");
    sb.append(", \"users\": [");
    if (roles != null) {
      for (Role role : roles) {
        sb.append(" {\"id\" : \"").append(role.getId()).append("\"},");
      }
    }
    sb.append("]");
    sb.append(", \"contact\": \"").append(contact != null ? contact.getId() : 0).append("\"");
    sb.append('}');
    return sb.toString();
  }
}
