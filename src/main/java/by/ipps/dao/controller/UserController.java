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

@RestController
@RequestMapping("/users")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserController extends BaseEntityAbstractController<UserPortal, UserService>
        implements BaseEntityController<UserPortal> {

    private UserService userService;
    private ModelMapper modelMapper;

    protected UserController(UserService userService, ModelMapper modelMapper) {
        super(userService);
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/auth")
    @ResponseBody
    public ResponseEntity auth(@RequestBody String login){
        UserPortal userPortal = userService.getUserByLogin(login);
//        System.out.println(user.toString());
        UserDto userDto = modelMapper.map(userPortal, UserDto.class);
//        System.out.println(userDto.toString());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
