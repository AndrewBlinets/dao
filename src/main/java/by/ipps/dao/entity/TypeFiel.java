package by.ipps.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeFiel extends BaseEntity {
  @Column(length = 6)
  private String expansion;

  @Column private String typeDoc;
  @Column private String mine;

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("{");
    sb.append(super.toString());
    sb.append(", \"expansion\" : \"").append(expansion).append('\"');
    sb.append(", \"typeDoc\" : \"").append(typeDoc).append('\"');
    sb.append(", \"mine\" : \"").append(mine).append('\"');
    sb.append('}');
    return sb.toString();
  }
}
