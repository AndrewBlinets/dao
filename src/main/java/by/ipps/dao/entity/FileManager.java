package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewFile;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class FileManager extends BaseEntity implements Serializable {

  @Column(nullable = false)
  @JsonView(ViewFile.BaseClass.class)
  private String fileName;

  @Column(nullable = false)
  @JsonView(ViewFile.BaseClass.class)
  private String fileMine;

  @Column(nullable = false)
  @JsonView(ViewFile.BaseClass.class)
  private String path;

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("{");
    sb.append(super.toString());
    sb.append(", \"fileName\" : \"").append(fileName).append('\"');
    sb.append(", \"fileMine\" : \"").append(fileMine).append('\"');
    sb.append(", \"path\" : \"").append(path).append('\"');
    sb.append('}');
    return sb.toString();
  }
}
