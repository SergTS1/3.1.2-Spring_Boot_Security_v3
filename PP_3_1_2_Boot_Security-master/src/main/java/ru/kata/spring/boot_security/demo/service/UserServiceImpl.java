package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserDao userDao;

    private RoleService roleService;

    private PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserDao userDao, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        // addDefaultUser();
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
//        user.setRoles(Collections.singleton(new Role(3L, "ROLE_USER")));
        userDao.saveUser(passwordCoder(user));
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(passwordCoder(user));
    }

    @Override
    public User getUserByLogin(String username) {
        return userDao.getUserByLogin(username);
    }

//    @Override
//    public void addDefaultUser() {
//        Set<Role> roleSet = new HashSet<>();
//        roleSet.add(roleService.findById(1L));
//        Set<Role> roleSet1 = new HashSet<>();
//        roleSet1.add(roleService.findById(1L));
//        roleSet1.add(roleService.findById(2L));
//        User user1 = new User( 1L, "Sergey", "Zakirov", 34, "admin", "admin");
//        User user2 = new User( 2L, "Natalya", "Kravchenco", 37, "user", "user" );
//    }

    @Override
    public User passwordCoder(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }
}
