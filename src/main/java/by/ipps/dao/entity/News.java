package by.ipps.dao.entity;

import by.ipps.dao.utils.constant.FilterName;
import by.ipps.dao.utils.view.ViewNews;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class News extends BaseEntity implements Serializable {

  @Column
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonView({ViewNews.AdminNewsClass.class})
  private Date datePublic;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @Filter(name = FilterName.LANGUAGE)
  @JsonView({ViewNews.AdminNewsClass.class})
  private List<NewsLanguageVersion> languageVersions;

  @OneToOne
  @JoinColumn(name = "mainImge")
  @JsonView({ViewNews.AdminNewsClass.class})
  private FileManager mainImage;

  @Column
  @JsonView({ViewNews.AdminNewsClass.class})
  private int countView;

  @Column
  @JsonView({ViewNews.AdminNewsClass.class})
  private String status;

  @ManyToOne
  @JoinColumn(
      name = "departament_id",
      referencedColumnName = "id")
  @JsonView({ViewNews.AdminNewsClass.class})
  private Department department;

  @ManyToOne
  @JoinColumn(
      name = "section_id",
      referencedColumnName = "id")
  @JsonView({ViewNews.AdminNewsClass.class})
  private Section section;

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("{");
    sb.append(super.toString());
    sb.append(", \"datePublic\" : ").append(datePublic);
    sb.append(", \"languageVersions\" :[");
            for (NewsLanguageVersion languageVersion : languageVersions) {
                sb.append("{\"id\" : ").append(languageVersion.getId()).append("},");
            }
    sb.append("], \"mainImage\" :\"").append(mainImage != null ? mainImage.getId() : 0);
    sb.append(", \"countView\" :").append(countView);
    sb.append(", \"status\" :\"").append(status).append('\"');
    sb.append(", \"department\" : ").append(department != null ? department.getId() : 0);
    sb.append(", \"section\" : ").append(section != null ? section.getId() : 0);
    sb.append('}');
    return sb.toString();
  }
}
