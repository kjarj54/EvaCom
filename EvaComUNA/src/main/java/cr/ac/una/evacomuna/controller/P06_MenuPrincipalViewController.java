package cr.ac.una.evacomuna.controller;

import cr.ac.una.evacomuna.util.FlowController;
import cr.ac.una.evacomuna.util.SoundUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

}
