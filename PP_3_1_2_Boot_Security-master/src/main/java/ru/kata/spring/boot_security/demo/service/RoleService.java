package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();
    void addRole(Role role);
    Role findById(Long id);
    Set<Role> findByIdRoles(List<Long> roles);
//    void addDefaultRole();
}
