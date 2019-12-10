package by.ipps.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPortal extends BaseEntity implements Serializable {

  @Column(nullable = false, length = 60)
  private String login;

  @Column(nullable = false)
  private String hashPassword;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateLastChangePassword;

  @Column(nullable = false, length = 60)
  private String name;

  @Column(nullable = false, length = 60)
  private String surName;

  @Column(nullable = false, length = 60)
  private String patronicName;

  @Column
  private String email;

  @ManyToOne
  @JoinColumn(name = "id_department")
  private Department department;

  @ManyToMany
  @JoinTable(
      name = "position_user",
      joinColumns = @JoinColumn(name = "id_user", nullable = false, updatable = false),
      inverseJoinColumns = {@JoinColumn(name = "position_id", nullable = false, updatable = false)})
  private List<Position> positions;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(
      name = "role_user",
      joinColumns = @JoinColumn(name = "id_user", nullable = false, updatable = false),
      inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)})
  private List<Role> roles;

  @OneToMany(mappedBy = "user")
  private List<Logger> logers;
}
