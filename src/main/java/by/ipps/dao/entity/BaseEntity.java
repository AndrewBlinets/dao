package by.ipps.dao.entity;

import by.ipps.dao.utils.view.*;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BaseEntity implements Serializable {

  @JsonView({
    ViewContact.BaseClass.class,
    ViewFile.BaseClass.class,
    ViewPage.BaseClassPage.class,
    ViewDepartment.BaseClassDepartment.class,
    ViewDepartment.FullInformationClassDepartment.class,
    ViewNews.AdminNewsClass.class,
    ViewPage.BaseClassPageContent.class,
    ViewPage.BaseClassPageName.class,
    ViewPage.AdminClass.class,
    ViewPage.SectionClass.class
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
  @JsonView({ViewNews.AdminNewsClass.class})
  private Date dti;

  @Column(nullable = false)
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateChangeStatusR;

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("\"id\" : ");
    sb.append("\"").append(id).append("\"");
    return sb.toString();
  }
}
