package br.edu.utfpr.pb.carlos.soster.oo24s.controller;

import br.edu.utfpr.pb.carlos.soster.oo24s.db.DatabaseConnection;
import br.edu.utfpr.pb.carlos.soster.oo24s.model.Usuario;
import br.edu.utfpr.pb.carlos.soster.oo24s.report.GenerateReport;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.view.JasperViewer;

public class FXMLPrincipalController implements Initializable {

    @FXML
    private VBox boxPrincipal;

    private Usuario usuarioAutenticado;

    public void setUsuarioAutenticado(Usuario usuario) {
        this.usuarioAutenticado = usuario;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setDataPane(Node node) {
        boxPrincipal.getChildren().setAll(node);
    }

    public VBox openVBox(String url) throws IOException {
        VBox v = (VBox) FXMLLoader.load(
                this.getClass().getResource(url));
        FadeTransition ft = new FadeTransition(
                Duration.millis(1000));
        ft.setNode(v);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        return v;
    }

    @FXML
    public void loadCategoria(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLCategoriaLista.fxml"
        ));
    }

    @FXML
    public void loadProduto(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLProdutoLista.fxml"
        ));
    }

    @FXML
    public void loadUsuario(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLUsuarioLista.fxml"
        ));
    }
    
    @FXML
    public void loadQuarto(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLQuartoLista.fxml"
        ));
    }
    
    @FXML
    public void loadCliente(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLClienteLista.fxml"
        ));
    }
    
    @FXML
    public void loadReserva(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLReservaLista.fxml"
        ));
        FXMLLoader loader = new FXMLLoader();
                loader.setLocation(
                    this.getClass()
                            .getResource("/fxml/FXMLReservaLista.fxml"));
        VBox root = (VBox) loader.load();
        FXMLReservaListaController controller
            = loader.getController();
        controller.setUsuario(usuarioAutenticado);
    }

    @FXML
    private void showReportProduto(ActionEvent event) {
        GenerateReport generateReport = new GenerateReport();
        InputStream file = this.getClass().getResourceAsStream("/report/rel-checkout.jasper");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("TITULO", "Relatório de chek-out");
        Image imagem = new ImageIcon(
                this.getClass().getResource("/imagens/logoUTFPR.jpg")).getImage();
        parameters.put("LOGO", imagem);

        DatabaseConnection conn = DatabaseConnection.getInstance();
        try {
            JasperViewer viewer = generateReport.getReport(
                    conn.getConnection(), parameters, file);
            viewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha ao exibir relatório!");
            alert.setContentText("Falha ao exibir relatório!");
            alert.showAndWait();
        }
    }

    @FXML
    private void showPieChart(ActionEvent event) throws IOException {
        setDataPane(openVBox("/fxml/FXMLPieChart.fxml"));
    }

    @FXML
    private void showBarChart(ActionEvent event) throws IOException {
        setDataPane(openVBox("/fxml/FXMLBarChart.fxml"));
    }
}
