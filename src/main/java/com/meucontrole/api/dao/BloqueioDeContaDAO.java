package com.meucontrole.api.dao;

import com.meucontrole.api.entidades.BloqueioDeConta;

import java.time.LocalDateTime;
import java.util.Optional;

public interface BloqueioDeContaDAO extends GenericoDAO<BloqueioDeConta> {

    Optional<BloqueioDeConta> procurarPorEmailDesdeAData(String email, LocalDateTime dateTime);

}
