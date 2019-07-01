package com.meucontrole.api.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_wallet")
public class Wallet extends Transactional {
    @OneToOne
    private User user;
}
