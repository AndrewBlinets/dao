package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewContact;
import by.ipps.dao.utils.view.ViewCustomer;
import by.ipps.dao.utils.view.ViewDepartment;
import by.ipps.dao.utils.view.ViewFile;
import by.ipps.dao.utils.view.ViewNews;
import by.ipps.dao.utils.view.ViewPage;
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
    ViewPage.BaseClassPage.class,
    ViewDepartment.BaseClassDepartment.class,
    ViewDepartment.FullInformationClassDepartment.class,
    ViewNews.AdminNewsClass.class,
    ViewPage.BaseClassPageContent.class,
    ViewPage.BaseClassPageName.class,
    ViewPage.AdminClass.class,
    ViewPage.SectionClass.class,
    ViewCustomer.BaseClass.class
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
    final StringBuilder sb = new StringBuilder("\"id\" : ");
    sb.append("\"").append(id).append("\"");
    return sb.toString();
  }
}
