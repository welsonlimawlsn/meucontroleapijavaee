package com.meucontrole.api.entidades;

import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.util.DataAplicacao;
import com.meucontrole.api.util.Mensagem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class RecuperacaoDeSenha extends EntidadeAbstrata {

    private LocalDateTime expiracao = DataAplicacao.localDateTimeNow().plusMinutes(15);

    private Boolean usada = false;

    @ManyToOne
    private UsuarioDaAplicacao usuarioDaAplicacao;

    public void usar() throws BadRequestException {
        if (usada) {
            throw new BadRequestException(Mensagem.LINK_DE_RECUPERACAO_USADO);
        }
        usada = true;
    }
}
