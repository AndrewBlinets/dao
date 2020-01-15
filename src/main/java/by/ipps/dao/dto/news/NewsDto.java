package by.ipps.dao.dto.news;

import by.ipps.dao.dto.BaseDto;
import by.ipps.dao.entity.FileManager;
import by.ipps.dao.entity.NewsLanguageVersion;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        if(!versions.isEmpty()){
            this.shortTitle = versions.get(0).getShortTitle();
            this.entrySpeech = versions.get(0).getEntrySpeech();
        }
    }

    public void setMainImage(FileManager mainImage) {
        this.mainImage = mainImage.getId();
    }
}
