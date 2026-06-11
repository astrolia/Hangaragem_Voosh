package org.sahthan.sahthan_v1.controller.usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.sahthan.sahthan_v1.service.UsuarioService;

import java.io.IOException;

public class CadastroUsuarioController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private PasswordField txtConfirmarSenha;

    private final UsuarioService usuarioService =
            new UsuarioService();

    @FXML
    public void onSalvar() {

        try {

            usuarioService.cadastrarUsuario(
                    txtNome.getText(),
                    txtEmail.getText(),
                    txtSenha.getText(),
                    txtConfirmarSenha.getText()
            );

            exibirAlerta(
                    Alert.AlertType.INFORMATION,
                    "Sucesso",
                    "Usuário cadastrado com sucesso.");

            limparCampos();

        } catch (Exception e) {

            exibirAlerta(
                    Alert.AlertType.ERROR,
                    "Erro",
                    e.getMessage());
        }
    }

    @FXML
    public void voltarLogin(ActionEvent event) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/org/sahthan/sahthan_v1/GerenciarUsuario/Login.fxml"));

            Parent root = loader.load();

            Scene novaCena = new Scene(root);

            Stage stageAtual =
                    (Stage) ((Node) event.getSource())
                            .getScene()
                            .getWindow();

            stageAtual.setScene(novaCena);

            stageAtual.show();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    private void limparCampos() {

        txtNome.clear();
        txtEmail.clear();
        txtSenha.clear();
        txtConfirmarSenha.clear();
    }

    private void exibirAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensagem) {

        Alert alerta = new Alert(tipo);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);

        alerta.showAndWait();
    }
}