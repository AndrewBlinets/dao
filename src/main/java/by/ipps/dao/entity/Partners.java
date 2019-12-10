package by.ipps.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Partners extends BaseEntity implements Serializable {

    @Column
    private String name;
    @OneToOne
    private FileManager image;

    @Column
    private String url;
}
