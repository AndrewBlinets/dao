package by.ipps.dao.entity;

import by.ipps.dao.utils.constant.FilterName;
import lombok.*;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FilterDef(
    name = FilterName.LANGUAGE,
    defaultCondition = "code_language = :language",
    parameters = {
      @ParamDef(name = "language", type = "string"),
    })
public class ProjectLanguageVersion extends BaseEntity implements Serializable {

  @Column(length = 500)
  private String title;

  @Column(length = 500)
  private String shortTitle;

  @Column(length = 10000)
  private String content;

  //  @Column private String entrySpeech;

  @Column(name = "code_language")
  private String codeLanguage;

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("{");
    sb.append(super.toString());
    sb.append(", \"title\" : \"").append(title).append('\"');
    sb.append(", \"shortTitle\" : \"").append(shortTitle).append('\"');
    sb.append(", \"content\" : \"").append(content).append('\"');
    //    sb.append(", \"entrySpeech\" : \"").append(entrySpeech).append('\"');
    sb.append(", \"codeLanguage\" : \"").append(codeLanguage).append('\"');
    sb.append('}');
    return sb.toString();
  }
}
