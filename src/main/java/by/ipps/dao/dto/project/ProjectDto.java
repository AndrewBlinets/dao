package by.ipps.dao.dto.project;

import by.ipps.dao.dto.sheet.BaseDto;
import by.ipps.dao.entity.FileManager;
import by.ipps.dao.entity.ProjectLanguageVersion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto extends BaseDto implements Serializable {

  private long mainImage;
  private String shortTitle;

  public void setLanguageVersions(List<ProjectLanguageVersion> versions) {
    if (!versions.isEmpty()) {
      this.shortTitle = versions.get(0).getShortTitle();
    }
  }

  public void setMainImage(FileManager image) {
    if (image != null) {
      this.mainImage = image.getId();
    }
  }
}
