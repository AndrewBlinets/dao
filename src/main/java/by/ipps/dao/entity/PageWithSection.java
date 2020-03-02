package by.ipps.dao.entity;

import by.ipps.dao.utils.constant.FilterName;
import by.ipps.dao.utils.view.ViewPage;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "page")
public class PageWithSection extends BaseEntity implements Serializable {

  @JsonView(ViewPage.AdminClass.class)
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @Filter(name = FilterName.LANGUAGE)
  private List<PageLanguageVersion> languageVersions;

  @Column
  @JsonView(ViewPage.AdminClass.class)
  private int code;

  @OneToMany(mappedBy = "pageWithSection")
  private List<Department> departments;

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

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("{");
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
