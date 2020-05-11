package by.ipps.dao.entity;

import by.ipps.dao.utils.constant.FilterName;
import by.ipps.dao.utils.view.ViewPage;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FilterDef(
    name = FilterName.STATUS,
    defaultCondition = "status = :status and statusr = 'A'",
    parameters = {
      @ParamDef(name = "status", type = "integer"),
    })
public class Block extends BaseEntity implements Serializable {

  @JsonView({ViewPage.AdminClass.class, ViewPage.SectionClass.class})
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @Filter(name = FilterName.LANGUAGE)
  private List<BlockLanguageVersion> languageVersions;

  @JsonView({ViewPage.AdminClass.class, ViewPage.SectionClass.class})
  @Column
  private int index;

  @Column
  @JsonView({ViewPage.AdminClass.class, ViewPage.SectionClass.class})
  private int status;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append(super.toString());
    sb.append(", \"languageVersions\" :[");
    for (BlockLanguageVersion languageVersion : languageVersions) {
      sb.append("{\"id\" : ").append(languageVersion.getId()).append("},");
    }
    sb.append(", \"index\" :").append(index);
    sb.append('}');
    return sb.toString();
  }
}
