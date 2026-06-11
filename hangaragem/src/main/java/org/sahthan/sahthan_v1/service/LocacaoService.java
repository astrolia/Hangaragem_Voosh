package org.sahthan.sahthan_v1.service;

import org.sahthan.sahthan_v1.dao.LocacaoDAO;
import org.sahthan.sahthan_v1.model.Aeronave;
import org.sahthan.sahthan_v1.model.Locacao;
import org.sahthan.sahthan_v1.model.Localidade;

import java.util.List;

import static org.sahthan.sahthan_v1.model.StatusHangar.LOCADO;

public class LocacaoService {

    LocacaoDAO locacaoDAO = new LocacaoDAO();

    //regra de negocio e chama a dao para inserir
    public void inserirlocacao(Locacao locacao) throws Exception{

        if(locacao.getHangar().getStatusHangar() == LOCADO){
            throw new Exception("Hangar já está locado.");
        }
        if(locacao.getCheckIn() != null && !locacao.getCheckIn().matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([12][0-9]{3})$"))
        {
            throw new Exception("Data inválida, use o padrão DD/MM/AAAA");
        }

        if(locacao.getCheckOut() != null && !locacao.getCheckOut().matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([12][0-9]{3})$"))
        {
            throw new Exception("Data inválida, use o padrão DD/MM/AAAA");
        }

        if(locacao.getHangar().getAltura() <= locacao.getAeronave().getAltura() ||
                locacao.getHangar().getLargura() <= locacao.getAeronave().getLargura() ||
                locacao.getHangar().getComprimento() <= locacao.getAeronave().getComprimento()){

            throw new Exception("Aeronave não pode ser comportada neste Hangar.");
        } else{
            locacao.getHangar().setStatusHangar(LOCADO);
            locacaoDAO.inserirLocacao(locacao);
        }


    }

    //listas de localidade aeronave e locacao
    public List<Localidade> listarLocalidade(){
        return locacaoDAO.listarLocalidade();
    }

    public List<Aeronave> listarAeronaves(){
        return locacaoDAO.listarAeronave();
    }

    public List<Locacao> listarLocacao(){
        return locacaoDAO.listarLocacao();
    }

    //deleta
    public void deletarLocacao(Locacao locacao) throws Exception{
        try{
            locacaoDAO.excluirLocacao(locacao.getId());

        }catch (Exception e){

            throw new Exception("Falha ao deletar o modelo Service");
        }

    }

}
