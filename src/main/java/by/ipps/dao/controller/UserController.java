package by.ipps.dao.controller;

import by.ipps.dao.controller.base.BaseEntityAbstractController;
import by.ipps.dao.controller.base.BaseEntityController;
import by.ipps.dao.dto.UserDto;
import by.ipps.dao.entity.UserPortal;
import by.ipps.dao.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/users")
public class UserController extends BaseEntityAbstractController<UserPortal, UserService>
    implements BaseEntityController<UserPortal> {

  private UserService userService;
  private ModelMapper modelMapper;

  protected UserController(UserService userService, ModelMapper modelMapper) {
    super(userService);
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  @Transactional
  @PostMapping("/auth")
  @ResponseBody
  public ResponseEntity<UserDto> auth(@RequestBody String login) {
    UserPortal userPortal = userService.getUserByLogin(login);
    if (userPortal != null) {
      UserDto userDto = modelMapper.map(userPortal, UserDto.class);
      return new ResponseEntity<>(userDto, HttpStatus.OK);
    } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
