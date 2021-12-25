package com.example.demo.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserDto {
    private Long id;
    @NotNull
//    @Size(min = 8, max = 10, message = "error triggered")
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String username;
    private String password;
    @NotNull
    private String email;
    private String role;
    private String batch;

    public UserDto() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}