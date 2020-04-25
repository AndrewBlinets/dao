package by.ipps.dao.dto.sheet;

import by.ipps.dao.entity.FileManager;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectionDto extends SectionDtoList {
  private List<BlockDto> blocks;
  private List<Long> files;

  public void setFiles(List<FileManager> files) {
    for (FileManager fileManager : files) this.files.add(fileManager.getId());
  }
}
