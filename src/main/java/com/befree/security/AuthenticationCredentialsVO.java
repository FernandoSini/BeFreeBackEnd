package com.befree.security;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AuthenticationCredentialsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("user_name")
    private String username;
    @JsonProperty("password")
    private String password;

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
}
