package org.sahthan.sahthan_v1.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.sahthan.sahthan_v1.model.Hangar;
import org.sahthan.sahthan_v1.util.JPAUtil;
import java.util.List;

public class HangarDAO {

    // inserir no banco novo hangar
    public void inserirHangar(Hangar hangar){
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
            try{
            tx.begin();

            em.persist(hangar);

            tx.commit();

        }catch (Exception e){

            if(tx.isActive()){
                tx.rollback();
            }
            System.out.println("ERRO ao inserir hangar: " + e.getMessage());

        }finally {
            em.close();
        }

    }

    //atualiza
    public boolean atualizarHangar(Hangar hangar) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // O merge analisa o id do objeto
            // Se o id existir ele faz o UPDATE
            em.merge(hangar);

            tx.commit();
            System.out.println("Modelo ID " + hangar.getId() + " atualizado.");
            return true;

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("ERRO ao atualizar hangar: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }


    public void excluirHangar(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // tornar monitorado
            Hangar hangarGerenciada = em.find(Hangar.class, id);

            // remove se existir
            if (hangarGerenciada != null) {

                // remove
                em.remove(hangarGerenciada);

                tx.commit();
                System.out.println("ID " + id + " excluída.");
            } else {
                System.out.println("ID " + id + " não encontrada para exclusão.");
                tx.rollback();
            }

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("ERRO ao excluir hangar: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    public List<Hangar> listarHangar(){
        EntityManager em = JPAUtil.getEntityManager();

        try{
            String jpql = "SELECT l FROM Hangar l";

            List<Hangar> lista = em.createQuery(jpql, Hangar.class).getResultList();
            return lista;
        }catch(Exception e){
            System.out.println("ERRO ao listar hangar: " + e.getMessage());
            return null;
        }finally{
            em.close();
        }
    }
}
