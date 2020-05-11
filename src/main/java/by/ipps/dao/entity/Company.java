package by.ipps.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
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

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append(super.toString());
    sb.append(", \"adress\" : \"").append(adress).append('\"');
    sb.append(", \"postIndex\" : \"").append(postIndex).append('\"');
    sb.append(", \"name\"  :  \"").append(name).append('\"');
    sb.append(", \"shortName\"  :  \"").append(shortName).append('\"');
    sb.append(", \"phoneFax\"  :  \"").append(phoneFax).append('\"');
    sb.append(", \"email\"  :  \"").append(email).append('\"');
    sb.append(", \"hotLine\"  :  \"").append(hotLine).append('\"');
    sb.append(", \"coordX\"  :  \"").append(coordX).append('\"');
    sb.append(", \"coordY\"  :  \"").append(coordY).append('\"');
    sb.append(", \"hotLinePhone\"  :  \"").append(hotLinePhone).append('\"');
    sb.append('}');
    return sb.toString();
  }
}
