package br.edu.utfpr.pb.carlos.soster.oo24s.controller;

import br.edu.utfpr.pb.carlos.soster.oo24s.dao.CategoriaDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.dao.ProdutoDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.dao.QuartoDao;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.ETipoQuarto;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Quarto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLQuartoCadastroController implements Initializable {

    @FXML
    private TextField textId;
    @FXML 
    private TextField textNumero;
    @FXML
    private RadioButton radioEconomico;
    @FXML
    private RadioButton radioSuperior;
    @FXML
    private RadioButton radioLuxo;
    @FXML
    private TextField textQtdeCamas;
    @FXML
    private TextField textQtdePessoas;
    @FXML
    private TextArea textValorDiaria;
    
    private QuartoDao quartoDao;
    private Stage stage;
    private Quarto quarto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.quartoDao = new QuartoDao();
        this.radioEconomico.setSelected(true);
    }
    
    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
        if (quarto.getId() != null) {
            textId.setText(quarto.getId().toString());
            textNumero.setText(quarto.getNumero().toString());
            if(null == quarto.getTipoQuarto()){
                radioEconomico.setSelected(true);
            }else switch (quarto.getTipoQuarto()) {
                case ECONOMICO:
                    radioEconomico.setSelected(true);
                    break;
                case SUPERIOR:
                    radioSuperior.setSelected(true);
                    break;
                default:
                    radioLuxo.setSelected(true);
                    break;
            }
            textQtdeCamas.setText(quarto.getQtdeCamas().toString());
            textQtdePessoas.setText(quarto.getQtdePessoas().toString());
            textValorDiaria.setText(quarto.getValorDiaria().toString());
        }
    }
    
    @FXML
    private void cancel() {
        this.stage.close();
    }
    @FXML
    private void save() {
        quarto.setNumero(Long.parseLong(textNumero.getText()));
        quarto.setTipoQuarto(radioEconomico.isSelected()? ETipoQuarto.ECONOMICO 
                : radioSuperior.isSelected() ? ETipoQuarto.SUPERIOR : ETipoQuarto.LUXO);
        quarto.setQtdeCamas(Integer.parseInt(textQtdeCamas.getText()));
        quarto.setQtdePessoas(Integer.parseInt(textQtdePessoas.getText()));
        quarto.setValorDiaria(Double.parseDouble(textValorDiaria.getText()));
        
        this.quartoDao.save(quarto);
        this.stage.close();
    }
    
}
