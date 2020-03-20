package com.fatush.library.service;

import com.fatush.library.dao.UserDao;
import com.fatush.library.exceptions.NotFoundException;
import com.fatush.library.model.Role;
import com.fatush.library.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    private final Logger logger = LogManager.getLogger(this.getClass());


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
                .orElseThrow(() -> new NotFoundException("User with id: " + id + " is not found"));
    }

    public User getUserByName(String name) {
        return userDao.getUsers().stream()
                .filter(user -> user.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Username " + name + " is incorrect"));
    }

    public String addNewUser(User user) {
        if (user.getName() != null) {
            if (!user.getName().isEmpty()) {
                if (user.getPassword() != null) {
                    if (!user.getPassword().isEmpty()) {
                        userDao.add(user);
                        logger.warn("New user " + user.getName() + " has been successfully added");

                        return "User \"" + user.getName() + "\" has been successfully added";
                    } else {
                        logger.error("Prevented adding new user with empty password");

                        return "User password cannot be empty";
                    }
                } else {
                    logger.error("Prevented adding new user without password parameter");

                    return "User password cannot be empty";
                }
            } else {
                logger.error("Prevented adding new user with empty username");

                return "Username cannot be empty";
            }
        } else {
            logger.error("Prevented adding new user without username parameter");

            return "Username cannot be empty";
        }
    }

    public void removeUser(int id) {
        userDao.remove(id);
        logger.warn("User " + getUserById(Integer.toString(id)).getName() + " has been removed");
    }
}
