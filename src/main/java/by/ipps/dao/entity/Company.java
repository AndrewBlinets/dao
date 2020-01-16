package by.ipps.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity {
  @Column private String adress;

  @Column(length = 6)
  private String postIndex;

  @Column private String name;
  @Column private String shortName;
  @Column private String phoneFax;
  @Column private String email;
  @Column private String hotLine;
  @Column private String coordX;
  @Column private String coordY;
  @Column private String hotLinePhone;
}
