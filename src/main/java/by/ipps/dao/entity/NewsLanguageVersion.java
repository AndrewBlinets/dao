package by.ipps.dao.entity;

import by.ipps.dao.utils.constant.FilterName;
import lombok.*;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FilterDef(
    name = FilterName.LANGUAGE,
    defaultCondition = "code_language = :language",
    parameters = {
      @ParamDef(name = "language", type = "string"),
    })
@EqualsAndHashCode(callSuper = true)
public class NewsLanguageVersion extends BaseEntity implements Serializable {

  @Column private String title;

  @Column(length = 10000)
  private String content;

  @Column(length = 100)
  private String shortTitle;

  @Column private String entrySpeech;

  @Column(name = "code_language")
  private String codeLanguage;

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("{");
    sb.append(super.toString());
    sb.append(", \"title\" : \"").append(title).append('\"');
    sb.append(", \"content\" : \"").append(content).append('\"');
    sb.append(", \"shortTitle\" : \"").append(shortTitle).append('\"');
    sb.append(", \"entrySpeech\" : \"").append(entrySpeech).append('\"');
    sb.append(", \"codeLanguage\" : \"").append(codeLanguage).append('\"');
    sb.append('}');
    return sb.toString();
  }
}
