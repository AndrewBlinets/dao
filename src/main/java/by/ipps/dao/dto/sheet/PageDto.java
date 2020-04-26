package by.ipps.dao.dto.sheet;

import by.ipps.dao.entity.PageLanguageVersion;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageDto extends BaseDto {
  private List<SectionDtoList> sections;
  private int code;
  private String name;

  public void setLanguageVersions(List<PageLanguageVersion> languageVersions) {
    if (!languageVersions.isEmpty()) this.name = languageVersions.get(0).getName();
  }
}