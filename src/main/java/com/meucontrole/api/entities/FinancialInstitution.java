package com.meucontrole.api.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tbl_financial_institution")
public class FinancialInstitution extends Transactional {

    @Column(name = "cln_name", nullable = false, length = 50)
    private String name;

    @ManyToOne(optional = false)
    private User user;

}
