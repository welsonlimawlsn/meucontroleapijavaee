package com.meucontrole.api.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

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


    public void enable() throws Exception {
        if (enabled) {
            throw new Exception("Essa conta já está ativado.");
        }
        enabled = true;
    }
}
