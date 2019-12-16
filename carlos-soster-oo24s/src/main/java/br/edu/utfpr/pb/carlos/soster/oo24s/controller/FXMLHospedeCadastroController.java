/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.carlos.soster.oo24s.controller;

import br.edu.utfpr.pb.carlos.soster.oo24s.dao.CategoriaDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.dao.ClienteDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.dao.ReservaDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Cliente;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Reserva;
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
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class FXMLHospedeCadastroController implements Initializable {

    @FXML
    private ComboBox<Cliente> comboHospede;
    
    private ClienteDao hospedeDao;
    private Stage stage;
    private Reserva reserva;
    private ReservaDao reservaDao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.hospedeDao = new ClienteDao();
        ObservableList<Cliente> hospedes = 
                FXCollections.observableArrayList(
                        hospedeDao.getAll()
                );
        this.comboHospede.setItems(hospedes);
    }
    
    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setReserva(Reserva reserva, ReservaDao reservaDao) {
        this.reserva = reserva;
        this.reservaDao = reservaDao;
    }
    
        @FXML
    private void cancel() {
        this.stage.close();
    }
    @FXML
    private void save() {
        reserva.addHospede(
                comboHospede.getSelectionModel()
                        .getSelectedItem());
        this.reservaDao.save(reserva);
        this.stage.close();
    }
    
}
