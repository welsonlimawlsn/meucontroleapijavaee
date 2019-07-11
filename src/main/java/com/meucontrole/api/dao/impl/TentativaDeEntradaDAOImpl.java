package com.meucontrole.api.dao.impl;

import com.meucontrole.api.dao.TentativaDeEntradaDAO;
import com.meucontrole.api.entidades.TentativaEntrada;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;

public class TentativaDeEntradaDAOImpl extends GenericoDAOImpl<TentativaEntrada> implements TentativaDeEntradaDAO {

    @PersistenceContext
    private EntityManager em;

    public TentativaDeEntradaDAOImpl() {
        super(TentativaEntrada.class);
    }

    @Override
    public long getNumeroDeTentativasPorEmailDesdeAData(String email, LocalDateTime dateTime) {
        TypedQuery<Long> query = em.createQuery("SELECT count(*) FROM TentativaEntrada asi WHERE asi.email = :email " +
                "AND asi.data > :dateTime", Long.class);
        query.setParameter("email", email);
        query.setParameter("dateTime", dateTime);
        return query.getSingleResult();
    }

}
