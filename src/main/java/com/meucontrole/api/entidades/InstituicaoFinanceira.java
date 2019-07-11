package com.meucontrole.api.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class InstituicaoFinanceira extends Transacionavel {

    @Column(nullable = false, length = 50)
    private String nome;

    @ManyToOne(optional = false)
    private Usuario usuario;

}
