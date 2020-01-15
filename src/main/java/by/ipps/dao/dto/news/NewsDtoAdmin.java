package by.ipps.dao.dto.news;

import by.ipps.dao.dto.BaseDto;
import by.ipps.dao.entity.NewsLanguageVersion;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDtoAdmin extends BaseDto implements Serializable{
    private Date datePublic;
    private Date dti;
    private String shortTitle;
    private String status;
    public void setLanguageVersions(List<NewsLanguageVersion> versions) {
        if(!versions.isEmpty()){
            this.shortTitle = versions.get(0).getShortTitle();
        }
    }
}
