package by.ipps.dao.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Language extends BaseEntity implements Serializable {

  @Column(nullable = false)
  private String nameLanguage;

  @Column(length = 5, nullable = false)
  private String codeLanguage;
}
