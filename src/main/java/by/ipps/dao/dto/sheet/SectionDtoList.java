package by.ipps.dao.dto.sheet;

import by.ipps.dao.entity.SectionLanguageVersion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectionDtoList extends BaseDto {
  private int type;
  private int index;
  private String name;
  private int status;

  public void setLanguageVersions(List<SectionLanguageVersion> languageVersions) {
    if (!languageVersions.isEmpty()) this.name = languageVersions.get(0).getName();
  }
}
