package by.sadovnick.springbootfinal.service;

import by.sadovnick.springbootfinal.models.User;
import java.util.List;

public interface UserService {

  List<User> findAll();

  void addUser(User user);

  void deleteUserById(int id);

  void editUser(int id, User user);

  User findUserByUsername(String username);
}
