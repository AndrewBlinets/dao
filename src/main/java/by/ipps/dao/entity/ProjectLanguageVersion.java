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

  @Column private String title;

  @Column private String shortTitle;

  @Column private String content;

  @Column private String entrySpeech;

  @Column(name = "code_language")
  private String codeLanguage;
}
