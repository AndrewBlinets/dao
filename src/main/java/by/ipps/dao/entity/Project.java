package by.ipps.dao.entity;

import by.ipps.dao.utils.constant.FilterName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
}
