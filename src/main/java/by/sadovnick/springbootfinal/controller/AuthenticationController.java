package by.sadovnick.springbootfinal.controller;

import by.sadovnick.springbootfinal.dto.RoleDto;
import by.sadovnick.springbootfinal.dto.UserDto;
import by.sadovnick.springbootfinal.service.MapperService;
import by.sadovnick.springbootfinal.service.RoleService;
import by.sadovnick.springbootfinal.service.UserService;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth_controller")
public class AuthenticationController {

  private final UserService userService;
  private final RoleService roleService;
  private final MapperService mapperService;

  @Autowired
  public AuthenticationController(UserService userService, RoleService roleService, MapperService mapperService) {
    this.userService = userService;
    this.roleService = roleService;
    this.mapperService = mapperService;
  }

  @GetMapping("/auth")
  public UserDto getAuthentication(Authentication authentication) {
    return mapperService.mapUserToUserDto(userService.findUserByUsername(authentication.getName()));
  }

  @GetMapping("/roles")
  public Set<RoleDto> getAllRoles() {
    return roleService.findAll().stream().map(mapperService::mapRoleToRoleDto).collect(Collectors.toSet());
  }
}
