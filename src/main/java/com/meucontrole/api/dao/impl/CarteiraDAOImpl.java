package com.meucontrole.api.dao.impl;

import com.meucontrole.api.dao.CarteiraDAO;
import com.meucontrole.api.entidades.Carteira;
import com.meucontrole.api.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class CarteiraDAOImpl extends GenericoDAOImpl<Carteira> implements CarteiraDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public CarteiraDAOImpl() {
        super(Carteira.class);
    }

    @Override
    public Optional<Carteira> procurarPorUsuario(Usuario usuario) {
        TypedQuery<Carteira> query = entityManager.createQuery("SELECT w FROM Carteira w WHERE w.user = :user", Carteira.class);
        query.setParameter("user", usuario);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
