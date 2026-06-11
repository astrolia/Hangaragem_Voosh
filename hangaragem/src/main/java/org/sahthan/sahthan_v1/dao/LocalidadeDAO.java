package org.sahthan.sahthan_v1.dao;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.sahthan.sahthan_v1.model.Aeronave;
import org.sahthan.sahthan_v1.model.Localidade;
import org.sahthan.sahthan_v1.util.JPAUtil;

import java.util.List;

@RequestScoped
public class LocalidadeDAO {

    public LocalidadeDAO() {
    }

    //inserir nova localidade no banco
    public void inserirLocalidade(Localidade localidade){
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();

            em.persist(localidade);

            tx.commit();

        }catch (Exception e){

            if(tx.isActive()){
                tx.rollback();
            }
            System.out.println("ERRO ao inserir localidade: " + e.getMessage());

        }finally {
            em.close();
        }

    }

    //lista localidades do banco
    public List<Localidade> listarLocalidade(){
        EntityManager em = JPAUtil.getEntityManager();

        try{
            String jpql = "SELECT l FROM Localidade l";

            List<Localidade> lista = em.createQuery(jpql, Localidade.class).getResultList();
            return lista;
        }catch(Exception e){
            System.out.println("ERRO ao listar localidade: " + e.getMessage());
            return null;
        }finally{
            em.close();
        }

    }

}
