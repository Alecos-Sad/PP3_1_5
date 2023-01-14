package by.sadovnick.springbootfinal.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority, Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "name")
  private String name;
  @ManyToMany(mappedBy = "roleSet")
  @JsonBackReference
  private Set<User> userSet;

  public Role() {
  }

  public Role(String role) {
    this.name = role;
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

  @Override
  public String getAuthority() {
    return this.getName();
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    Role role = (Role) o;
    return id == role.id && Objects.equals(name, role.name) && Objects.equals(userSet, role.userSet);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return name;
  }
}
