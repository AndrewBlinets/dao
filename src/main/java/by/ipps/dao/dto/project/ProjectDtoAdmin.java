package by.ipps.dao.dto.project;

import by.ipps.dao.dto.page.BaseDto;
import by.ipps.dao.entity.ProjectLanguageVersion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDtoAdmin extends BaseDto implements Serializable {

  private Date datePublic;
  private Date dti;
  private String shortTitle;
  private int status;
  private boolean publicForCustomer;

  public void setLanguageVersions(List<ProjectLanguageVersion> versions) {
    if (!versions.isEmpty()) {
      this.shortTitle = versions.get(0).getShortTitle();
    }
  }
}
