package by.ipps.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeFiel extends BaseEntity {
    @Column(length = 6)
    private String expansion;
    @Column
    private String typeDoc;
    @Column
    private String mine;
}
