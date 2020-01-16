package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewContact;
import by.ipps.dao.utils.view.ViewDepartment;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

  @JsonView(ViewContact.BaseClass.class)
  @ManyToMany(mappedBy = "departments")
  private Set<UserPortal> users;

  @JsonView(ViewDepartment.FullInformationClassDepartment.class)
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "leader", nullable = true)
  private UserPortal leader;

  @ManyToMany(mappedBy = "departments")
  private List<Position> positions;

  @JsonView(ViewDepartment.BaseClassDepartment.class)
  @ManyToOne
  @JoinColumn(name = "section_id")
  private Section section;

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("{");
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
    sb.append(", \"section\" :\"").append(section != null ? section.getId() : 0).append("\"");
    sb.append('}');
    return sb.toString();
  }
}
