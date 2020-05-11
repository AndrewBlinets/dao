package by.ipps.dao.dto;

import by.ipps.dao.dto.sheet.BaseDto;
import by.ipps.dao.entity.ProjectLanguageVersion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDtoForCustomerOne extends BaseDto {
  private String shortTitle;
  private List<CustomerForProject> customers;
  private boolean favorites;

  public void setLanguageVersions(List<ProjectLanguageVersion> versions) {
    if (!versions.isEmpty()) {
      for (ProjectLanguageVersion projectLanguageVersion : versions)
        if (projectLanguageVersion.getCodeLanguage().equals("ru")) {
          this.shortTitle = projectLanguageVersion.getShortTitle();
          break;
        }
    }
  }
}
