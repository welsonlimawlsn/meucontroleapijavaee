package com.meucontrole.api.dao;

import com.meucontrole.api.entidades.EntidadeAbstrata;

import java.util.List;
import java.util.Optional;

public interface GenericoDAO<E extends EntidadeAbstrata> {

    Optional<E> procurarPorId(String id);

    E inserir(E entidade);

    E alterar(E entidade);

    void remover(E entidade);

    List<E> listarTodos();

}
