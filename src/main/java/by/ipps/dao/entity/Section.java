package by.ipps.dao.entity;

import by.ipps.dao.utils.constant.FilterName;
import by.ipps.dao.utils.view.ViewPage;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@FilterDef(
    name = FilterName.STATUS,
    defaultCondition = "status = :status and statusR = 'A'",
    parameters = {
      @ParamDef(name = "status", type = "integer"),
    })
public class Section extends BaseEntity implements Serializable {
  @JsonView({ViewPage.AdminClass.class, ViewPage.SectionClass.class})
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @Filter(name = FilterName.LANGUAGE)
  private List<SectionLanguageVersion> languageVersions;

  @Column
  @JsonView({ViewPage.AdminClass.class, ViewPage.SectionClass.class})
  private int type;

  @Column
  @JsonView({ViewPage.AdminClass.class, ViewPage.SectionClass.class})
  private int index;

  @Column
  @JsonView({ViewPage.AdminClass.class, ViewPage.SectionClass.class})
  private int status;

  @JsonView({ViewPage.AdminClass.class, ViewPage.SectionClass.class})
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @OrderBy("index asc")
  @Filter(name = FilterName.STATUS)
  private List<Block> blocks;

  @JsonView({ViewPage.AdminClass.class, ViewPage.SectionClass.class})
  @OneToMany(fetch = FetchType.LAZY)
  private List<FileManager> files;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append(super.toString());
    sb.append(", \"languageVersions\" :[");
    if (languageVersions != null)
      for (SectionLanguageVersion languageVersion : languageVersions) {
        sb.append("{\"id\" : ").append(languageVersion.getId()).append("},");
      }
    if (files != null)
      for (FileManager fileManager : files) {
        sb.append("{\"id\" : ").append(fileManager.getId()).append("},");
      }
    if (blocks != null)
      for (Block block : blocks) {
        sb.append("{\"id\" : ").append(block.getId()).append("},");
      }
    sb.append(", \"index\" :").append(index);
    sb.append(", \"type\" :").append(type);
    sb.append(", \"status\" :").append(status);
    sb.append('}');
    return sb.toString();
  }
}
