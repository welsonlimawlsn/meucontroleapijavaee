package com.meucontrole.api.dto;

import com.meucontrole.api.entities.User;

public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static UserDTO getDTOObject(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.id = user.getId();
        userDTO.firstName = user.getFirstName();
        userDTO.lastName = user.getLastName();
        userDTO.email = user.getEmail();
        return userDTO;
    }
}
