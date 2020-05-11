package by.ipps.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Vacancy extends BaseEntity implements Serializable {
  @Column private String name;

  @Column private String content;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append(super.toString());
    sb.append(", \"name\" : \"").append(name).append('\"');
    sb.append(", \"content\" : \"").append(content).append('\"');
    sb.append('}');
    return sb.toString();
  }
}
