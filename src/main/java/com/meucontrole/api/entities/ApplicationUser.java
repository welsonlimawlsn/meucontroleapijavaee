package com.meucontrole.api.entities;

import com.meucontrole.api.exceptions.BadRequestException;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import static com.meucontrole.api.util.Message.CONTA_JA_ATIVADA;

@MappedSuperclass
public class ApplicationUser extends AbstractEntity {

    @Column(name = "cln_first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "cln_last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "cln_email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "cln_password", nullable = false)
    private String password;

    @Column(name = "cln_enabled", nullable = false)
    private Boolean enabled = false;


    public void enable() throws BadRequestException {
        if (enabled) {
            throw new BadRequestException(CONTA_JA_ATIVADA);
        }
        enabled = true;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
