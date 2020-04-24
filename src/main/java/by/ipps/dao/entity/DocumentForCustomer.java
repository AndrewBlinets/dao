package by.ipps.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DocumentForCustomer extends BaseEntity {

  @JoinColumn
  @ManyToOne
  private Project project;

  @Column
  @JsonIgnore
  private byte[] file;

  @Column(length = 100)
  @JsonIgnore
  private String fileName;

  @Column(length = 50)
  private String mimeType;

}