package by.ipps.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FileManager extends BaseEntity implements Serializable {

    @Column(nullable = false)
    @JsonIgnore
    private String fileName;

    @Column(nullable = false)
    @JsonIgnore
    private String fileMine;

    @Column(nullable = false)
    @JsonIgnore
    private String path;
}
