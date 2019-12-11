package com.railvayticketiffice.entity;

import com.railvayticketiffice.enums.Role;

public class User implements Identified<Integer> {

    public User() {
    }

    public User(int id, String login, String password, Role role, String name, String surname, double funds) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.funds = funds;
    }

    private int id;

    private String login;

    private String password;

    private Role role;

    private String name;

    private String surname;

    private double funds;

    public double getFunds() {
        return funds;
    }

    public void setFunds(double founds) {
        this.funds = founds;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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
