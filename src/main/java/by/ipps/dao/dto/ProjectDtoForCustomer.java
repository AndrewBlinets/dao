package by.ipps.dao.dto;

import by.ipps.dao.dto.sheet.BaseDto;
import by.ipps.dao.entity.ProjectLanguageVersion;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDtoForCustomer extends BaseDto {
  private String shortTitle;

  public void setLanguageVersions(List<ProjectLanguageVersion> versions) {
    if (!versions.isEmpty()) {
      this.shortTitle = versions.get(0).getShortTitle();
    }
  }
}
