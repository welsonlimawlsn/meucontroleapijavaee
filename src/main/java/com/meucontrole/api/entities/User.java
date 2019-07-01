package com.meucontrole.api.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tbl_user")
public class User extends ApplicationUser {

    @OneToMany(mappedBy = "user")
    private List<FinancialInstitution> financialInstitutions;

    @OneToMany(mappedBy = "user")
    private List<CreditCard> creditCards;

    @OneToOne(mappedBy = "user")
    private Wallet wallet;
}
