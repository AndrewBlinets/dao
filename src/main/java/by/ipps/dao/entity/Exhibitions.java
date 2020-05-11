package by.ipps.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
