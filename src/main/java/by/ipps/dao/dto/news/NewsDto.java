package by.ipps.dao.dto.news;

import by.ipps.dao.dto.BaseDto;
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
public class NewsDto extends BaseDto implements Serializable {

    private Date datePublic;
    private long mainImage;
    private int countView;
    private String shortTitle;
    private String entrySpeech;

    public void setLanguageVersions(List<NewsLanguageVersion> versions) {
        this.shortTitle = versions.get(0).getShortTitle();
        this.entrySpeech = versions.get(0).getEntrySpeech();
    }
}
