package by.ipps.dao.entity;

import by.ipps.dao.utils.constant.FilterName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class NewsLanguageVersion extends BaseEntity implements Serializable {

  //    @OneToOne(fetch = FetchType.EAGER)
  //    @JoinColumn(name = "language")
  //    private Language language;

  @Column private String title;

  @Column(length = 10000)
  private String content;

  @Column(length = 30)
  private String shortTitle;

  @Column private String entrySpeech;

  @Column(name = "code_language")
  private String codeLanguage;
}
