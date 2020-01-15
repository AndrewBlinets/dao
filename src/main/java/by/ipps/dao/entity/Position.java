package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewContact;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Position extends BaseEntity implements Serializable {
    @JsonView(ViewContact.BaseClass.class)
    @Column
    private String name;
    @ManyToMany(mappedBy = "positions")
    private List<UserPortal> users;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "deportamnt_position",
            joinColumns = @JoinColumn(name = "id_position", nullable = false, updatable = false),
            inverseJoinColumns = {@JoinColumn(name = "id_deportament", nullable = false, updatable = false)})
    private List<Department> departments;
}
