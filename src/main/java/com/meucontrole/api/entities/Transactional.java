package com.meucontrole.api.entities;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tbl_transactional")
public class Transactional extends AbstractEntity {

    @OneToMany(mappedBy = "origin", cascade = REMOVE)
    private List<Transaction> transactions;
}
