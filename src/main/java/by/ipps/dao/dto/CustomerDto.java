package by.ipps.dao.dto;

import by.ipps.dao.dto.sheet.BaseDto;
import by.ipps.dao.entity.Role;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDto extends BaseDto {
  private long id;
  private String login;
  private String hashPassword;
  private String name;
  private String surName;
  private String patronicName;
  private List<String> roles;
  private String email;
  private boolean enabled;
  private boolean block;
  private Date dateLastChangePassword;

  public void setRoles(Set<Role> roles) {
    this.roles = new ArrayList<>();
    for (Role r : roles) {
      this.roles.add(r.getName());
    }
  }
}
