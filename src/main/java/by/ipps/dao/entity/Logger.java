package by.ipps.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Logger extends BaseEntity implements Serializable {

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UserPortal user;

  @Column(nullable = false)
  private String tableName;

  @Column(nullable = false)
  private long rowId;

  @Column(length = 6, nullable = false)
  private String event;

  @Column(length = 1000)
  private String oldValue;

  public Logger(UserPortal user, String tableName, long rowId, String event) {
    this.user = user;
    this.tableName = tableName;
    this.rowId = rowId;
    this.event = event;
  }

  public Logger(UserPortal user, String tableName, long rowId, String event, String oldValue) {
    this.user = user;
    this.tableName = tableName;
    this.rowId = rowId;
    this.event = event;
    this.oldValue = oldValue;
  }
}
