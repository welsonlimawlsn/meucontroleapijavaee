package com.meucontrole.api.dto.user;

import com.meucontrole.api.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
