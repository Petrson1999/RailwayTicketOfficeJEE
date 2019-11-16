package com.vladimirpetrenkodev.railvayticketiffice.entity;

import inerfaces.Identified;

public class User implements Identified<Integer> {

    public User() {
    }

    public User(int id, String login, String password, String role, String name, String surname) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.name = name;
        this.surname = surname;
    }

    private int id;

    private String login;

    private String password;

    private String role;

    private String name;

    private String surname;

    @Override
    public Integer getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
