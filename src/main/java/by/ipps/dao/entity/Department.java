package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewContact;
import by.ipps.dao.utils.view.ViewDepartment;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@ToString
public class Department extends BaseEntity implements Serializable {

    @JsonView({ViewContact.BaseClass.class, ViewDepartment.BaseClassDepartment.class})
    @Column
    private String name;
    @JsonView({ViewDepartment.BaseClassDepartment.class})
    @Column
    private String code;
    @JsonView(ViewContact.BaseClass.class)
    @ManyToMany(mappedBy = "departments")
    private Set<UserPortal> users;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader", nullable = true)
    private UserPortal leader;
    @ManyToMany(mappedBy = "departments")
    private List<Position> positions;
    @JsonView(ViewDepartment.BaseClassDepartment.class)
    @ManyToOne
    @JoinColumn(name = "section_id", insertable = false, updatable = false)
    private Section section;
}
