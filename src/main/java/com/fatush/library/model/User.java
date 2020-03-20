package com.fatush.library.model;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class User {

    private int id = 1;
    private static AtomicInteger count = new AtomicInteger(0);
    private String name;
    private String password;
    private List<Role> roles;

    public User() {
    }

    public User(String name, String password) {
        this.setId(count.incrementAndGet());
        this.name = name;
        this.password = password;
        this.roles = List.of(Role.USER);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static AtomicInteger getCount() {
        return count;
    }

    public static void setCount(AtomicInteger count) {
        User.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
