package by.ipps.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contact extends BaseEntity {

    @Column
    private String nameControl;
    @Column
    private String positionName;
    @Column
    private String room;
    @Column
    private String phone;
    @Column
    private String email;
    @Column
    private String note;

}
