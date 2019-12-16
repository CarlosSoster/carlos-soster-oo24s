package br.edu.utfpr.pb.carlos.soster.oo24s.controller;

import br.edu.utfpr.pb.carlos.soster.oo24s.dao.ClienteDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Cliente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLClienteCadastroController implements Initializable {

    @FXML
    private TextField textID;
    @FXML 
    private TextField textNome;
    @FXML
    private TextField textCpf;
    @FXML
    private TextField textRg;
    @FXML
    private TextField textNumeroPassaporte;

    
    private ClienteDao clienteDao;
    private Stage stage;
    private Cliente cliente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.clienteDao = new ClienteDao();
    }
    
    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        if (cliente.getId() != null) {
            textID.setText(cliente.getId().toString());
            textNome.setText(cliente.getNome());
            textCpf.setText(cliente.getCpf());
            textRg.setText(cliente.getRg());
            textNumeroPassaporte.setText(cliente.getNumeroPassaporte());
        }
    }
    @FXML
    private void cancel() {
        this.stage.close();
    }
    @FXML
    private void save() {
        cliente.setNome(textNome.getText());
        cliente.setCpf(textCpf.getText());
        cliente.setRg(textRg.getText());
        cliente.setNumeroPassaporte(textNumeroPassaporte.getText());
        this.clienteDao.save(cliente);
        this.stage.close();
    }
    
}
