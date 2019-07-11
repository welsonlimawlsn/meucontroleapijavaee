package com.meucontrole.api.dao.impl;

import com.meucontrole.api.dao.RecuperacaoDeSenhaDAO;
import com.meucontrole.api.entidades.RecuperacaoDeSenha;
import com.meucontrole.api.util.DataAplicacao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class RecuperacaoDeSenhaDAOImpl extends GenericoDAOImpl<RecuperacaoDeSenha> implements RecuperacaoDeSenhaDAO {

    @PersistenceContext
    private EntityManager em;

    public RecuperacaoDeSenhaDAOImpl() {
        super(RecuperacaoDeSenha.class);
    }

    @Override
    public Optional<RecuperacaoDeSenha> procurarPorIdEExpiracaoValida(String id) {
        TypedQuery<RecuperacaoDeSenha> query = em.createQuery("SELECT pr FROM RecuperacaoDeSenha pr WHERE pr.id = :id" +
                " AND pr.expiracao > :datetime", RecuperacaoDeSenha.class);
        query.setParameter("id", id);
        query.setParameter("datetime", DataAplicacao.localDateTimeNow());
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
