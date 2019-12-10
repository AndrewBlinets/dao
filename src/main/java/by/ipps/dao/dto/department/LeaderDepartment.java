package by.ipps.dao.dto.department;

import by.ipps.dao.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaderDepartment extends BaseDto implements Serializable {
    private String name;
    private String surName;
    private String patronicName;
}
