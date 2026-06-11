package org.sahthan.sahthan_v1.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.sahthan.sahthan_v1.model.Aeronave;
import org.sahthan.sahthan_v1.model.Locacao;
import org.sahthan.sahthan_v1.model.Localidade;
import org.sahthan.sahthan_v1.util.JPAUtil;

import java.util.List;

public class LocacaoDAO {


    public void inserirLocacao(Locacao locacao){

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{

            tx.begin();

            em.persist(locacao);

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

    public List<Aeronave> listarAeronave(){
        EntityManager em = JPAUtil.getEntityManager();

        try{
            String jpql = "SELECT m FROM Aeronave m";

            List<Aeronave> lista = em.createQuery(jpql, Aeronave.class).getResultList();
            return lista;
        }catch(Exception e){
            System.out.println("ERRO ao listar aeronave: " + e.getMessage());
            return null;
        }finally{
            em.close();
        }

    }

    public List<Locacao> listarLocacao(){
        EntityManager em = JPAUtil.getEntityManager();

        try{
            String jpql = "SELECT m FROM Locacao m";

            List<Locacao> lista = em.createQuery(jpql, Locacao.class).getResultList();
            return lista;
        }catch(Exception e){
            System.out.println("ERRO ao listar aeronave: " + e.getMessage());
            return null;
        }finally{
            em.close();
        }

    }

    public void excluirLocacao(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // tornar monitorado
            Locacao locacaoGerenciada = em.find(Locacao.class, id);

            // remove se existir
            if (locacaoGerenciada != null) {

                // remove
                em.remove(locacaoGerenciada);

                tx.commit();
                System.out.println("Modelo ID " + id + " excluída.");
            } else {
                System.out.println("Modelo ID " + id + " não encontrada para exclusão.");
                tx.rollback();
            }

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("ERRO ao excluir locacao: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
