package org.sahthan.sahthan_v1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    //nomes bem intuitivos, funções carregadas nos botões para iniciarem outras telas
    @FXML
    public void irParaCadastrarAeronave(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/sahthan/sahthan_v1/GerenciarAeronave/CadastrarAeronave.fxml"));
            Parent root = loader.load();

            Scene novaCena = new Scene(root);

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageAtual.setScene(novaCena);
            stageAtual.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void irParaVisualizarAeronave(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/sahthan/sahthan_v1/GerenciarAeronave/VisualizarAeronave.fxml"));
            Parent root = loader.load();

            Scene novaCena = new Scene(root);


            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageAtual.setScene(novaCena);
            stageAtual.show();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    public void irParaCadastrarLocalidade(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/sahthan/sahthan_v1/GerenciarLocalidade/CadastrarLocalidade.fxml"));
            Parent root = loader.load();

            Scene novaCena = new Scene(root);

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageAtual.setScene(novaCena);
            stageAtual.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     @FXML
    public void irParaVisualizarLocalidade(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/sahthan/sahthan_v1/GerenciarLocalidade/VisualizarLocalidade.fxml"));
            Parent root = loader.load();

            Scene novaCena = new Scene(root);


            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageAtual.setScene(novaCena);
            stageAtual.show();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    public void irParaCadastrarHangar(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/sahthan/sahthan_v1/GerenciarHangar/CadastrarHangar.fxml"));
            Parent root = loader.load();

            Scene novaCena = new Scene(root);

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageAtual.setScene(novaCena);
            stageAtual.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     @FXML
    public void irParaVisualizarHangar(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/sahthan/sahthan_v1/GerenciarHangar/VisualizarHangar.fxml"));
            Parent root = loader.load();

            Scene novaCena = new Scene(root);


            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageAtual.setScene(novaCena);
            stageAtual.show();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    public void irParaCadastrarLocacao(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/sahthan/sahthan_v1/GerenciarLocacao/CadastrarLocacao.fxml"));
            Parent root = loader.load();

            Scene novaCena = new Scene(root);

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageAtual.setScene(novaCena);
            stageAtual.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void irParaVisualizarLocacao(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/sahthan/sahthan_v1/GerenciarLocacao/VisualizarLocacao.fxml"));
            Parent root = loader.load();

            Scene novaCena = new Scene(root);

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageAtual.setScene(novaCena);
            stageAtual.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}