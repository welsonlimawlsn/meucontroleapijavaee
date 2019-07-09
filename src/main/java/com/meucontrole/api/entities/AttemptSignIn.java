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
@Table(name = "tbl_attempt_sign_in")
public class AttemptSignIn extends AbstractEntity {

    @Column(name = "cln_email", nullable = false)
    private String email;

    @Column(name = "cln_datetime", nullable = false)
    private LocalDateTime dateTime;

}
