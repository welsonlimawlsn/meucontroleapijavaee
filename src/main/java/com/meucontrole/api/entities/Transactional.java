package com.meucontrole.api.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tbl_transactional")
public class Transactional extends AbstractEntity {

    @OneToMany(mappedBy = "origin", cascade = REMOVE, fetch = EAGER)
    private List<Transaction> transactions;
}
