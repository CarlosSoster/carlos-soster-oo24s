/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.carlos.soster.oo24s.controller;

import br.edu.utfpr.pb.carlos.soster.oo24s.dao.ReservaDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.dao.UsuarioDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Cliente;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Reserva;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.exolab.castor.types.DateTime;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class FXMLReservaListaController implements Initializable {

    @FXML
    private TableView<Reserva> tableData;
    @FXML
    private TableColumn<Reserva, Long> columnId;
    @FXML
    private TableColumn<Reserva, Cliente> columnCliente;
    @FXML
    private TableColumn<Reserva, DateTime> columnDataReserva;
    @FXML
    private TableColumn<Reserva, DateTime> columnDataEntrada;
    @FXML
    private TableColumn<Reserva, DateTime> columnDataSaida;
    
    private ReservaDao reservaDao;
    private ObservableList<Reserva> list = 
            FXCollections.observableArrayList();
    
    private Usuario usuario;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.reservaDao = new ReservaDao();
        setColumnProperties();
        loadData();
    }
    
    private void setColumnProperties() {
        columnId.setCellValueFactory(
          new PropertyValueFactory<>("id")
        );
        columnCliente.setCellValueFactory(
          new PropertyValueFactory<>("cliente")
        );
        columnDataReserva.setCellValueFactory(
          new PropertyValueFactory<>("dataReserva")
        );
        columnDataEntrada.setCellValueFactory(
          new PropertyValueFactory<>("dataEntrada")
        );
        columnDataSaida.setCellValueFactory(
          new PropertyValueFactory<>("dataSaida")
        );
    }

    private void loadData() {
        list.clear();
        list.addAll(reservaDao.getAll());
        tableData.setItems(list);
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    private void openForm(
            Reserva reserva, 
            ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                this.getClass()
                    .getResource("/fxml/FXMLReservaCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Reserva");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(
                    ((Node) event.getSource())
                            .getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            
            FXMLReservaCadastroController controller = 
                    loader.getController();
            controller.setReserva(reserva, usuario);
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();
            
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro ao abrir "
                    + "a janela de cadastro!");
            alert.setContentText("Por favor, tente realizar "
                    + "a operação novamente!");
            alert.showAndWait();
        }
        loadData();
    }
    
    @FXML
    private void edit(ActionEvent event) {
        Reserva reserva = 
                tableData.getSelectionModel()
                    .getSelectedItem();
        this.openForm(reserva, event);
    }
    
    @FXML
    private void newRecord(ActionEvent event) {
        this.openForm(new Reserva(), event);
    }
    
    @FXML
    private void delete(ActionEvent event) {
        if (tableData.getSelectionModel()
                .getSelectedIndex() >=0) {
            try {
                Reserva reserva =  tableData
                        .getSelectionModel().getSelectedItem();
                reservaDao.delete(reserva.getId());
                tableData.getItems().remove(
                        tableData.getSelectionModel()
                                    .getSelectedIndex());
                                       
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro "
                    + " ao remover o registro!");
            alert.setContentText("Por favor, tente realizar "
                    + "a operação novamente!");
            alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum registro "
                    + "selecionado");
            alert.setContentText("Por favor, "
                    + "selecione um registro "
                    + "na tabela!");
            alert.showAndWait();
        }
    }
    
}
