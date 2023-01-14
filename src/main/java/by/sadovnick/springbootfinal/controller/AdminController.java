package by.sadovnick.springbootfinal.controller;

import by.sadovnick.springbootfinal.dto.UserDto;
import by.sadovnick.springbootfinal.exceptions.BadResponse;
import by.sadovnick.springbootfinal.exceptions.UserNotFoundException;
import by.sadovnick.springbootfinal.models.User;
import by.sadovnick.springbootfinal.service.MapperService;
import by.sadovnick.springbootfinal.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/gateway")
public class AdminController {

  public final UserService userService;
  private final MapperService mapperService;

  @Autowired
  public AdminController(UserService userService,
      MapperService mapperService) {
    this.userService = userService;
    this.mapperService = mapperService;
  }

  @GetMapping
  public List<UserDto> getAllUsers() {
    return userService.findAll()
        .stream()
        .map(mapperService::mapUserToUserDto).collect(Collectors.toList());
  }

  @PostMapping("/new")
  public ResponseEntity<HttpStatus> addNewUser(@RequestBody UserDto userDto) {
    User user = mapperService.mapUserDtoToUser(userDto);
    userService.addUser(user);
    System.out.println("CREATE");
    return ResponseEntity.ok(HttpStatus.CREATED);
  }

  @PatchMapping("/edit/{id}")
  public ResponseEntity<HttpStatus> editUser(@RequestBody UserDto userDto, @PathVariable("id") int id) {
    System.out.println("EDIT START");
    userService.editUser(id, mapperService.mapUserDtoToUser(userDto));
    System.out.println("EDIT STOP");
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
    userService.deleteUserById(id);
    System.out.println("DELETED");
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @ExceptionHandler
  private ResponseEntity<BadResponse> handleException(UserNotFoundException exception) {
    BadResponse badResponse = new BadResponse("User not found");
    return new ResponseEntity<>(badResponse, HttpStatus.NOT_FOUND);
  }
}
