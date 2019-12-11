package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewContact;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Department extends BaseEntity implements Serializable {

    @JsonView(ViewContact.BaseClass.class)
    @Column
    private String name;
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
    @ManyToOne
    @JoinColumn(name = "section_id", insertable = false, updatable = false)
    private Section section;
}
