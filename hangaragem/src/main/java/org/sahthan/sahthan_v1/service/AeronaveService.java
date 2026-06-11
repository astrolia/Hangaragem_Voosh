package org.sahthan.sahthan_v1.service;

import jakarta.enterprise.context.RequestScoped;
import org.sahthan.sahthan_v1.dao.AeronaveDAO;
import org.sahthan.sahthan_v1.model.Aeronave;

import java.io.Serializable;
import java.util.List;

@RequestScoped
public class AeronaveService implements Serializable {

    private final AeronaveDAO aeronaveDAO = new AeronaveDAO();

    //regra de negocio e chama dao
    public void salvarAeronave(Aeronave aeronave) throws Exception{

        if(aeronave.getMatricula() != null && !aeronave.getMatricula().matches("^(PR|PT|PS|PP)-[A-Z]{3}$")){

            throw new Exception("Matrícula inválida! Use o padrão brasileiro (Ex: PR-YSA).");

        }
        if(aeronave.getModelo() == null || aeronave.getAltura() == 0 || aeronave.getComprimento() == 0 || aeronave.getLargura() == 0 || aeronave.getPesoMedio() == 0){
            throw new Exception("Preencha todos os campos!");
        }

        else{

            aeronaveDAO.inserirAeronave(aeronave);
        }

    }

    public void deletarAeronave(Aeronave aeronave) throws Exception{
        try{
            aeronaveDAO.excluirAeronave(aeronave.getId());

        }catch (Exception e){

            throw new Exception("Falha ao deletar o modelo Service");
        }

    }

    public List<Aeronave> listarAeronaves(){
        return aeronaveDAO.listarAeronave();
    }

    public void editarAeronave(Aeronave aeronave){
        aeronaveDAO.atualizarAeronave(aeronave);
    }
}

