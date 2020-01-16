package by.ipps.dao.entity;

import by.ipps.dao.utils.constant.FilterName;
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
  private Date datePublic;

  @OneToMany(fetch = FetchType.LAZY)
  @Filter(name = FilterName.LANGUAGE)
  private List<NewsLanguageVersion> languageVersions;

  @OneToOne
  @JoinColumn(name = "mainImge")
  private FileManager mainImage;

  @Column private int countView;

  @Column private String status;

  @ManyToOne
  @JoinColumn(
      name = "departament_id",
      referencedColumnName = "id",
      insertable = false,
      updatable = false)
  private Department department;

  @ManyToOne
  @JoinColumn(
      name = "section_id",
      referencedColumnName = "id",
      insertable = false,
      updatable = false)
  private Section section;
}
