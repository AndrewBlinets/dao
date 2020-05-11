package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewContact;
import by.ipps.dao.utils.view.ViewDepartment;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Department extends BaseEntity implements Serializable {

  @JsonView({ViewContact.BaseClass.class, ViewDepartment.BaseClassDepartment.class})
  @Column
  private String name;

  @JsonView({ViewDepartment.BaseClassDepartment.class})
  @Column
  private String code;

  @JsonView({ViewContact.BaseClass.class})
  @ManyToMany(mappedBy = "departments")
  private List<UserPortal> users;

  @JsonView({ViewDepartment.FullInformationClassDepartment.class})
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "leader")
  private UserPortal leader;

  @ManyToMany(mappedBy = "departments")
  private List<Position> positions;

  @JsonView(ViewDepartment.BaseClassDepartment.class)
  @ManyToOne
  @JoinColumn(name = "page_id")
  private Sheet sheet;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append(super.toString());
    sb.append(",\"name\" : \"").append(name).append('\"');
    sb.append(", \"code\" : \"").append(code).append('\"');
    sb.append(", \"users\": [");
    if (users != null) {
      for (UserPortal user : users) {
        sb.append(" {\"id\" : \"").append(user.getId()).append("\"},");
      }
    }
    sb.append("]");
    sb.append(", \"leader\" : \"").append(leader != null ? leader.getId() : 0).append("\"");
    sb.append(", \"positions\" : [");
    if (positions != null) {
      for (Position position : positions) {
        sb.append("{\"position\" :").append(position.getId()).append("\"},");
      }
    }
    sb.append("]");
    sb.append(", \"page\" :\"").append(sheet != null ? sheet.getId() : 0).append("\"");
    sb.append('}');
    return sb.toString();
  }
}
