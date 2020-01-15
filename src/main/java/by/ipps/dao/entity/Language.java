package by.ipps.dao.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Language extends BaseEntity implements Serializable {

    @Column(nullable = false)
    private String nameLanguage;

    @Column(length = 5, nullable = false)
    private String codeLanguage;

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "language")
//    private ProjectLanguageVersion projectLanguageVersion;
}
