package by.sadovnick.springbootfinal.dto;

import by.sadovnick.springbootfinal.models.User;
import java.util.Set;

public class RoleDto {

  private int id;
  private String name;
  private Set<User> userSet;

  public RoleDto() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<User> getUserSet() {
    return userSet;
  }

  public void setUserSet(Set<User> userSet) {
    this.userSet = userSet;
  }
}
