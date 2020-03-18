package com.fatush.library.controller;

import com.fatush.library.model.User;
import com.fatush.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/user")
@RestController
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Collection<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    public User getOneUser(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        userService.addNewUser(user);

        return user;
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable int id) {
        userService.removeUser(id);
    }

}
