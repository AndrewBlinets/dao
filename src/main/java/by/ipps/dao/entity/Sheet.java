package by.ipps.dao.entity;

import by.ipps.dao.utils.constant.FilterName;
import by.ipps.dao.utils.view.ViewDocumentForCustomer;
import by.ipps.dao.utils.view.ViewPage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "page")
public class Sheet extends BaseEntity implements Serializable {

  @JsonView({ViewPage.AdminClass.class, ViewDocumentForCustomer.FileClass.class})
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @Filter(name = FilterName.LANGUAGE)
  private List<PageLanguageVersion> languageVersions;

  @Column
  @JsonView(ViewPage.AdminClass.class)
  private int code;

  @OneToMany(mappedBy = "sheet")
  private List<Department> departments;

  @OneToMany(mappedBy = "sheet")
  private List<DocumentForCustomer> documentForCustomers;

  @OneToMany(mappedBy = "sheet")
  private List<NewsForCustomer> newsForCustomers;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonView(ViewPage.AdminClass.class)
  @Filter(name = FilterName.STATUS)
  @Where(clause = "statusr = 'A'")
  @OrderBy("index asc")
  private List<Section> sections;

  @Column
  @JsonView({ViewPage.AdminClass.class})
  private int status;

  @Column
  @JsonView({ViewPage.AdminClass.class})
  private boolean showForClientInBar;

  @ManyToMany(mappedBy = "sheets")
  @JsonIgnore
  private List<Customer> customers;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append(super.toString());
    sb.append(", \"code\" : \"").append(code).append('\"');
    sb.append(", \"status\" : \"").append(status).append('\"');
    sb.append(", \"showForClientInBar\" : \"").append(showForClientInBar).append('\"');
    sb.append(", \"departments\":[");
    if (departments != null) {
      for (Department department : departments) {
        sb.append("{\"department\" :").append(department.getId()).append("\"},");
      }
    }
    if (sections != null) {
      for (Section section : sections) {
        sb.append("{\"department\" :").append(section.getId()).append("\"},");
      }
    }
    sb.append("]}");
    return sb.toString();
  }
}
