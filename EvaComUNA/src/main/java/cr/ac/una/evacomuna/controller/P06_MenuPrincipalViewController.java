package cr.ac.una.evacomuna.controller;

import cr.ac.una.evacomuna.util.FlowController;
import cr.ac.una.evacomuna.util.SoundUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P06_MenuPrincipalViewController extends Controller implements Initializable {

    @FXML
    private MFXButton btnEmpleados;
    @FXML
    private MFXButton btnGenerales;
    @FXML
    private MFXButton btnCompetencias;
    @FXML
    private MFXButton btnPuestos;
    @FXML
    private MFXButton btnSalir;
    @FXML
    private AnchorPane root;
    @FXML
    private MFXButton btnEvaluaciones;
    @FXML
    private MFXButton btnCerrarSesion;

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
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnEmpleados(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().goView("P03_RegistroView");
    }

    @FXML
    private void onActionBtnGenerales(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().goView("P07_MantenimientoGeneralesView");
    }

    @FXML
    private void onActionBtnCompetencias(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().goView("P08_MantenimientoCompetencias");
    }

    @FXML
    private void onActionBtnPuestos(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().goView("P09_MantenimientoPuestosView");
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().salir();
    }

    @FXML
    private void onActionBtnEvaluaciones(ActionEvent event) {
    }

    @FXML
    private void onActionBtnCerrarSesion(ActionEvent event) {
    }

}
