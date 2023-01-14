package by.sadovnick.springbootfinal.service;

import by.sadovnick.springbootfinal.dto.RoleDto;
import by.sadovnick.springbootfinal.dto.UserDto;
import by.sadovnick.springbootfinal.models.Role;
import by.sadovnick.springbootfinal.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperServiceImpl implements MapperService {

  private final ModelMapper modelMapper;

  @Autowired
  public MapperServiceImpl(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public RoleDto mapRoleToRoleDto(Role role) {
    return modelMapper.map(role, RoleDto.class);
  }

  @Override
  public UserDto mapUserToUserDto(User user) {
    return modelMapper.map(user, UserDto.class);
  }

  @Override
  public User mapUserDtoToUser(UserDto userDto) {
    return modelMapper.map(userDto, User.class);
  }
}
