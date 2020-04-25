package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewContact;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Contact extends BaseEntity {

  @JsonView(ViewContact.BaseClass.class)
  @Column
  private String note;

  @OneToOne
  @JoinColumn(name = "users")
  private UserPortal userPortal;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append(super.toString());
    sb.append(", \"note\" : \"").append(note).append('\"');
    sb.append(", \"userPortal\" : ").append(userPortal.getId());
    sb.append('}');
    return sb.toString();
  }
}
