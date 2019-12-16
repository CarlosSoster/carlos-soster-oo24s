package br.edu.utfpr.pb.carlos.soster.oo24s.controller;

import br.edu.utfpr.pb.carlos.soster.oo24s.dao.ClienteDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Cliente;
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

public class FXMLClienteListaController implements Initializable {
    
    @FXML
    private TableView<Cliente> tableData;
    @FXML
    private TableColumn<Cliente, Long> columnID;
    @FXML
    private TableColumn<Cliente, String> columnNome;
    @FXML
    private TableColumn<Cliente, String> columnCpf;
    @FXML
    private TableColumn<Cliente, String> columnRg;
    @FXML
    private TableColumn<Cliente, String> columnNumPassaporte;
    
    private ClienteDao clienteDao;
    private ObservableList<Cliente> list = 
            FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.clienteDao = new ClienteDao();
        setColumnProperties();
        loadData();
    }
    
    private void setColumnProperties() {
        columnID.setCellValueFactory(
          new PropertyValueFactory<>("id")
        );
        columnNome.setCellValueFactory(
          new PropertyValueFactory<>("nome")
        );
        columnCpf.setCellValueFactory(
          new PropertyValueFactory<>("cpf")
        );
        columnRg.setCellValueFactory(
          new PropertyValueFactory<>("rg")
        );
        columnNumPassaporte.setCellValueFactory(
          new PropertyValueFactory<>("numeroPassaporte")
        );
    }

    private void loadData() {
        list.clear();
        list.addAll(clienteDao.getAll());
        
        tableData.setItems(list);
    }
    
    private void openForm(
            Cliente cliente, 
            ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                this.getClass()
                    .getResource("/fxml/FXMLClienteCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Cliente");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(
                    ((Node) event.getSource())
                            .getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            
            
            FXMLClienteCadastroController controller = 
                    loader.getController();
           
            controller.setCliente(cliente);
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
        Cliente cliente = 
                tableData.getSelectionModel()
                    .getSelectedItem();
        this.openForm(cliente, event);
    }
    
    @FXML
    private void newRecord(ActionEvent event) {
        this.openForm(new Cliente(), event);
    }
    
    @FXML
    private void delete(ActionEvent event) {
        if (tableData.getSelectionModel()
                .getSelectedIndex() >=0) {
            try {
                Cliente cliente =  tableData
                        .getSelectionModel().getSelectedItem();
                clienteDao.delete(cliente.getId());
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
