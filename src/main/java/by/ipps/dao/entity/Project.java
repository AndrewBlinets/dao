package by.ipps.dao.entity;

import by.ipps.dao.utils.constant.FilterName;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Project extends BaseEntity implements Serializable {

    @OneToMany(fetch = FetchType.LAZY)
    @Filter(name = FilterName.LANGUAGE)
    private List<ProjectLanguageVersion> languageVersions;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mainImage")
    private FileManager mainImage;

    @OneToMany(fetch = FetchType.LAZY)
    private List<FileManager> images;

    @ManyToOne
    @JoinColumn(name = "departament_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Section section;
}
