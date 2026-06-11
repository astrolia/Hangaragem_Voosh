package org.sahthan.sahthan_v1.service;

import org.sahthan.sahthan_v1.dao.LocalidadeDAO;
import org.sahthan.sahthan_v1.model.Localidade;

import java.util.List;

public class LocalidadeService {

    LocalidadeDAO localidadeDAO = new LocalidadeDAO();


    //inserir, chama a dao, bem basico
    public void inserirLocalidade(Localidade localidade){

        localidadeDAO.inserirLocalidade(localidade);
    }

    public void deletarLocalidade(Localidade localidade) throws Exception{
        try{
            localidadeDAO.excluirLocalidade(localidade.getId());

        }catch (Exception e){

            throw new Exception("Falha ao deletar o modelo Service");
        }

    }

    //retorna uma lista com as localidades do banco
    public List<Localidade> listarLocalidade(){
        return localidadeDAO.listarLocalidade();
    }

}
