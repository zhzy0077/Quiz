package com.nuptsast.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Zheng on 16/7/21.
 * All Rights Reversed.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(unique = true)
    @Size(min = 6, max = 20)
    private String username;
    @NotNull
    @Size(min = 6, max = 60)
    private String password;
    @NotNull
    private String authority = "ROLE_USER";
    @NotNull
    private String targetDepartment;
    @NotNull
    private String phoneNumber;

    public User() {
    }

    public User(String username, String password, String targetDepartment, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.targetDepartment = targetDepartment;
        this.phoneNumber = phoneNumber;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTargetDepartment() {
        return targetDepartment;
    }

    public void setTargetDepartment(String targetDepartment) {
        this.targetDepartment = targetDepartment;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
