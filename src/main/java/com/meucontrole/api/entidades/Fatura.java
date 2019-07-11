package com.meucontrole.api.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Fatura extends Transacionavel {

    @Column(nullable = false)
    private LocalDate competencia;

    @Column(nullable = false)
    private LocalDate dataDeVencimento;

    @Column(nullable = false)
    private LocalDate dataDeFechamento;

    private LocalDate dataDePagamento;

    @ManyToOne(optional = false)
    private CartaoDeCredito cartaoDeCredito;

}
