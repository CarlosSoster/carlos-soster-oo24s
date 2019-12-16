/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.carlos.soster.oo24s.controller;

import br.edu.utfpr.pb.carlos.soster.oo24s.dao.QuartoDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.ETipoQuarto;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Produto;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Quarto;
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

public class FXMLQuartoListaController implements Initializable {

    @FXML
    private TableView<Quarto> tableData;
    @FXML
    private TableColumn<Quarto, Long> columnId;
    @FXML
    private TableColumn<Quarto, Long> columnNumero;
    @FXML
    private TableColumn<Quarto, ETipoQuarto> columnTipo;
    @FXML
    private TableColumn<Quarto, Integer> columnQtdeCamas;
    @FXML
    private TableColumn<Quarto, Integer> columnQtdePessoas;
    @FXML
    private TableColumn<Quarto, Double> columnValorDiaria;
    
    private QuartoDao quartoDao;
    private ObservableList<Quarto> list = 
            FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.quartoDao = new QuartoDao();
        setColumnProperties();
        loadData();
    }
    
    private void setColumnProperties() {
        columnId.setCellValueFactory(
          new PropertyValueFactory<>("id")
        );
        columnNumero.setCellValueFactory(
          new PropertyValueFactory<>("numero")
        );
        columnTipo.setCellValueFactory(
          new PropertyValueFactory<>("tipoQuarto")
        );
        columnQtdeCamas.setCellValueFactory(
          new PropertyValueFactory<>("qtdeCamas")
        );
        columnQtdePessoas.setCellValueFactory(
          new PropertyValueFactory<>("qtdePessoas")
        );
        columnValorDiaria.setCellValueFactory(
          new PropertyValueFactory<>("valorDiaria")
        );
        
    }

    private void loadData() {
        list.clear();
        list.addAll(quartoDao.getAll());
        
        tableData.setItems(list);
    }
    
    private void openForm(
            Quarto quarto, 
            ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                this.getClass()
                    .getResource("/fxml/FXMLQuartoCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Quartos");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(
                    ((Node) event.getSource())
                            .getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            
            FXMLQuartoCadastroController controller = 
                    loader.getController();
            controller.setQuarto(quarto);
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
        Quarto quarto = 
                tableData.getSelectionModel()
                    .getSelectedItem();
        this.openForm(quarto, event);
    }
    
    @FXML
    private void newRecord(ActionEvent event) {
        this.openForm(new Quarto(), event);
    }
    
    @FXML
    private void delete(ActionEvent event) {
        if (tableData.getSelectionModel()
                .getSelectedIndex() >=0) {
            try {
                Quarto quarto =  tableData
                        .getSelectionModel().getSelectedItem();
                quartoDao.delete(quarto.getId());
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
