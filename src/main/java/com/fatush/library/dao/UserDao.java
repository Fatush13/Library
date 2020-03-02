package com.fatush.library.dao;

import com.fatush.library.model.Role;
import com.fatush.library.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDao {

    private static Map<Integer, User> users;

    static {
        users = new HashMap<>() {
            {
                put(1, new User(1, "Anthony", "123", Role.USER));
                put(2, new User(2, "Witcher", "asd", Role.ADMIN));
            }
        };
    }

    public Collection<User> getUsers() {
        return users.values();
    }

    public void update(int id, User user) {
        users.put(id, user);
    }

    public void remove(int id) {
        users.remove(id);
    }

    public void add(User user) {
        users.put(user.getId(), user);
    }
}
