package com.meucontrole.api.entidades;

import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.util.DataAplicacao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDate;

import static com.meucontrole.api.util.Mensagem.CONTA_JA_ATIVADA;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class UsuarioDaAplicacao extends EntidadeAbstrata {

    @Column(nullable = false, length = 20)
    private String nome;

    @Column(nullable = false, length = 20)
    private String sobrenome;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private Boolean ativa = false;

    @Column(nullable = false)
    private LocalDate dataDeCriacao = DataAplicacao.localDateNow();

    public void ativar() throws BadRequestException {
        if (ativa) {
            throw new BadRequestException(CONTA_JA_ATIVADA);
        }
        ativa = true;
    }

}
