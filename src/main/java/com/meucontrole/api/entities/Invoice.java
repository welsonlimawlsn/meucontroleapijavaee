package com.meucontrole.api.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tbl_invoice")
public class Invoice extends Transactional {

    @Column(name = "cln_competence", nullable = false)
    private LocalDate competence;

    @Column(name = "cln_expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "cln_closing_date", nullable = false)
    private LocalDate closingDate;

    @Column(name = "cln_payment_date")
    private LocalDate paymentDate;

    @ManyToOne(optional = false)
    private CreditCard creditCard;

}
