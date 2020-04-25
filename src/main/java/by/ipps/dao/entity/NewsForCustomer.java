package by.ipps.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class NewsForCustomer extends BaseEntity {

  @Column(length = 500)
  private String title;

  @Column(length = 10000)
  private String content;

  @Column(length = 500)
  private String shortTitle;

  @Column(length = 2000)
  private String entrySpeech;

  @OneToOne
  @JoinColumn(name = "mainImge")
  private FileManager mainImage;

  @ManyToOne
  @JoinColumn(name = "project_id", referencedColumnName = "id")
  private Project project;
}
