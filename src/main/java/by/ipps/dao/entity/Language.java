package by.ipps.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Language extends BaseEntity implements Serializable {

  @Column(nullable = false)
  private String nameLanguage;

  @Column(length = 5, nullable = false)
  private String codeLanguage;
}
