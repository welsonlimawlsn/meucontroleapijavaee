package com.meucontrole.api.entidades;

import com.meucontrole.api.exceptions.BadRequestException;
import com.meucontrole.api.util.DataAplicacao;
import com.meucontrole.api.util.Mensagem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class AtivacaoDeConta extends EntidadeAbstrata {

    @ManyToOne(optional = false)
    private UsuarioDaAplicacao usuarioDaAplicacao;

    @Column(nullable = false)
    private LocalDateTime expiracao = DataAplicacao.localDateTimeNow().plusHours(1);

    @Column(nullable = false)
    private Boolean usada = false;

    public AtivacaoDeConta() {
    }

    public AtivacaoDeConta(UsuarioDaAplicacao usuarioDaAplicacao) {
        this.usuarioDaAplicacao = usuarioDaAplicacao;
    }

    public void usar() throws BadRequestException {
        if (usada) {
            throw new BadRequestException(Mensagem.LINK_DE_ATIVACAO_JA_USADO);
        }
        usada = true;
    }

    public boolean ehValida() {
        return expiracao.isAfter(DataAplicacao.localDateTimeNow());
    }

}
