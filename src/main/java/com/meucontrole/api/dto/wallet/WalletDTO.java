package com.meucontrole.api.dto.wallet;

import com.meucontrole.api.entities.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WalletDTO {

    private List<Transaction> transactions;

}
