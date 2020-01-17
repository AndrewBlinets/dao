package by.ipps.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exhibitions extends BaseEntity implements Serializable {
  @Column private String name;

  @Column
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  @OneToMany(fetch = FetchType.EAGER)
  private List<FileManager> images;

//  @Override
//  public String toString() {
//    final StringBuffer sb = new StringBuffer("Exhibitions{");
//    sb.append("name='").append(name).append('\'');
//    sb.append(", date=").append(date);
//    sb.append(", images=").append(images);
//    sb.append('}');
//    return sb.toString();
//  }
}
