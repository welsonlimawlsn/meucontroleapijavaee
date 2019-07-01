package com.meucontrole.api.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Transactional extends AbstractEntity {

    @OneToMany(mappedBy = "origin", cascade = REMOVE)
    private List<Transaction> transactions;
}
