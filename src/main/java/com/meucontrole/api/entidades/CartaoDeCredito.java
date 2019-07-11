package com.meucontrole.api.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class CartaoDeCredito extends EntidadeAbstrata {

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataDeVencimentoEsperada;

    @Column(nullable = false)
    private LocalDate dataDeFechametoExperada;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal limite;

    @OneToMany(mappedBy = "cartaoDeCredito")
    private List<Fatura> faturas;

    @ManyToOne(optional = false)
    private Usuario usuario;

}
