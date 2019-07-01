package com.meucontrole.api.dto;

import com.meucontrole.api.entities.User;

public class NewUserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User convertToObject() {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
