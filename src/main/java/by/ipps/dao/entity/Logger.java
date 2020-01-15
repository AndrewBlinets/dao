package by.ipps.dao.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Logger extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserPortal user;
    @Column(nullable = false)
    private String tableName;
    @Column(nullable = false)
    private long rowId;
    @Column(length = 6, nullable = false)
    private String event;
    @Column(length = 1000)
    private String oldValue;

}
