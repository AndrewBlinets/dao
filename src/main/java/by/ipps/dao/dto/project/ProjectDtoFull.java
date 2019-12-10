package by.ipps.dao.dto.project;

import by.ipps.dao.dto.BaseDto;
import by.ipps.dao.entity.FileManager;
import by.ipps.dao.entity.ProjectLanguageVersion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDtoFull extends BaseDto implements Serializable {

    private long mainImage;
    private String title;
    private String content;
    private List<Long> images;

    public void setLanguageVersions(List<ProjectLanguageVersion> versions) {
        if(!versions.isEmpty()){
            this.title = versions.get(0).getTitle();
            this.content = versions.get(0).getContent();
        }
    }

    public void setImages(List<FileManager> images){
        if(!images.isEmpty()){
            this.images = new ArrayList<>();
            for (FileManager fileManager: images)
                this.images.add(fileManager.getId());
        }
    }
}
