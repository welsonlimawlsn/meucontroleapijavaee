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
@Table(name = "tbl_password_recovery")
public class PasswordRecovery extends AbstractEntity {

    @Column(name = "cln_expiration")
    private LocalDateTime expiration = ApplicationDate.localDateTimeNow().plusMinutes(15);

    @Column(name = "cln_used")
    private Boolean used = false;

    @ManyToOne
    private ApplicationUser applicationUser;

    public void use() throws BadRequestException {
        if (used) {
            throw new BadRequestException(Message.LINK_DE_RECUPERACAO_USADO);
        }
        used = true;
    }
}
