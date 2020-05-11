package by.ipps.dao.entity;

import by.ipps.dao.utils.constant.FilterName;
import by.ipps.dao.utils.view.ViewPage;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FilterDef(
    name = FilterName.LANGUAGE,
    defaultCondition = "code_language = :language",
    parameters = {
      @ParamDef(name = "language", type = "string"),
    })
public class BlockLanguageVersion extends BaseEntity {

  @JsonView({ViewPage.AdminClass.class, ViewPage.SectionClass.class})
  @Column(length = 10000)
  private String content;

  @JsonView({ViewPage.AdminClass.class, ViewPage.SectionClass.class})
  @Column(name = "code_language")
  private String codeLanguage;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append(super.toString());
    sb.append(", \"content\" :").append(content).append("\",");
    sb.append(", \"codeLanguage\" :").append(codeLanguage).append("\"");
    sb.append('}');
    return sb.toString();
  }
}
