package by.ipps.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeAchievement extends BaseEntity implements Serializable {
    @Column
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "typeAchievement")
    private List<Achievement> achievements;
}
