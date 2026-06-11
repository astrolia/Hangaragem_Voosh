package org.sahthan.sahthan_v1.controller.hangar;

import javafx.beans.property.SimpleStringProperty;
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
import org.sahthan.sahthan_v1.model.Hangar;
import org.sahthan.sahthan_v1.model.Locacao;
import org.sahthan.sahthan_v1.model.Localidade;
import org.sahthan.sahthan_v1.service.HangarService;
import javafx.event.ActionEvent;

import java.io.IOException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VisualizarHangarController implements Initializable {

    private final HangarService hangarService = new HangarService();

    @FXML
    private TableView<Hangar> tableViewHangar;
    @FXML
    private TableColumn<Hangar, String> columnNomeHangar;
    @FXML
    private TableColumn<Hangar, String> columnAltura;
    @FXML
    private TableColumn<Hangar, String> columnLargura;
    @FXML
    private TableColumn<Hangar, Double> columnComprimento;
    @FXML
    private TableColumn<Hangar, String> columnStatusHangar;
    @FXML
    private TableColumn<Hangar, String> columnLocalidade;

    private List<Hangar> listarHangar= hangarService.listarHangar();

    private ObservableList<Hangar> observableListHangares;


    @FXML
    private void carregarHangares(){
        try{
            columnNomeHangar.setCellValueFactory(new PropertyValueFactory<>("nome"));
            columnAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));
            columnLargura.setCellValueFactory(new PropertyValueFactory<>("largura"));
            columnComprimento.setCellValueFactory(new PropertyValueFactory<>("comprimento"));
            columnStatusHangar.setCellValueFactory(cellData -> {
                String status = cellData.getValue().getStatusHangar().name();
                String statusFormatado = status.equals("DISPONIVEL") ? "Disponível" : "Locado";
                return new javafx.beans.property.SimpleStringProperty(statusFormatado);
            });

            columnLocalidade.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getLocalidade().getNomeAeroporto()));

            observableListHangares = FXCollections.observableArrayList(listarHangar);

            tableViewHangar.setItems(observableListHangares);

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
        carregarHangares();

        tableViewHangar.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarHangar(newValue));
    }

    public void selecionarHangar(Hangar hangar){

        System.out.println("Hangar selecionado: " + hangar.getId());
    }

    @FXML
    public void removerHangar() throws Exception {
        try{
            Hangar hangar = tableViewHangar.getSelectionModel().getSelectedItem();


            tableViewHangar.getItems().remove(hangar);
            hangarService.deletarHangar(hangar);
            System.out.println("cliente removido: " + hangar.getId());
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
