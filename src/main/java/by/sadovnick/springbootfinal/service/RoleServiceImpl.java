package by.sadovnick.springbootfinal.service;

import by.sadovnick.springbootfinal.exceptions.RoleNotFoundException;
import by.sadovnick.springbootfinal.models.Role;
import by.sadovnick.springbootfinal.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role findRoleById(int id) {
        return roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);
    }
}
