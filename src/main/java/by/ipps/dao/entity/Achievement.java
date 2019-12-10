package by.ipps.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Achievement extends BaseEntity implements Serializable {
    @Column
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private FileManager image;

    @Column
    private String type;
}
