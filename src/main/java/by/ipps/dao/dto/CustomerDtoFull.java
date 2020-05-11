package by.ipps.dao.dto;

import by.ipps.dao.entity.Org;
import by.ipps.dao.entity.Role;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDtoFull {
  private long id;
  private String name;
  private String surName;
  private String patronicName;
  private List<String> roles;
  private String email;
  private Org org;
  List<ProjectDtoForCustomer> projects;

  public void setRoles(Set<Role> roles) {
    this.roles = new ArrayList<>();
    for (Role r : roles) {
      this.roles.add(r.getName());
    }
  }
}
