package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewContact;
import by.ipps.dao.utils.view.ViewDepartment;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
public class UserPortal extends BaseEntity implements Serializable {

    @Column(nullable = false, length = 60)
    private String login;
    @Column(nullable = false)
    private String hashPassword;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastChangePassword;
    @Column(nullable = false, length = 60)
    @JsonView({ViewContact.BaseClass.class, ViewDepartment.FullInformationClassDepartment.class})
    private String name;
    @JsonView(ViewContact.BaseClass.class)
    @Column(nullable = false, length = 60)
    private String surName;
    @JsonView(ViewContact.BaseClass.class)
    @Column(nullable = false, length = 60)
    private String patronicName;
    @JsonView(ViewContact.BaseClass.class)
    @Column
    private String email;
    @JsonView(ViewContact.BaseClass.class)
    @Column
    private String phone;
    @JsonView(ViewContact.BaseClass.class)
    @Column(length = 3)
    private String room;
    @Column
    private Boolean enabled;
    @Column
    private Boolean block;
    @ManyToMany
    @JoinTable(
            name = "department_user",
            joinColumns = @JoinColumn(name = "id_user", nullable = false, updatable = false),
            inverseJoinColumns = {@JoinColumn(name = "department_id", nullable = false, updatable = false)})
    private Set<Department> departments;
    @JsonView(ViewContact.BaseClass.class)
    @ManyToMany
    @JoinTable(
            name = "position_user",
            joinColumns = @JoinColumn(name = "id_user", nullable = false, updatable = false),
            inverseJoinColumns = {@JoinColumn(name = "position_id", nullable = false, updatable = false)})
//    @Where(clause = "statusR = 'A' and position.id = ")
    // todo fix many position for contact
    private Set<Position> positions;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "role_user",
            joinColumns = @JoinColumn(name = "id_user", nullable = false, updatable = false),
            inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)})
    private Set<Role> roles;
    @OneToMany(mappedBy = "user")
    private List<Logger> logers;
    @JsonView(ViewContact.BaseClass.class)
    @OneToOne(mappedBy = "userPortal")
    private Contact contact;
}
