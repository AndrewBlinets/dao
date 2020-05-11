package by.ipps.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "typeachievement")
@Data
public class TypeAchievement extends BaseEntity implements Serializable {
  @Column private String name;

  @JsonIgnore
  @OneToMany(mappedBy = "typeAchievement")
  private List<Achievement> achievements;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append(super.toString());
    sb.append(", \"name\" : \"").append(name).append('\"');
    sb.append(", \"achievements\" : \"").append(achievements);
    sb.append("\"}");
    return sb.toString();
  }
}
