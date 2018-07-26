package sda.project.autoKomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.project.autoKomis.model.Role;
import sda.project.autoKomis.repository.RoleRepository;

import java.util.List;

@Service
public class DefaultRoleService implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return  roleRepository.findAll();
    }
}
