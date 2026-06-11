package org.sahthan.sahthan_v1.controller.locacao;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.sahthan.sahthan_v1.application.MenuApllication;
import org.sahthan.sahthan_v1.model.*;
import org.sahthan.sahthan_v1.service.HangarService;
import org.sahthan.sahthan_v1.service.LocacaoService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.sahthan.sahthan_v1.model.StatusHangar.DISPONIVEL;

public class VisualizarLocacaoController implements Initializable {

    private final LocacaoService locacaoService = new LocacaoService();
    private final HangarService hangarService = new HangarService();

    @FXML
    private TableView<Locacao> tableViewLocacao;
    @FXML
    private TableColumn<Locacao, String> columnLocalidade;
    @FXML
    private TableColumn<Locacao, String> columnHangar;
    @FXML
    private TableColumn<Locacao, String> columnModelo;
    @FXML
    private TableColumn<Locacao, String> columnMatricula;
    @FXML
    private TableColumn<Locacao, String> columnCheckin;
    @FXML
    private TableColumn<Locacao, String> columnCheckout;
    @FXML
    private TableColumn<Locacao, Double> columnValor;

    private List<Locacao> listaLocacoes;

    private ObservableList<Locacao> observableListLocacoes;

    @FXML
    private void carregarLocacoes(){
        try {

            listaLocacoes = locacaoService.listarLocacao();
            if (listaLocacoes == null) {
                listaLocacoes = new ArrayList<>();
            }

            // Mapeamento de propriedades Simples (direto da classe Locacao)
            columnCheckin.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
            columnCheckout.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
            columnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

            // Mapeamento de propriedades Complexas (Objetos dentro de Locacao)
            // Se Locacao tem um "getHangar()", e Hangar tem um "getNome()":
            columnHangar.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getHangar().getNome()));

            // Se Hangar tem uma "getLocalidade()", e Localidade tem um "getNomeAeroporto()":
            columnLocalidade.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getHangar().getLocalidade().getNomeAeroporto()));

            // Se Locacao tem uma "getAeronave()", e Aeronave tem "getModelo()" e "getMatricula()":
            columnModelo.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getAeronave().getModelo()));

            columnMatricula.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getAeronave().getMatricula()));


            observableListLocacoes = FXCollections.observableArrayList(listaLocacoes);
            tableViewLocacao.setItems(observableListLocacoes);

        } catch (Exception e){
            e.printStackTrace();
            exibirAlerta(Alert.AlertType.ERROR, "Erro no Sistema", "Falha ao carregar tabela: " + e.getMessage());
        }
    }

    private void exibirAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregarLocacoes();
    }

    @FXML
    public void removerLocacao() throws Exception {
        try{
            Locacao locacao = tableViewLocacao.getSelectionModel().getSelectedItem();

            locacao.getHangar().setStatusHangar(DISPONIVEL);
            hangarService.atualizarHangar(locacao.getHangar());


            tableViewLocacao.getItems().remove(locacao);
            //observableListAeronaves.remove(aeronave);
            locacaoService.deletarLocacao(locacao);
            System.out.println("Locacao removida: " + locacao.getHangar());
        }catch(Exception e){
            exibirAlerta(Alert.AlertType.ERROR, "Erro no Sistema", e.getMessage());
        }
    }

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
