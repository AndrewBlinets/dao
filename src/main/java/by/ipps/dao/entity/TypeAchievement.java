package by.ipps.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeAchievement extends BaseEntity implements Serializable {
  @Column private String name;

  @JsonIgnore
  @OneToMany(mappedBy = "typeAchievement")
  private List<Achievement> achievements;
}
