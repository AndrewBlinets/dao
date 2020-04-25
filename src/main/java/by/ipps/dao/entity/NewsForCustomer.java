package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewNews;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class NewsForCustomer extends NewsLanguageVersion {
  @OneToOne
  @JoinColumn(name = "mainImge")
  @JsonView({ViewNews.AdminNewsClass.class})
  private FileManager mainImage;
}
