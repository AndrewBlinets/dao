package by.ipps.dao.dto;

import by.ipps.dao.entity.Department;
import by.ipps.dao.entity.Role;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto extends BaseDto {
    private String login;
    private String hashPassword;
    private String name;
    private String surName;
    private String patronicName;
    private List<String> roles;
    private String position;
    private String email;
    private long department;

    public void setRole(List<Role> roles) {
        this.roles = new ArrayList<>();
        for (Role r : roles) {
            this.roles.add(r.getName());
        }
    }

    public void setDepartment(Department department) {
        this.department = department.getId();
    }
}
