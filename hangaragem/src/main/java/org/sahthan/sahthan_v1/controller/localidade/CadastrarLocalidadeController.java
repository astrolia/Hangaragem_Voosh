package org.sahthan.sahthan_v1.controller.localidade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.sahthan.sahthan_v1.application.MenuApllication;
import org.sahthan.sahthan_v1.model.Localidade;
import org.sahthan.sahthan_v1.service.LocalidadeService;

import java.io.IOException;

public class CadastrarLocalidadeController {

    //atributos da tela
    @FXML
    private TextField txtNomeAeroporto;
    @FXML
    private TextField txtnPistas;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtUF;

    //service
    private final LocalidadeService localidadeService = new LocalidadeService();


    //Função para salvar no banco de dados chamando a service
    @FXML
    private void onSalvar() {
        try {

            //carrega os valores escritos
            String nomeAeroporto = txtNomeAeroporto.getText();
            String uf = txtCidade.getText();
            String cidade = txtUF.getText();
            int nPistas = Integer.parseInt(txtnPistas.getText());

            //intancia uma localidade para ser cadastrada
            Localidade novaLocalidade = new Localidade(nomeAeroporto, nPistas, cidade, uf);

            //service
            localidadeService.inserirLocalidade(novaLocalidade);

            //conclusões
            exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Localidade cadastrada com sucesso!");
            onLimpar();

        }catch (Exception e) {
            exibirAlerta(Alert.AlertType.ERROR, "Erro no Sistema", e.getMessage());
        }
    }

    //limpa os campos especificados da tela
    @FXML
    private void onLimpar() {
        txtNomeAeroporto.clear();
        txtnPistas.clear();
        txtCidade.clear();
        txtUF.clear();
    }

    //exibe alerta na tela
    private void exibirAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    //volta para o menu principal
    @FXML
    public void voltarMenu(ActionEvent event) {
        try {
            MenuApllication app = new MenuApllication();

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app.start(stageAtual);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
