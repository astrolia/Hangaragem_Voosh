package org.sahthan.sahthan_v1.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.sahthan.sahthan_v1.model.Usuario;
import org.sahthan.sahthan_v1.util.JPAUtil;

public class UsuarioDAO {

    public void inserirUsuario(Usuario usuario) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

            tx.begin();

            em.persist(usuario);

            tx.commit();

        } catch (Exception e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            throw e;

        } finally {
            em.close();
        }
    }

    public Usuario autenticar(String email, String senhaHash) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT u FROM Usuario u " +
                    "WHERE u.email = :email " +
                    "AND u.senha = :senha";

            return em.createQuery(jpql, Usuario.class)
                    .setParameter("email", email)
                    .setParameter("senha", senhaHash)
                    .getSingleResult();

        } catch (Exception e) {

            return null;

        } finally {
            em.close();
        }
    }

    public boolean existeEmail(String email) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT COUNT(u) FROM Usuario u " +
                    "WHERE u.email = :email";

            Long quantidade = em.createQuery(jpql, Long.class)
                    .setParameter("email", email)
                    .getSingleResult();

            return quantidade > 0;

        } finally {
            em.close();
        }
    }
}