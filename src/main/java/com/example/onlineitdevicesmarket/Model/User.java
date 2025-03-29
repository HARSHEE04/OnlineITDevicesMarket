package com.example.onlineitdevicesmarket.Model;

import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
@Table(name="users")
public class User {



    private String name;

    @Id
    @Column(nullable = false, unique = true)
    private String email; // Using email as the primary key
    private String password;

    public User() {
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
    public enum Role {
        USER, ADMIN
    }
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }



    @Enumerated(EnumType.STRING)
    private Role role;

    //constructor to get existing user
    public User(String name, String email, String password, Role role) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
