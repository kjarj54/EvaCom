package cr.ac.una.evacomuna.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P09_MantenimientoPuestosViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField txfPuesto;
    @FXML
    private JFXCheckBox chkActiva;
    @FXML
    private MFXButton btnEliminarPuesto;
    @FXML
    private MFXButton btnLimpiarPuesto;
    @FXML
    private MFXButton btnAgregarPuesto;
    @FXML
    private TableView<?> tbvCompetencias;
    @FXML
    private JFXTextField txfBuscarNombre;
    @FXML
    private JFXCheckBox chkBuscarActivas;
    @FXML
    private MFXButton btnFiltrar;
    @FXML
    private JFXTextField txfBuscarCompetencia;
    @FXML
    private MFXButton btnAgregarCompetencia;
    @FXML
    private TableView<?> tbvPuestos;

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
    private void onActionBtnEliminarPuesto(ActionEvent event) {
    }

    @FXML
    private void onActionBtnLimpiarPuesto(ActionEvent event) {
    }

    @FXML
    private void onActionBtnAgregarPuesto(ActionEvent event) {
    }

    @FXML
    private void onActionBtnAgregarCompetencia(ActionEvent event) {
    }
    
}
