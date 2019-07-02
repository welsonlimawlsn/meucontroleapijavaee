package com.meucontrole.api.entities;

import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.util.ApplicationDate;
import com.meucontrole.api.util.Message;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tbl_account_activation")
public class AccountActivation extends AbstractEntity {

    @ManyToOne(optional = false)
    private ApplicationUser applicationUser;

    @Column(name = "cln_expiration_time", nullable = false)
    private LocalDateTime expirationTime = ApplicationDate.localDateTimeNow().plusHours(1);
    ;

    @Column(name = "cln_used", nullable = false)
    private Boolean used = false;

    public AccountActivation() {
    }

    public AccountActivation(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public void use() throws BadRequestException {
        if (used) {
            throw new BadRequestException(Message.LINK_DE_ATIVACAO_JA_USADO);
        }
        used = true;
    }

    public boolean isValid() {
        return expirationTime.isAfter(ApplicationDate.localDateTimeNow());
    }
}
