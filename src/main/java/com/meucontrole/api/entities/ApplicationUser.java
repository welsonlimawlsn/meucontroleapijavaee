package com.meucontrole.api.entities;

import com.meucontrole.api.exceptions.BadRequestException;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import static com.meucontrole.api.util.Message.CONTA_JA_ATIVADA;

@MappedSuperclass
public class ApplicationUser extends AbstractEntity {

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enabled = false;


    public void enable() throws BadRequestException {
        if (enabled) {
            throw new BadRequestException(CONTA_JA_ATIVADA);
        }
        enabled = true;
    }
}
