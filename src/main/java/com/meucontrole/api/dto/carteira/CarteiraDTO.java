package com.meucontrole.api.dto.carteira;

import com.meucontrole.api.entidades.Transacao;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CarteiraDTO {

    private List<Transacao> trasacoes;

}
