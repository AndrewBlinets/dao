package by.ipps.dao.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

@Entity
@Data
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
