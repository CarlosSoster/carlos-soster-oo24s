/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.carlos.soster.oo24s.controller;

import br.edu.utfpr.pb.carlos.soster.oo24s.dao.ClienteDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.dao.ContatoDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Cliente;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Contato;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.EOperadora;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.ETipoContato;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class FXMLContatoCadastroController implements Initializable {

    @FXML 
    private TextField textTelefone;
    @FXML
    private ComboBox<EOperadora> comboOperadora;
    @FXML
    private RadioButton radioResidencial;
    @FXML
    private RadioButton radioCelular;
    
    private ClienteDao clienteDao;
    private Contato contato;
    private Stage stage;
    private Cliente cliente;
    private ContatoDao contatoDao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.clienteDao = new ClienteDao();
        this.contatoDao = new ContatoDao();
        this.comboOperadora.setItems(FXCollections.observableArrayList(EOperadora.values()));
        this.radioResidencial.setSelected(true);
    }    
    
    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setContato(Contato contato, Cliente cliente) {
        this.contato = contato;
        this.cliente = cliente;
        if (contato.getId() != null) {
            textTelefone.setText(contato.getTelefone());
            comboOperadora.setValue(contato.getOperadora());
            if(contato.getTipoContato() == ETipoContato.CELULAR){
                radioCelular.setSelected(true);
            }else{
                radioResidencial.setSelected(true);
            }
        }
    }
    
    @FXML
    private void cancel() {
        this.stage.close();
    }
    @FXML
    private void save() {
        try{
            
            contato.setTelefone(textTelefone.getText());
            contato.setOperadora(
                    comboOperadora.getSelectionModel()
                            .getSelectedItem());
            contato.setTipoContato(radioCelular.isSelected()? ETipoContato.CELULAR : ETipoContato.RESIDENCIAL);
            contato.setCliente(cliente);
            if(contato.getId() != null){
                contatoDao.save(contato);
            }else{
                List<Contato> contatos = cliente.getContatos();
                contatos.add(contato);
                cliente.setContatos(contatos);
            }
            
            this.clienteDao.save(cliente);
            this.stage.close();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro "
                    + " ao salvar o registro!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    
}
