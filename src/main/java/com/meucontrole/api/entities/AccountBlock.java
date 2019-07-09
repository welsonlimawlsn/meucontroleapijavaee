package com.meucontrole.api.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tbl_account_block")
public class AccountBlock extends AbstractEntity {

    @Column(name = "cln_email", nullable = false)
    private String email;

    @Column(name = "cln_expiration", nullable = false)
    private LocalDateTime expiration;

}
