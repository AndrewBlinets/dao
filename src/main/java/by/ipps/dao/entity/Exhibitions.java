package by.ipps.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Exhibitions extends BaseEntity implements Serializable {
  @Column private String name;

  @Column
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  @OneToMany(fetch = FetchType.EAGER)
  private List<FileManager> images;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append(super.toString());
    sb.append(", \"name\" : \"").append(name).append('\"');
    sb.append(", \"date\" : ").append(date);
    sb.append(", \"images\" : [");
    for (FileManager fileManager : images) {
      sb.append("{\"id\":").append(fileManager.getId()).append("},");
    }
    sb.append("],");
    sb.append('}');
    return sb.toString();
  }
}
