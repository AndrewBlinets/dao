package by.ipps.dao.entity;

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
public class Achievement extends BaseEntity implements Serializable {
  @Column private String name;

  @OneToMany(fetch = FetchType.EAGER)
  private List<FileManager> images;

  @ManyToOne
  @JoinColumn(name = "type_id", referencedColumnName = "id", insertable = false, updatable = false)
  private TypeAchievement typeAchievement;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append(super.toString());
    sb.append(", \"name\" : \"").append(name).append('\"');
    sb.append(", \"images\" : [").append(images);
    if (images != null) {
      for (FileManager image : images) {
        sb.append(" {\"id\" : \"").append(image.getId()).append("\"},");
      }
    }
    sb.append("]");
    sb.append(", \"typeAchievement\" : \"").append(typeAchievement);
    sb.append("\"}");
    return sb.toString();
  }
}
