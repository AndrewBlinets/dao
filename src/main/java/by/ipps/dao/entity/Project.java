package by.ipps.dao.entity;

import by.ipps.dao.utils.constant.FilterName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Project extends BaseEntity implements Serializable {

  @Column
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date datePublic;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @Filter(name = FilterName.LANGUAGE)
  private List<ProjectLanguageVersion> languageVersions;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "mainImage")
  private FileManager mainImage;

  //  @OneToMany(fetch = FetchType.LAZY)
  //  private List<FileManager> images;

  @ManyToOne
  @JoinColumn(name = "departament_id", referencedColumnName = "id")
  private Department department;

  @ManyToOne
  @JoinColumn(name = "page_id", referencedColumnName = "id")
  private PageWithSection page;

  @Column private int status;

  @Column private boolean publicForCustomer;

  @OneToMany(fetch = FetchType.LAZY)
  private List<DocumentForCustomer> images;

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("{");
    sb.append(super.toString());
    //    sb.append(", \"images\" : [");
    //    for (FileManager fileManager : images) {
    //      sb.append("{\"id\":").append(fileManager.getId()).append("},");
    //    }
    //    sb.append("],");
    sb.append(" \"languageVersions\" : [");
    for (ProjectLanguageVersion projectLanguageVersion : languageVersions) {
      sb.append("{\"id\":").append(projectLanguageVersion.getId()).append("},");
    }
    sb.append("]");
    sb.append(", \"mainImage\" : ").append(mainImage != null ? mainImage.getId() : 0);
    sb.append(", \"department\" : ").append(department != null ? department.getId() : 0);
    sb.append(", \"page\" : ").append(page != null ? page.getId() : 0);
    sb.append('}');
    return sb.toString();
  }
}
