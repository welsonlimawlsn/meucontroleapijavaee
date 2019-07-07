package com.meucontrole.api.entities;

import com.meucontrole.api.enums.TransactionType;
import com.meucontrole.api.exceptions.BadRequestException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.meucontrole.api.util.Message.QUANTIDADE_MINIMA_TRANSACAO_COM_REPETICAO;

@Getter
@Setter
@Entity
@Table(name = "tbl_repeat_transaction")
public class RepeatTransaction extends AbstractEntity {

    @Column(name = "cln_number_repetitions", nullable = false)
    private Integer numberRepetitions;

    @Column(name = "cln_value", nullable = false, precision = 15, scale = 2)
    private BigDecimal value;

    @Column(name = "cln_description", nullable = false)
    private String description;

    @Column(name = "cln_initial_expiration_date", nullable = false)
    private LocalDate initialExpirationDate;

    @ManyToOne
    private Category category;

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
