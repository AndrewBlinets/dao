package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewCustomer;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Customer extends UserBase implements Serializable {

  @ManyToOne
  @JoinColumn(name = "org_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Org org;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(
          name = "role_customer",
          joinColumns = @JoinColumn(name = "id_customer", nullable = false, updatable = false),
          inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)})
  private Set<Role> roles;

  @OneToMany
  @Where(clause = "statusr = 'A' and publicForCustomer = true")
  private List<Project> projects;
}
