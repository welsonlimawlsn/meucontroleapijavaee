package com.meucontrole.api.entidades;

import com.meucontrole.api.enums.TipoDeTransacao;
import com.meucontrole.api.exceptions.BadRequestException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.meucontrole.api.util.Mensagem.*;
import static java.time.ZoneOffset.UTC;

@Getter
@Setter
@Entity
public class Transacao extends EntidadeAbstrata {

    private LocalDate dateDePagamento;

    private LocalDate dataDeVencimento;

    @Column(nullable = false)
    private String descricao;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal valor;

    @ManyToOne
    private Categoria categoria;

    @Column(nullable = false)
    private TipoDeTransacao tipo;

    @ManyToOne(optional = false)
    private Transacionavel origem;

    @ManyToOne
    private TransacaoComRepeticao transacaoComRepeticao;

    @PrePersist
    private void prePersist() throws BadRequestException {
        if (tipo == TipoDeTransacao.DESPESA && valor.signum() == 1) {
            throw new BadRequestException(DESPESA_PRECISA_SER_NEGATIVA);
        }

        if (tipo == TipoDeTransacao.RECEITA && valor.signum() == -1) {
            throw new BadRequestException(RECEITA_PRECISA_SER_POSITIVO);
        }
    }

    public void pagar(LocalDate dataDePagamento) throws BadRequestException {
        if (this.dateDePagamento != null) {
            throw new BadRequestException(TRANSACAO_JA_CONSOLIDADA);
        }
        this.dateDePagamento = dataDePagamento;
    }

    public void pagar() throws BadRequestException {
        pagar(LocalDate.now(UTC));
    }

}
