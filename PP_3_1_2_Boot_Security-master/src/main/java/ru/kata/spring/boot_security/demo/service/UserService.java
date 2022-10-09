package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public void saveUser(User user);
    public User getUser(Long id);
    public void deleteById(Long id);
    public void updateUser(User user);
    User getUserByLogin(String username);
//    void addDefaultUser();
   User passwordCoder(User user);
}
