package org.sahthan.sahthan_v1.controller.locacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.sahthan.sahthan_v1.application.MenuApllication;
import org.sahthan.sahthan_v1.model.*;
import org.sahthan.sahthan_v1.service.HangarService;
import org.sahthan.sahthan_v1.service.LocacaoService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static org.sahthan.sahthan_v1.model.StatusHangar.LOCADO;

public class CadastrarLocacaoController implements Initializable {

    @FXML
    private TextField txtCheckin;
    @FXML
    private TextField txtCheckout;
    @FXML
    private TextField txtValor;

    private final LocacaoService locacaoService = new LocacaoService();
    private final HangarService hangarService = new HangarService();

    @FXML
    private ComboBox<Localidade> comboLocalidade;
    @FXML
    private ComboBox<Hangar> comboHangar;
    @FXML
    private ComboBox<Aeronave> comboAeronave;


    //listas
    private List<Localidade> listaLocalidade;
    private List<Hangar> listaHangar;
    private List<Aeronave> listaAeronave;

    //observer lists
    private ObservableList<Localidade> observableListLocalidade;
    private ObservableList<Aeronave> observableListAeronave;
    private ObservableList<Hangar> observableListHangar;

    //carrega combo de localidade e aeronave
    public void carregarComboBox(){

        listaLocalidade = locacaoService.listarLocalidade();
        listaAeronave = locacaoService.listarAeronaves();

        observableListLocalidade = FXCollections.observableArrayList(listaLocalidade);
        observableListAeronave = FXCollections.observableArrayList(listaAeronave);

        comboLocalidade.setItems(observableListLocalidade);
        comboAeronave.setItems(observableListAeronave);

    }

    //carrega combo hangar
    public void carregarComboHangar(){
        Localidade localidade = comboLocalidade.getValue();

        listaHangar = localidade.getHangares();
        observableListHangar = FXCollections.observableArrayList(listaHangar);
        comboHangar.setItems(observableListHangar);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregarComboBox();

        //inicializa combo do hangar dps de selecionada a localidade
        comboLocalidade.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {carregarComboHangar();});

        //tableViewAeronave.getSelectionModel().selectedItemProperty().addListener(
        //(observable, oldValue, newValue) -> selecionarAeronave(newValue));
    }

    @FXML
    private void onSalvar() {
        try {

            double valor = Double.parseDouble(txtValor.getText());
            String checkIn = txtCheckin.getText();
            String checkOut = txtCheckout.getText();

            Localidade localidade = comboLocalidade.getSelectionModel().getSelectedItem();
            Aeronave aeronave = comboAeronave.getSelectionModel().getSelectedItem();
            Hangar hangar = comboHangar.getSelectionModel().getSelectedItem();


            hangarService.atualizarHangar(hangar);
            Locacao novaLocacao = new Locacao(localidade, hangar, aeronave, checkIn, checkOut, valor);


            locacaoService.inserirlocacao(novaLocacao);


            exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Locação feita com sucesso!");
            onLimpar();


        }catch (Exception e) {
            exibirAlerta(Alert.AlertType.ERROR, "Erro no Sistema", e.getMessage());
        }
    }

    @FXML
    private void onLimpar() {
        txtValor.clear();
        txtCheckout.clear();
        txtCheckin.clear();
        comboLocalidade.getSelectionModel().clearSelection();
        comboAeronave.getSelectionModel().clearSelection();
        comboHangar.getSelectionModel().clearSelection();
    }

    private void exibirAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
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
