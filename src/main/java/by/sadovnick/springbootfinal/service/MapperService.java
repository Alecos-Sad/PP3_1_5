package by.sadovnick.springbootfinal.service;

import by.sadovnick.springbootfinal.dto.RoleDto;
import by.sadovnick.springbootfinal.dto.UserDto;
import by.sadovnick.springbootfinal.models.Role;
import by.sadovnick.springbootfinal.models.User;

public interface MapperService {

  RoleDto mapRoleToRoleDto(Role role);

  UserDto mapUserToUserDto(User user);

  User mapUserDtoToUser(UserDto userDto);
}
