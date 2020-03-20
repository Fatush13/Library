package com.fatush.library.dao;

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
                put(1, new User("user", "123"));
                put(2, new User("admin", "asd"));
            }
        };
    }

    public Collection<User> getUsers() {
        return users.values();
    }

    public void add(User user) {
        users.put(User.getCount().intValue() + 1, new User(user.getName(), user.getPassword()));
    }

    public void remove(int id) {
        users.remove(id);
    }
}
