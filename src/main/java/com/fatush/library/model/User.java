package com.fatush.library.model;

import java.util.List;

public class User {

    private int id = 1;
    private static int idCounter = 1;
    private String name;
    private String password;
    private List<Role> roles;

    public User(String name, String password) {
        this.setId(id);
        this.name = name;
        this.password = password;
        this.roles = List.of(Role.USER);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = idCounter++;
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
