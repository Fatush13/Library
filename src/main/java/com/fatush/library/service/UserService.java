package com.fatush.library.service;

import com.fatush.library.dao.UserDao;
import com.fatush.library.exceptions.NotFoundException;
import com.fatush.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Collection<User> getAll() {
        return userDao.getUsers();
    }

    public User getUserById(String id) {
        return userDao.getUsers().stream()
                .filter(user -> String.valueOf(user.getId()).equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public void addNewUser(User user) {
        userDao.add(user);
    }

    public void updateUser(int id, User user) {
        userDao.update(id, user);
    }

    public void removeUser(int id) {
        userDao.remove(id);
    }

}
