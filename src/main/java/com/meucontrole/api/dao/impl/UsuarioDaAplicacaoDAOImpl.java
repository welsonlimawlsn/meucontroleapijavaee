package com.meucontrole.api.dao.impl;

import com.meucontrole.api.dao.UsuarioDaAplicacaoDAO;
import com.meucontrole.api.entidades.Usuario;
import com.meucontrole.api.entidades.UsuarioDaAplicacao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class UsuarioDaAplicacaoDAOImpl extends GenericoDAOImpl<UsuarioDaAplicacao> implements UsuarioDaAplicacaoDAO {

    @PersistenceContext
    private EntityManager em;

    public UsuarioDaAplicacaoDAOImpl() {
        super(UsuarioDaAplicacao.class);
    }

    @Override
    public Optional<UsuarioDaAplicacao> procurarPorEmailESenha(String email, String senha) {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :password", Usuario.class);
        query.setParameter("email", email);
        query.setParameter("password", senha);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UsuarioDaAplicacao> procurarPorEmail(String email) {
        TypedQuery<UsuarioDaAplicacao> query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", UsuarioDaAplicacao.class);
        query.setParameter("email", email);
        try {
            UsuarioDaAplicacao result = query.getSingleResult();
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
