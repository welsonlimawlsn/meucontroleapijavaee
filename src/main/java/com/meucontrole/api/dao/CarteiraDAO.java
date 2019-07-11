package com.meucontrole.api.dao;

import com.meucontrole.api.entidades.Carteira;
import com.meucontrole.api.entidades.Usuario;

import java.util.Optional;

public interface CarteiraDAO extends GenericoDAO<Carteira> {

    Optional<Carteira> procurarPorUsuario(Usuario usuario);

}
