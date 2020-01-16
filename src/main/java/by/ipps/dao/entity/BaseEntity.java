package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewContact;
import by.ipps.dao.utils.view.ViewDepartment;
import by.ipps.dao.utils.view.ViewFile;
import by.ipps.dao.utils.view.ViewSection;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BaseEntity implements Serializable {

  @JsonView({
    ViewContact.BaseClass.class,
    ViewFile.BaseClass.class,
    ViewSection.BaseClassSection.class,
    ViewDepartment.BaseClassDepartment.class,
    ViewDepartment.FullInformationClassDepartment.class
  })
  @Id
  @Column
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  private long id;

  @Column(nullable = false, length = 3)
  private String statusR = "A";

  @Column(nullable = false)
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date dti;

  @Column(nullable = false)
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateChangeStatusR;
}
