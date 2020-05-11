package by.ipps.dao.entity;

import by.ipps.dao.utils.constant.FilterName;
import by.ipps.dao.utils.view.ViewDocumentForCustomer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Project extends BaseEntity implements Serializable {

  @Column
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date datePublic;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @Filter(name = FilterName.LANGUAGE)
  @JsonView({ViewDocumentForCustomer.FileClass.class})
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
  private Sheet page;

  @Column private int status;

  @Column(name = "public_for_customer")
  private boolean publicForCustomer;

  @OneToMany(fetch = FetchType.LAZY)
  private List<DocumentForCustomer> images;

  @ManyToMany(mappedBy = "projects")
  @JsonIgnore
  private List<Customer> customers;

  @OneToMany(mappedBy = "project")
  @JsonIgnore
  private List<DocumentForCustomer> documentForCustomers;

  @OneToMany(mappedBy = "project")
  @JsonIgnore
  private List<NewsForCustomer> newsForCustomers;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
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
