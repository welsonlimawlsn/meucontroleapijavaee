package com.meucontrole.api.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class TentativaEntrada extends EntidadeAbstrata {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDateTime data;

}
