package by.sadovnick.springbootfinal.service;

import by.sadovnick.springbootfinal.models.Role;
import java.util.List;

public interface RoleService {

  List<Role> findAll();

  void save(Role role);

  Role findRoleById(int id);
}
