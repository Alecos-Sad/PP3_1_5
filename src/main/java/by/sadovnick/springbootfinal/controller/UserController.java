package by.sadovnick.springbootfinal.controller;

import by.sadovnick.springbootfinal.dto.UserDto;
import by.sadovnick.springbootfinal.service.MapperService;
import by.sadovnick.springbootfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/gateway")
public class UserController {

  private final UserService userService;
  private final MapperService mapperService;

  @Autowired
  public UserController(UserService userService, MapperService mapperService) {
    this.userService = userService;
    this.mapperService = mapperService;
  }

  @GetMapping
  public UserDto getUser(Authentication authentication) {
    return mapperService.mapUserToUserDto(userService.findUserByUsername(authentication.getName()));
  }


}
