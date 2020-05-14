package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewContact;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Contact extends BaseEntity {

  @JsonView(ViewContact.BaseClass.class)
  @Column
  private String note;

  @OneToOne
  @JoinColumn(name = "position")
  @JsonView(ViewContact.BaseClass.class)
  private Position position;

  @OneToOne
  @JoinColumn(name = "users")
  @JsonView(ViewContact.BaseClass.class)
  private UserPortal userPortal;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append(super.toString());
    sb.append(", \"note\" : \"").append(note).append('\"');
    sb.append(", \"position\" : \"").append(position.getId()).append('\"');
    sb.append(", \"userPortal\" : ").append(userPortal.getId());
    sb.append('}');
    return sb.toString();
  }
}
