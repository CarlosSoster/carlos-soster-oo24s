/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.carlos.soster.oo24s.controller;

import br.edu.utfpr.pb.carlos.soster.oo24s.dao.ClienteDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.dao.ProdutoDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.dao.ReservaDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.dao.ReservaProdutoDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Cliente;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.ETipoProduto;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Produto;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Reserva;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.ReservaProduto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class FXMLReservaServicoCadastroController implements Initializable {

    @FXML
    private ComboBox<Produto> comboServico;
    @FXML
    private TextField textQuantidade;
    @FXML
    private TextField textValor;
    
    private ProdutoDao produtoDao;
    private ReservaProdutoDao reservaProdutoDao;
    private ReservaProduto reservaProduto;
    private Stage stage;
    private Reserva reserva;
    private ReservaDao reservaDao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.produtoDao = new ProdutoDao();
        this.reservaProdutoDao = new ReservaProdutoDao();
        ObservableList<Produto> servicos = 
                FXCollections.observableArrayList(
                        produtoDao.findProdutoByTipo(ETipoProduto.SERVICO)
                );
        this.comboServico.setItems(servicos);
    }
    
    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setServico(ReservaProduto servico, Reserva reserva) {
        this.reservaProduto = servico;
        this.reserva = reserva;
        if (servico.getId() != null) {
            textValor.setText(servico.getValor().toString());
            textQuantidade.setText(String.valueOf(servico.getQuantidade()));
            comboServico.setValue(servico.getProduto());
        }
    }
    
    @FXML
    private void cancel() {
        this.stage.close();
    }
    @FXML
    private void save() {
        reservaProduto.setQuantidade(Integer.parseInt(textQuantidade.getText()));
        reservaProduto.setValor(Double.parseDouble(textValor.getText()));
        reservaProduto.setProduto(
                comboServico.getSelectionModel()
                        .getSelectedItem());
        reservaProduto.setReserva(reserva);
        this.reservaProdutoDao.save(reservaProduto);
        this.stage.close();
    }
    
    
}
