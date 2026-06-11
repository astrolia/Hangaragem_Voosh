package org.sahthan.sahthan_v1.service;

import org.sahthan.sahthan_v1.dao.LocalidadeDAO;
import org.sahthan.sahthan_v1.model.Aeronave;
import org.sahthan.sahthan_v1.model.Localidade;

import java.util.List;

public class LocalidadeService {

    LocalidadeDAO localidadeDAO = new LocalidadeDAO();


    //inserir, chama a dao, bem basico
    public void inserirLocalidade(Localidade localidade){

        localidadeDAO.inserirLocalidade(localidade);

    }

    //retorna uma lista com as localidades do banco
    public List<Localidade> listarLocalidade(){
        return localidadeDAO.listarLocalidade();
    }



}
