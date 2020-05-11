package by.ipps.dao.dto.sheet;

import by.ipps.dao.entity.PageLanguageVersion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageDtoFull extends BaseDto {
  private List<SectionDto> sections;
  private int code;
  private String name;

  public void setLanguageVersions(List<PageLanguageVersion> languageVersions) {
    if (!languageVersions.isEmpty()) this.name = languageVersions.get(0).getName();
  }
}
