package by.ipps.dao.entity;

import by.ipps.dao.utils.constant.FilterName;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FilterDef(
        name = FilterName.LANGUAGE,
        defaultCondition = "code_language = :language",
        parameters = {
                @ParamDef(name = "language", type = "string"),
        }
)
public class ProjectLanguageVersion extends BaseEntity implements Serializable {

    @Column
    private String title;

    @Column
    private String shortTitle;

    @Column
    private String content;

    @Column
    private String entrySpeech;

    @Column(name = "code_language")
    private String codeLanguage;

}
