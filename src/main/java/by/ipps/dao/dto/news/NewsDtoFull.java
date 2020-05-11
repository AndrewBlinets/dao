package by.ipps.dao.dto.news;

import by.ipps.dao.dto.sheet.BaseDto;
import by.ipps.dao.entity.FileManager;
import by.ipps.dao.entity.NewsLanguageVersion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsDtoFull extends BaseDto implements Serializable {

  private Date datePublic;
  private long mainImage;
  private int countView;
  private String title;
  private String content;
  private String entrySpeech;
  private String shortTitle;

  public void setLanguageVersions(List<NewsLanguageVersion> versions) {
    this.title = versions.get(0).getTitle();
    this.content = versions.get(0).getContent();
    this.entrySpeech = versions.get(0).getEntrySpeech();
    this.shortTitle = versions.get(0).getShortTitle();
  }

  public void setMainImage(FileManager mainImage) {
    this.mainImage = mainImage.getId();
  }
}
