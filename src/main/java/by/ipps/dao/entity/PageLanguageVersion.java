package by.ipps.dao.entity;

import by.ipps.dao.utils.constant.FilterName;
import by.ipps.dao.utils.view.ViewDocumentForCustomer;
import by.ipps.dao.utils.view.ViewPage;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@FilterDef(
    name = FilterName.LANGUAGE,
    defaultCondition = "code_language = :language",
    parameters = {
      @ParamDef(name = "language", type = "string"),
    })
public class PageLanguageVersion extends BaseEntity {

  @JsonView({ViewPage.AdminClass.class, ViewDocumentForCustomer.FileClass.class})
  @Column
  private String name;

  @JsonView(ViewPage.AdminClass.class)
  @Column(name = "code_language")
  private String codeLanguage;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append(super.toString());
    sb.append(", \"name\" :").append(name).append("\",");
    sb.append(", \"codeLanguage\" :").append(codeLanguage).append("\"");
    sb.append('}');
    return sb.toString();
  }
}
