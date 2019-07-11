package com.meucontrole.api.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Transacionavel extends EntidadeAbstrata {

    @OneToMany(mappedBy = "origem", cascade = REMOVE, fetch = EAGER)
    private List<Transacao> transacoes;

}
