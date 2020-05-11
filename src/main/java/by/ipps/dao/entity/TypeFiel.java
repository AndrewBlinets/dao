package by.ipps.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "typefiel")
@Data
public class TypeFiel extends BaseEntity {
  @Column(length = 6)
  private String expansion;

  @Column private String typeDoc;
  @Column private String mine;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append(super.toString());
    sb.append(", \"expansion\" : \"").append(expansion).append('\"');
    sb.append(", \"typeDoc\" : \"").append(typeDoc).append('\"');
    sb.append(", \"mine\" : \"").append(mine).append('\"');
    sb.append('}');
    return sb.toString();
  }
}
