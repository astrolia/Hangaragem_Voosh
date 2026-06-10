package org.sahthan.sahthan_v1.controller.localidade;

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
import javafx.event.ActionEvent;

import java.io.IOException;
import org.sahthan.sahthan_v1.service.LocalidadeService;
import org.sahthan.sahthan_v1.model.Localidade;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VisualizarLocalidadeController implements Initializable {

    private final LocalidadeService localidadeService = new LocalidadeService();

    @FXML
    private TableView<Localidade> tableViewLocalidade;
    @FXML
    private TableColumn<Localidade, String> columnNome;
    @FXML
    private TableColumn<Localidade, Integer> columnNPistas;
    @FXML
    private TableColumn<Localidade, String> columnCidade;
    @FXML
    private TableColumn<Localidade, String> columnUF;
    

    private List<Localidade> listarLocalidade= localidadeService.listarLocalidade();

    private ObservableList<Localidade> observableListLocalidades;


    @FXML
    private void carregarLocalidades(){
        try{
            columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeAeroporto"));
            columnNPistas.setCellValueFactory(new PropertyValueFactory<>("nPistas"));
            columnCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
            columnUF.setCellValueFactory(new PropertyValueFactory<>("uf"));
        
            observableListLocalidades = FXCollections.observableArrayList(listarLocalidade);

            tableViewLocalidade.setItems(observableListLocalidades);

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
        carregarLocalidades();

        tableViewLocalidade.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarLocalidade(newValue));
    }

    public void selecionarLocalidade(Localidade localidade){

        System.out.println("Localidade selecionado: " + localidade.getId());
    }

    @FXML
    public void removerLocalidade() throws Exception {
        try{
           Localidade localidade = tableViewLocalidade.getSelectionModel().getSelectedItem();


            tableViewLocalidade.getItems().remove(localidade);
            localidadeService.deletarLocalidade(localidade);
            System.out.println("cliente removido: " + localidade.getId());
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
