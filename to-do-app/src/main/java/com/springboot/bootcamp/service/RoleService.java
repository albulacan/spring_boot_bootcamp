package com.springboot.bootcamp.service;

import com.springboot.bootcamp.model.Role;
import com.springboot.bootcamp.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }

    public Optional<Role> findById(long id) {
        return this.roleRepository.findById(id);
    }

    public Role findByName(String name) {
        return this.roleRepository.findByName(name);
    }

    public Role save(Role role) {
        return this.roleRepository.save(role);
    }

}
