package by.ipps.dao.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Partners extends BaseEntity implements Serializable {

  @Column private String name;

  @OneToOne private FileManager image;

  @Column private String url;

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("{");
    sb.append(super.toString());
    sb.append(", \"name\" : \"").append(name).append('\"');
    sb.append(", \"image\" : ").append(image.getId());
    sb.append(", \"url\" : \"").append(url).append('\"');
    sb.append('}');
    return sb.toString();
  }
}
