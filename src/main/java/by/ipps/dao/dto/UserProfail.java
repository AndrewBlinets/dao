package by.ipps.dao.dto;

import by.ipps.dao.dto.sheet.BaseDto;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfail extends BaseDto implements Serializable {
  private String username;
  private String email;
  private String newPassword;
}
