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
import org.sahthan.sahthan_v1.model.Usuario;
import org.sahthan.sahthan_v1.service.UsuarioService;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    private final UsuarioService usuarioService =
            new UsuarioService();

    @FXML
    public void onEntrar() {

        try {

            String email = txtEmail.getText();
            String senha = txtSenha.getText();

            Usuario usuario =
                    usuarioService.autenticar(
                            email,
                            senha);

            if(usuario == null){

                exibirAlerta(
                        Alert.AlertType.ERROR,
                        "Erro",
                        "Email ou senha inválidos.");

                return;
            }

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/org/sahthan/sahthan_v1/Menu/Menu.fxml"));

            Parent root = loader.load();

            Stage stage =
                    (Stage) txtEmail.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Menu");

            stage.show();

        } catch (Exception e) {

            exibirAlerta(
                    Alert.AlertType.ERROR,
                    "Erro",
                    e.getMessage());
        }
    }

    @FXML
    public void irParaCadastro(ActionEvent event) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/org/sahthan/sahthan_v1/GerenciarUsuario/CadastroUsuario.fxml"));

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