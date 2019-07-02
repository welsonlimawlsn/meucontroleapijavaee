package com.meucontrole.api.entities;

import com.meucontrole.api.util.ApplicationDate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tbl_account_activation")
public class AccountActivation extends AbstractEntity {

    @Column(name = "cln_application_user", nullable = false)
    private ApplicationUser applicationUser;

    @Column(name = "cln_expiration_time", nullable = false)
    private LocalDateTime expirationTime;

    public AccountActivation() {
        expirationTime = ApplicationDate.localDateTimeNow().plusHours(1);
    }

    public AccountActivation(ApplicationUser applicationUser) {
        this();
        this.applicationUser = applicationUser;
    }
}
