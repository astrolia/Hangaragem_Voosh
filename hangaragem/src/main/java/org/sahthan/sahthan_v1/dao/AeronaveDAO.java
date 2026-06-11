package org.sahthan.sahthan_v1.dao;

import org.sahthan.sahthan_v1.model.Aeronave;
import org.sahthan.sahthan_v1.util.JPAUtil;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

@RequestScoped
public class AeronaveDAO {

    public AeronaveDAO() {
    }

    public boolean inserirAeronave(Aeronave aeronave){

        //obter gerador e fabrica
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            //começar operação
            tx.begin();

            em.persist(aeronave);

            tx.commit();
            return true;

        }catch(Exception e){

            if(tx.isActive()){
                tx.rollback();
            }
            System.out.println("ERRO ao inserir modelo: " + e.getMessage());
            return false;
        }finally {
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

    public void excluirAeronave(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // tornar monitorado
            Aeronave aeronaveGerenciada = em.find(Aeronave.class, id);

            // remove se existir
            if (aeronaveGerenciada != null) {

                // remove
                em.remove(aeronaveGerenciada);

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
            System.out.println("ERRO ao excluir modelo: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public boolean atualizarAeronave(Aeronave aeronave) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // O merge analisa o id do objeto
            // Se o id existir ele faz o UPDATE
            em.merge(aeronave);

            tx.commit();
            System.out.println("Modelo ID " + aeronave.getId() + " atualizado.");
            return true;

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("ERRO ao atualizar modelo: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    public List<Aeronave> buscarPorNome(String nomeDigitado) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            //consulta JPQL buscando por nome em Modelo
            String jpql = "SELECT m FROM Modelo m WHERE LOWER(m.nome) LIKE LOWER(:nome)";

            return em.createQuery(jpql, Aeronave.class)
                    .setParameter("nome", "%" + nomeDigitado + "%") // O % faz o papel do "contém"
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
