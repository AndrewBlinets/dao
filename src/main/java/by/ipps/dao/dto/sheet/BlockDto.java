package by.ipps.dao.dto.sheet;

import by.ipps.dao.entity.BaseEntity;
import by.ipps.dao.entity.BlockLanguageVersion;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlockDto extends BaseEntity {

  private int index;
  private String content;

  public void setLanguageVersions(List<BlockLanguageVersion> languageVersions) {
    if (!languageVersions.isEmpty()) this.content = languageVersions.get(0).getContent();
  }
}
