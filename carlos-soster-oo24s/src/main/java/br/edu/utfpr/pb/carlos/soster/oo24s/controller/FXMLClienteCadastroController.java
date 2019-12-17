package br.edu.utfpr.pb.carlos.soster.oo24s.controller;

import br.edu.utfpr.pb.carlos.soster.oo24s.dao.CidadeDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.dao.ClienteDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Cidade;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Cliente;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Contato;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.EOperadora;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.ETipoContato;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.ReservaProduto;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
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
    @FXML
    private ComboBox<Cidade> comboCidade;
    @FXML
    private TableView<Contato> tableContatos;
    @FXML
    private TableColumn<Contato, String> columnTelefone;
    @FXML
    private TableColumn<Contato, EOperadora> columnOperadora;
    @FXML
    private TableColumn<Contato, ETipoContato> columnTipo;
    
    private ObservableList<Contato> listContatos = 
            FXCollections.observableArrayList();
    
    private ClienteDao clienteDao;
    private Stage stage;
    private Cliente cliente;
    private CidadeDao cidadeDao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.clienteDao = new ClienteDao();
        this.cidadeDao = new CidadeDao();
        ObservableList<Cidade> cidades = 
                FXCollections.observableArrayList(
                        cidadeDao.getAll()
                );
        comboCidade.setItems(cidades);
        setColumnContato();
        loadContatos();
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
            comboCidade.setValue(cliente.getCidade());
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
        cliente.setCidade(comboCidade.getSelectionModel().getSelectedItem());
        this.clienteDao.save(cliente);
        this.stage.close();
    }
    
    @FXML
    private void newRecord(ActionEvent event) {
        this.openForm(new Contato(), event);
    }
    
    @FXML
    private void edit(ActionEvent event) {
        Contato contato = 
                tableContatos.getSelectionModel()
                    .getSelectedItem();
        this.openForm(contato, event);
    }
    
    @FXML
    private void delete(ActionEvent event) {
        if (tableContatos.getSelectionModel()
                .getSelectedIndex() >=0) {
            try {
                List<Contato> contatos = cliente.getContatos();
                Contato contato =  tableContatos
                        .getSelectionModel().getSelectedItem();
                contatos.remove(contato);
                cliente.setContatos(contatos);
                clienteDao.save(cliente);
                tableContatos.getItems().remove(
                        tableContatos.getSelectionModel()
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
    
    private void openForm(
            Contato contato, 
            ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                this.getClass()
                    .getResource("/fxml/FXMLContatoCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Contato");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(
                    ((Node) event.getSource())
                            .getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            
            FXMLContatoCadastroController controller = 
                    loader.getController();
            controller.setContato(contato, cliente);
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
        loadContatos();
    }
    
    private void setColumnContato() {
        columnTelefone.setCellValueFactory(
          new PropertyValueFactory<>("telefone")
        );
        columnOperadora.setCellValueFactory(
          new PropertyValueFactory<>("operadora")
        );
        columnTipo.setCellValueFactory(
          new PropertyValueFactory<>("tipoContato")
        );
    }
    
    private void loadContatos() {
        listContatos.clear();
        if (cliente != null) {
            listContatos.addAll(cliente.getContatos());
            tableContatos.setItems(listContatos);
        }
    }
}
