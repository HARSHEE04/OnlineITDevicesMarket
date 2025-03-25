package com.example.onlineitdevicesmarket.Model;

import jakarta.persistence.*;

import javax.management.relation.Role;

@Entity
@Table(name="users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uId;
    private String name;
    private String email;
    private String password;

    public User() {
    }

    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public enum Role {
        USER, ADMIN
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    //constructor to get existing user
    public User(int id, String name, String email, String password, Role role) {
        this.uId = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
