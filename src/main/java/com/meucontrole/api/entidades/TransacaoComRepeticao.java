package com.meucontrole.api.entidades;

import com.meucontrole.api.enums.TipoDeTransacao;
import com.meucontrole.api.exceptions.BadRequestException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.meucontrole.api.util.Mensagem.QUANTIDADE_MINIMA_TRANSACAO_COM_REPETICAO;

@Getter
@Setter
@Entity
public class TransacaoComRepeticao extends EntidadeAbstrata {

    @Column(nullable = false)
    private Integer numberRepetitions;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal value;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate initialExpirationDate;

    @ManyToOne
    private Categoria categoria;

    @Column(nullable = false)
    private TipoDeTransacao tipo;

    @OneToMany(mappedBy = "transacaoComRepeticao", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;

    @PrePersist
    private void prePersist() throws BadRequestException {
        if (transacoes.size() < 2) {
            throw new BadRequestException(QUANTIDADE_MINIMA_TRANSACAO_COM_REPETICAO);
        }
    }

}
