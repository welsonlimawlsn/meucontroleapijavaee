package com.meucontrole.api.entities;

import com.meucontrole.api.enums.TransactionType;
import com.meucontrole.api.exceptions.BadRequestException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.meucontrole.api.util.Message.*;
import static java.time.ZoneOffset.UTC;

@Entity
public class Transaction extends AbstractEntity {

    private LocalDate paymentDate;
    private LocalDate expirationDate;

    @Column(nullable = false)
    private String description;

    @Column(precision = 2, nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    private TransactionType type;

    @ManyToOne(optional = false)
    private Transactional origin;

    @ManyToOne
    private RepeatTransaction repeatTransaction;

    @PrePersist
    private void prePersist() throws BadRequestException {
        if (type == TransactionType.EXPENSE && value.signum() == 1) {
            throw new BadRequestException(DESPESA_PRECISA_SER_NEGATIVA);
        }

        if (type == TransactionType.INCOME && value.signum() == -1) {
            throw new BadRequestException(RECEITA_PRECISA_SER_POSITIVO);
        }
    }

    public void pay(LocalDate paymentDate) throws BadRequestException {
        if (paymentDate != null) {
            throw new BadRequestException(TRANSACAO_JA_CONSOLIDADA);
        }
        this.paymentDate = paymentDate;
    }

    public void pay() throws BadRequestException {
        pay(LocalDate.now(UTC));
    }

}
