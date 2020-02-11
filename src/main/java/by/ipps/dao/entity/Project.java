package by.ipps.dao.entity;

import by.ipps.dao.utils.constant.FilterName;
import lombok.*;
import org.hibernate.annotations.Filter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Project extends BaseEntity implements Serializable {

  @OneToMany(fetch = FetchType.LAZY)
  @Filter(name = FilterName.LANGUAGE)
  private List<ProjectLanguageVersion> languageVersions;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "mainImage")
  private FileManager mainImage;

  @OneToMany(fetch = FetchType.LAZY)
  private List<FileManager> images;

  @ManyToOne
  @JoinColumn(name = "departament_id", referencedColumnName = "id")
  private Department department;

  @ManyToOne
  @JoinColumn(name = "section_id", referencedColumnName = "id")
  private Section section;

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("{");
    sb.append(super.toString());
    sb.append(", \"images\" : [");
    for (FileManager fileManager : images) {
      sb.append("{\"id\":").append(fileManager.getId()).append("},");
    }
    sb.append("],");
    sb.append(" \"languageVersions\" : [");
    for (ProjectLanguageVersion projectLanguageVersion : languageVersions) {
      sb.append("{\"id\":").append(projectLanguageVersion.getId()).append("},");
    }
    sb.append("]");
    sb.append(", \"mainImage\" : ").append(mainImage != null ? mainImage.getId() : 0);
    sb.append(", \"department\" : ").append(department != null ? department.getId() : 0);
    sb.append(", \"section\" : ").append(section != null ? section.getId() : 0);
    sb.append('}');
    return sb.toString();
  }
}
