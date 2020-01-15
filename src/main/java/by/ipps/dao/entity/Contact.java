package by.ipps.dao.entity;

import by.ipps.dao.utils.view.ViewContact;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contact extends BaseEntity {

    @JsonView(ViewContact.BaseClass.class)
    @Column
    private String note;
    @OneToOne
    @JoinColumn(name = "users")
    private UserPortal userPortal;

}
