package cr.ac.una.evacomuna.controller;

import cr.ac.una.evacomuna.util.SoundUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P12_AplicarEvaluacionViewController extends Controller implements Initializable {

    @FXML
    private MFXButton btnSalir;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblPuesto;
    @FXML
    private Label lblPeriodo;
    @FXML
    private Label lblFecha;
    @FXML
    private MFXButton btnFinalizarEvaluacion;
    @FXML
    private GridPane grdPrincipal;
    @FXML
    private Label lblCheck;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);

        onActionsOther();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
    }

    @FXML
    private void onActionBtnFinalizarEvaluacion(ActionEvent event) {
    }

    private void onActionsOther() {
        lblCheck.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
            lblCheck.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 204, 51, 1), 10, 0.5, 0.0, 0.0);");
        });
        lblCheck.setOnMouseExited(event -> {
            lblCheck.setStyle("");
        });
    }

}
