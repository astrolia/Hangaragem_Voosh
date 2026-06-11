package org.sahthan.sahthan_v1.service;

import org.sahthan.sahthan_v1.dao.HangarDAO;
import org.sahthan.sahthan_v1.model.Hangar;

public class HangarService {

    private final HangarDAO hangarDAO = new HangarDAO();

    //regras de negocio e chama a dao
    public void inserirHangar(Hangar hangar) throws Exception{

        if(hangar.getAltura() == 0 || hangar.getComprimento() == 0 || hangar.getLargura() == 0 || hangar.getLocalidade() == null){

            throw new Exception("Preencha todos os campos!");
        }else{
            hangarDAO.inserirHangar(hangar);
        }

    }

    //atualiza
    public void atualizarHangar(Hangar hangar){

        hangarDAO.atualizarHangar(hangar);

    }
}
