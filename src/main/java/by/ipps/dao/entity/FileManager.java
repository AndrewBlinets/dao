package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewFile;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FileManager extends BaseEntity implements Serializable {

    @Column(nullable = false)
    @JsonView(ViewFile.BaseClass.class)
    private String fileName;

    @Column(nullable = false)
    @JsonView(ViewFile.BaseClass.class)
    private String fileMine;

    @Column(nullable = false)
    @JsonView(ViewFile.BaseClass.class)
    private String path;
}
