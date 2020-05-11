package by.ipps.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

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

  @ManyToOne
  @JoinColumn(name = "sheet_id", referencedColumnName = "id")
  private Sheet sheet;
}
