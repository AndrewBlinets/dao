package by.ipps.dao.entity;

import by.ipps.dao.utils.constant.FilterName;
import by.ipps.dao.utils.view.ViewNews;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@FilterDef(
    name = FilterName.LANGUAGE,
    defaultCondition = "code_language = :language",
    parameters = {
      @ParamDef(name = "language", type = "string"),
    })
@EqualsAndHashCode(callSuper = true)
public class NewsLanguageVersion extends BaseEntity implements Serializable {

  @Column(length = 500)
  @JsonView({ViewNews.AdminNewsClass.class})
  private String title;

  @Column(length = 10000)
  @JsonView({ViewNews.AdminNewsClass.class})
  private String content;

  @Column(length = 500)
  @JsonView({ViewNews.AdminNewsClass.class})
  private String shortTitle;

  @Column(length = 2000)
  @JsonView({ViewNews.AdminNewsClass.class})
  private String entrySpeech;

  @Column(name = "code_language")
  @JsonView({ViewNews.AdminNewsClass.class})
  private String codeLanguage;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
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
