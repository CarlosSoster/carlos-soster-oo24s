/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.carlos.soster.oo24s.controller;

import br.edu.utfpr.pb.carlos.soster.oo24s.dao.ClienteDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.dao.ProdutoDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.dao.QuartoDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.dao.ReservaDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.dao.ReservaProdutoDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Cliente;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.ETipoProduto;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Produto;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Quarto;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Reserva;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.ReservaProduto;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Usuario;
import java.net.URL;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class FXMLReservaCadastroController implements Initializable {

    @FXML
    private TextField textID;
    @FXML
    private Label labelUsuario;
    @FXML 
    private ComboBox<Quarto> comboQuarto;
    @FXML
    private ComboBox<Cliente> comboCliente;
    @FXML
    private TextField textMotivo;
    @FXML
    private TextField textValorDiaria;
    @FXML
    private DatePicker dateReserva;
    @FXML
    private DatePicker dateEntrada;
    @FXML
    private DatePicker dateSaida;
    
    @FXML
    private TableView<Cliente> tableHospedes;
    @FXML
    private TableColumn<Cliente, String> columnNome;
    
    private ClienteDao hospedeDao;
    private ObservableList<Cliente> listHospedes = 
            FXCollections.observableArrayList();
    
    @FXML
    private TableView<ReservaProduto> tableServicos;
    @FXML
    private TableColumn<ReservaProduto, Produto> columnServico;
    @FXML
    private TableColumn<ReservaProduto, Integer> columnQuantidadeServico;
    @FXML
    private TableColumn<ReservaProduto, Double> columnValorUnitarioServico;
    
    private ReservaProdutoDao servicoDao;
    private ObservableList<ReservaProduto> listServicos = 
            FXCollections.observableArrayList();
    
    @FXML
    private TableView<ReservaProduto> tableProdutos;
    @FXML
    private TableColumn<ReservaProduto, Produto> columnProduto;
    @FXML
    private TableColumn<ReservaProduto, Integer> columnQuantidade;
    @FXML
    private TableColumn<ReservaProduto, Double> columnValorUnitario;
    
    private ReservaProdutoDao produtoDao;
    private ObservableList<ReservaProduto> listProdutos = 
            FXCollections.observableArrayList();
    
    private ReservaDao reservaDao;
    private QuartoDao quartoDao;
    private ClienteDao clienteDao;
    private Stage stage;
    private Reserva reserva;
    private ReservaProduto servico;
    private ReservaProduto produto;
    private Usuario usuario;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.produtoDao = new ReservaProdutoDao();
        this.servicoDao = new ReservaProdutoDao();
        this.reservaDao = new ReservaDao();
        this.clienteDao = new ClienteDao();
        this.quartoDao = new QuartoDao();
        this.hospedeDao = new ClienteDao();
        
        ObservableList<Quarto> quartos = 
                FXCollections.observableArrayList(
                        quartoDao.getAll()
                );
        this.comboQuarto.setItems(quartos);
        
        ObservableList<Cliente> clientes = 
                FXCollections.observableArrayList(
                        clienteDao.getAll()
                );
        this.comboCliente.setItems(clientes);
        setColumnHospede();
        setColumnServico();
        setColumnProduto();
        loadHospedes();
        loadServicos();
        loadProdutos();
    }
    
    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
    
    private void setColumnHospede() {
        columnNome.setCellValueFactory(
          new PropertyValueFactory<>("nome")
        );
    }
    
    private void setColumnServico() {
        columnServico.setCellValueFactory(
          new PropertyValueFactory<>("produto")
        );
        columnQuantidadeServico.setCellValueFactory(
          new PropertyValueFactory<>("quantidade")
        );
        columnValorUnitarioServico.setCellValueFactory(
          new PropertyValueFactory<>("valor")
        );
        
    }
    
    private void setColumnProduto() {
        columnProduto.setCellValueFactory(
          new PropertyValueFactory<>("produto")
        );
        columnQuantidade.setCellValueFactory(
          new PropertyValueFactory<>("quantidade")
        );
        columnValorUnitario.setCellValueFactory(
          new PropertyValueFactory<>("valor")
        );
        
    }
    
    public void setReserva(Reserva reserva, Usuario usuario) {
        this.reserva = reserva;
        this.usuario = usuario;
        if (reserva.getId() != null) {
            textID.setText(reserva.getId().toString());
            comboQuarto.setValue(reserva.getQuarto());
            comboCliente.setValue(reserva.getCliente());
            textMotivo.setText(reserva.getMotivo());
            textValorDiaria.setText(reserva.getValorDiaria().toString());
            dateReserva.setValue(reserva.getDataReserva());
            labelUsuario.setText(reserva.getUsuario().getNome());
            if (reserva.getDataEntrada() != null) {
                dateEntrada.setValue(reserva.getDataEntrada());
            }
            if (reserva.getDataSaida() != null) {
                dateSaida.setValue(reserva.getDataSaida());
            }
            loadHospedes();
            loadServicos();
            loadProdutos();
        }else{
            labelUsuario.setText(this.usuario.getNome());
        }
    }
    @FXML
    private void cancel() {
        this.stage.close();
    }
    @FXML
    private void save() {
        try {
            reserva.setCliente(
                comboCliente.getSelectionModel()
                        .getSelectedItem());
            reserva.setMotivo(textMotivo.getText());
            reserva.setValorDiaria(Double.parseDouble(textValorDiaria.getText()));
            reserva.setDataReserva(dateReserva.getValue());
            reserva.setDataEntrada(dateEntrada.getValue());
            reserva.setDataSaida(dateSaida.getValue());
            if (reservaDao.validateQuarto(reserva.getDataReserva(), reserva.getDataSaida(),
                    comboQuarto.getSelectionModel().getSelectedItem())) {
                reserva.setQuarto(
                    comboQuarto.getSelectionModel()
                            .getSelectedItem());
            } else{
                throw new Exception("O quarto selecionado já pertence a outra reserva!");
            }
            if (reserva.getId() == null){
               reserva.setUsuario(usuario);
            }
            this.reservaDao.save(reserva);
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
    
    @FXML
    private void newHospede(ActionEvent event) {
        this.openFormHospedes(new Cliente(), event);
    }
    
    @FXML
    private void deleteHospede(ActionEvent event) {
        if (tableHospedes.getSelectionModel()
                .getSelectedIndex() >=0) {
            try {
                Set<Cliente> hospedes = reserva.getHospedes();
                Cliente cliente =  tableHospedes
                        .getSelectionModel().getSelectedItem();
                hospedes.remove(cliente);
                reserva.setHospedes(hospedes);
                reservaDao.save(reserva);
                tableHospedes.getItems().remove(
                        tableHospedes.getSelectionModel()
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
    
    @FXML
    private void newServico(ActionEvent event) {
        this.openFormServicos(new ReservaProduto(), event);
    }
    
    @FXML
    private void editServico(ActionEvent event) {
         ReservaProduto servico = 
                tableServicos.getSelectionModel()
                    .getSelectedItem();
        this.openFormServicos(servico, event);
    }
    
    @FXML
    private void deleteServico(ActionEvent event) {
        if (tableServicos.getSelectionModel()
                .getSelectedIndex() >=0) {
            try {
                ReservaProduto servico =  tableServicos
                        .getSelectionModel().getSelectedItem();
                servicoDao.delete(servico.getId());
                tableServicos.getItems().remove(
                        tableServicos.getSelectionModel()
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
    
    @FXML
    private void newProduto(ActionEvent event) {
        this.openFormProdutos(new ReservaProduto(), event);
    }
    
    @FXML
    private void editProduto(ActionEvent event) {
        ReservaProduto produto = 
                tableServicos.getSelectionModel()
                    .getSelectedItem();
        this.openFormServicos(produto, event);
    }
    
    @FXML
    private void deleteProduto(ActionEvent event) {
        if (tableProdutos.getSelectionModel()
                .getSelectedIndex() >=0) {
            try {
                ReservaProduto produtos =  tableProdutos
                        .getSelectionModel().getSelectedItem();
                produtoDao.delete(produto.getId());
                tableProdutos.getItems().remove(
                        tableProdutos.getSelectionModel()
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
    
    private void openFormHospedes(
            Cliente hospede, 
            ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                this.getClass()
                    .getResource("/fxml/FXMLHospedeCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Hospede");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(
                    ((Node) event.getSource())
                            .getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            
            FXMLHospedeCadastroController controller = 
                    loader.getController();
           
            controller.setReserva(reserva, reservaDao);
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
        loadHospedes();
    }
    
    private void loadHospedes() {
        listHospedes.clear();
        if (reserva != null) {
            listHospedes.addAll(reserva.getHospedes());
            tableHospedes.setItems(listHospedes);
        }
    }
    
    private void openFormServicos(
            ReservaProduto servico, 
            ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                this.getClass()
                    .getResource("/fxml/FXMLReservaServicoCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Servico");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(
                    ((Node) event.getSource())
                            .getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            FXMLReservaServicoCadastroController controller = 
                    loader.getController();
           
            controller.setServico(servico, reserva);
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
        loadServicos();
    }
    
    private void loadServicos() {
        listServicos.clear();
        if (reserva != null) {
            listServicos.addAll(servicoDao.findProdutoByTipo(ETipoProduto.SERVICO));
            tableServicos.setItems(listServicos);
        }
    }   
    
    private void openFormProdutos(
            ReservaProduto produto, 
            ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                this.getClass()
                    .getResource("/fxml/FXMLReservaProdutoCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Produto");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(
                    ((Node) event.getSource())
                            .getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            FXMLReservaProdutoCadastroController controller = 
                    loader.getController();
           
            controller.setProduto(produto, reserva);
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
        loadProdutos();
    }
    
    private void loadProdutos() {
        listProdutos.clear();
        if (reserva != null) {
            listProdutos.addAll(produtoDao.findProdutoByTipo(ETipoProduto.PRODUTO));
            tableProdutos.setItems(listProdutos);
        }
    }
}
