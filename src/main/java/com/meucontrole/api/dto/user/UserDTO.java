package com.meucontrole.api.dto.user;

import com.meucontrole.api.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public static UserDTO getDTOObject(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.id = user.getId();
        userDTO.firstName = user.getFirstName();
        userDTO.lastName = user.getLastName();
        userDTO.email = user.getEmail();
        return userDTO;
    }
}
