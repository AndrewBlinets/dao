package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewDepartment;
import by.ipps.dao.utils.view.ViewSection;
import com.fasterxml.jackson.annotation.JsonView;
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
public class Section extends BaseEntity implements Serializable {
    @JsonView({ViewDepartment.BaseClassDepartment.class, ViewSection.BaseClassSection.class})
    @Column
    private String name;
    @Column
    @JsonView(ViewSection.BaseClassSection.class)
    private int code;
    @OneToMany(mappedBy = "section")
    private List<Department> departments;
}
