package by.ipps.dao.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageProjectDto {

    List<ProjectDto> content;
    long totalElements;
    private int number;
    private int size;
    private int totalPages;
    private int numberOfElements;
}
