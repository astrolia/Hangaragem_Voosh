package org.sahthan.sahthan_v1.controller.aeronave;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.sahthan.sahthan_v1.application.MenuApllication;
import org.sahthan.sahthan_v1.model.Aeronave;
import org.sahthan.sahthan_v1.service.AeronaveService;
import javafx.event.ActionEvent;

import java.io.IOException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VisualizarAeronaveController implements Initializable {

    private final AeronaveService aeronaveService = new AeronaveService();

    @FXML
    private TableView<Aeronave> tableViewAeronave;
    @FXML
    private TableColumn<Aeronave, String> columnModelo;
    @FXML
    private TableColumn<Aeronave, String> columnMatricula;
    @FXML
    private TableColumn<Aeronave, String> columnFabricante;
    @FXML
    private TableColumn<Aeronave, Double> columnComprimento;
    @FXML
    private TableColumn<Aeronave, Double> columnAltura;
    @FXML
    private TableColumn<Aeronave, Double> columnLargura;
    @FXML
    private TableColumn<Aeronave, Double> columnPesoMedio;

    private List<Aeronave> listaAeronaves = aeronaveService.listarAeronaves();

    private ObservableList<Aeronave> observableListAeronaves;


    @FXML
    private void carregarAeronaves(){
        try{

            columnMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
            columnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
            columnFabricante.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
            columnComprimento.setCellValueFactory(new PropertyValueFactory<>("comprimento"));
            columnAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));
            columnLargura.setCellValueFactory(new PropertyValueFactory<>("largura"));
            columnPesoMedio.setCellValueFactory(new PropertyValueFactory<>("pesoMedio"));
            observableListAeronaves = FXCollections.observableArrayList(listaAeronaves);

            tableViewAeronave.setItems(observableListAeronaves);

        } catch (Exception e){
            exibirAlerta(Alert.AlertType.ERROR, "Erro no Sistema", e.getMessage());
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
        carregarAeronaves();

        tableViewAeronave.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarAeronave(newValue));
    }

    public void selecionarAeronave(Aeronave aeronave){

        System.out.println("Aeronave selecionada: " + aeronave.getMatricula());
    }

    @FXML
    public void removerAeronave() throws Exception {
        try{
            Aeronave aeronave = tableViewAeronave.getSelectionModel().getSelectedItem();


            tableViewAeronave.getItems().remove(aeronave);
            //observableListAeronaves.remove(aeronave);
            aeronaveService.deletarAeronave(aeronave);
            System.out.println("cliente removido: " + aeronave.getModelo());
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
