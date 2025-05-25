package com.bike_rental.user_service.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String role;
    String username;
    String password;
    String email;

    public User() {};

    public User(String role, String username, String email, String password){
        this.role = role;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getId() {return  this.id; }
    public String getUsername() {
        return this.username;
    }
    public String getRole() {
        return this.role;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEmail() { return  this.email; }

    public void setPassword(String hashedPassword) {
        this.password = hashedPassword;
    }

}
