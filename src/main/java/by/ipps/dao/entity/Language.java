package by.ipps.dao.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Language extends BaseEntity implements Serializable {

  @Column(nullable = false)
  private String nameLanguage;

  @Column(length = 5, nullable = false)
  private String codeLanguage;
}
