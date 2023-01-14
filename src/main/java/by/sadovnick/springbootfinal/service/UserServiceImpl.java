package by.sadovnick.springbootfinal.service;

import by.sadovnick.springbootfinal.exceptions.UserNotFoundException;
import by.sadovnick.springbootfinal.models.User;
import by.sadovnick.springbootfinal.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  public final UserRepository userRepository;
  public final RoleService roleService;
  public final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepository,
      RoleService roleService,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleService = roleService;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional(readOnly = true)
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  @Transactional
  public void addUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
  }

  @Override
  @Transactional
  public void deleteUserById(int id) {
    userRepository.deleteById(id);
  }

  @Override
  @Transactional
  public void editUser(int id, User user) {
    user.setId(id);
    Optional<User> tempUser = userRepository.findById(id);
    if (user.getRoleSet().isEmpty()) {
      tempUser.ifPresent(u -> user.setRoleSet(u.getRoleSet()));
    }
    if (user.getPassword().isEmpty()) {
      tempUser.ifPresent(u -> user.setPassword(u.getPassword()));
    }
    addUser(user);
  }

  @Override
  @Transactional
  public User findUserByUsername(String username) {
    return userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
  }
}
