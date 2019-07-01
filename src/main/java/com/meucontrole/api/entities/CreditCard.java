package com.meucontrole.api.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_credit_card")
public class CreditCard extends AbstractEntity {

    @Column(name = "cln_name", nullable = false, length = 50)
    private String name;

    @Column(name = "cln_expected_expiration_date", nullable = false)
    private LocalDate expectedExpirationDate;

    @Column(name = "cln_expected_closing_date", nullable = false)
    private LocalDate expectedClosingDate;

    @Column(name = "cln_limit", nullable = false, precision = 15, scale = 2)
    private BigDecimal limit;

    @OneToMany(mappedBy = "creditCard")
    private List<Invoice> invoices;

    @ManyToOne(optional = false)
    private User user;
}
