package com.meucontrole.api.entities;

import com.meucontrole.api.enums.TransactionType;
import com.meucontrole.api.exceptions.BadRequestException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.meucontrole.api.util.Message.QUANTIDADE_MINIMA_TRANSACAO_COM_REPETICAO;

@Entity
public class RepeatTransaction extends AbstractEntity {

    @Column(nullable = false)
    private Integer numberRepetitions;

    @Column(nullable = false, precision = 2)
    private BigDecimal value;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate initialExpirationDate;

    @Column(nullable = false)
    private TransactionType type;

    @OneToMany(mappedBy = "repeatTransaction", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @PrePersist
    private void prePersist() throws BadRequestException {
        if (transactions.size() < 2) {
            throw new BadRequestException(QUANTIDADE_MINIMA_TRANSACAO_COM_REPETICAO);
        }
    }
}
