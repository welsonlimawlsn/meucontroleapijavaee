package com.meucontrole.api.entities;

import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.util.ApplicationDate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDate;

import static com.meucontrole.api.util.Message.CONTA_JA_ATIVADA;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ApplicationUser extends AbstractEntity {

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

    @Column(name = "cln_account_creation_date", nullable = false)
    private LocalDate accountCreationDate = ApplicationDate.localDateNow();

    public void enable() throws BadRequestException {
        if (enabled) {
            throw new BadRequestException(CONTA_JA_ATIVADA);
        }
        enabled = true;
    }

}
