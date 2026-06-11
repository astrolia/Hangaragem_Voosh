package org.sahthan.sahthan_v1.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.sahthan.sahthan_v1.model.Aeronave;
import org.sahthan.sahthan_v1.model.Hangar;
import org.sahthan.sahthan_v1.util.JPAUtil;

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
}
