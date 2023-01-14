package by.sadovnick.springbootfinal.repository;

import by.sadovnick.springbootfinal.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByName(String role);
}
