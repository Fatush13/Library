package com.fatush.library.service;

import com.fatush.library.dao.UserDao;
import com.fatush.library.exceptions.NotFoundException;
import com.fatush.library.model.Role;
import com.fatush.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
        getUserById("2").setRoles(List.of(Role.ADMIN));
    }

    public Collection<User> getAll() {
        return userDao.getUsers();
    }

    public User getUserById(String id) {
        return userDao.getUsers().stream()
                .filter(user -> String.valueOf(user.getId()).equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User getUserByName(String name) {
        return userDao.getUsers().stream()
                .filter(user -> user.getName().equals(name))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public void addNewUser(User user) {
        if (!user.getName().isEmpty()) {
            if (!user.getPassword().isEmpty()) {
                userDao.add(user);
            } else throw new IllegalArgumentException("User password cannot be empty");
        } else throw new IllegalArgumentException("User name cannot be empty");
    }

    public void removeUser(int id) {
        userDao.remove(id);
    }

}
