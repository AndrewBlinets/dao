package by.ipps.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exhibitions extends BaseEntity implements Serializable {
    @Column
    private String name;
    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @OneToMany(fetch = FetchType.EAGER)
    private List<FileManager> images;
}
