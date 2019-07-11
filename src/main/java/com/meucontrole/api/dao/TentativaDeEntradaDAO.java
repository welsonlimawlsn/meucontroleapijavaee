package com.meucontrole.api.dao;

import com.meucontrole.api.entidades.TentativaEntrada;

import java.time.LocalDateTime;

public interface TentativaDeEntradaDAO extends GenericoDAO<TentativaEntrada> {

    long getNumeroDeTentativasPorEmailDesdeAData(String email, LocalDateTime dateTime);

}
