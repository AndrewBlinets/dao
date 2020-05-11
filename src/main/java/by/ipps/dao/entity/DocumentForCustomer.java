package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewDocumentForCustomer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class DocumentForCustomer extends BaseEntity {

  @JoinColumn @ManyToOne private Project project;
  @JoinColumn @ManyToOne private Sheet sheet;

  @Column @JsonView({ViewDocumentForCustomer.BaseClass.class})
  private byte[] file;

  @Column(length = 100)
  @JsonView({ViewDocumentForCustomer.BaseClass.class, ViewDocumentForCustomer.FileClass.class})
  private String fileName;

  @Column
  @JsonView({ViewDocumentForCustomer.FileClass.class})
  private String description;

  @Column
  @JsonView({ViewDocumentForCustomer.FileClass.class, ViewDocumentForCustomer.FileClass.class})
  private int size;

  @Column(length = 50)
  @JsonView({ViewDocumentForCustomer.BaseClass.class, ViewDocumentForCustomer.FileClass.class})
  private String mimeType;
}
