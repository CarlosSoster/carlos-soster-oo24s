package br.edu.utfpr.pb.carlos.soster.oo24s.controller;

import br.edu.utfpr.pb.carlos.soster.oo24s.dao.CategoriaDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.dao.ProdutoDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Categoria;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.ETipoProduto;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Produto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class FXMLProdutoCadastroController implements Initializable {

    @FXML
    private TextField textId;
    @FXML 
    private TextField textNome;
    @FXML
    private TextField textValor;
    @FXML
    private ComboBox<Categoria> comboCategoria;
    @FXML
    private TextArea textDescricao;
    @FXML
    private RadioButton radioProduto;
    @FXML
    private RadioButton radioServico;
    
    private ProdutoDao produtoDao;
    private CategoriaDao categoriaDao;
    private Stage stage;
    private Produto produto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.produtoDao = new ProdutoDao();
        this.categoriaDao = new CategoriaDao();
        ObservableList<Categoria> categorias = 
                FXCollections.observableArrayList(
                        categoriaDao.getAll()
                );
        this.comboCategoria.setItems(categorias);
        this.radioProduto.setSelected(true);
    }    
    
    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
        if (produto.getId() != null) {
            textId.setText(produto.getId().toString());
            textNome.setText(produto.getNome());
            textValor.setText(produto.getValor().toString());
            comboCategoria.setValue(produto.getCategoria());
            textDescricao.setText(produto.getDescricao());
            if(produto.getTipoProduto() == ETipoProduto.SERVICO){
                radioServico.setSelected(true);
            }else{
                radioProduto.setSelected(true);
            }
        }
    }
    @FXML
    private void cancel() {
        this.stage.close();
    }
    @FXML
    private void save() {
        produto.setNome(textNome.getText());
        produto.setValor(Double.parseDouble(
                textValor.getText()));
        produto.setDescricao(textDescricao.getText());
        produto.setCategoria(
                comboCategoria.getSelectionModel()
                        .getSelectedItem());
        produto.setTipoProduto(radioProduto.isSelected()? ETipoProduto.PRODUTO : ETipoProduto.SERVICO);
        
        this.produtoDao.save(produto);
        this.stage.close();
    }
}
