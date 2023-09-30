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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P08_MantenimientoCompetenciasController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txfCompetencia;
    @FXML
    private JFXCheckBox chkActiva;
    @FXML
    private MFXButton btnEliminarCompetencia;
    @FXML
    private MFXButton btnLimpiarCompetencia;
    @FXML
    private MFXButton btnAgregarCompetencia;
    @FXML
    private TableView<?> tbvCompetencias;
    @FXML
    private JFXTextField txfBuscarNombre;
    @FXML
    private JFXCheckBox chkBuscarActivas;
    @FXML
    private MFXButton btnFiltrar;
    @FXML
    private JFXTextField txfCaracteristica;
    @FXML
    private MFXButton btnAgregarCaracteristica;
    @FXML
    private TableView<?> tbvCaracteristicas;
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
    private void onActionBtnEliminarCompetencia(ActionEvent event) {
    }

    @FXML
    private void onActionBtnLimpiarCompetencia(ActionEvent event) {
    }

    @FXML
    private void onActionBtnAgregarCompetencia(ActionEvent event) {
    }

    @FXML
    private void onActionBtnAgregarCaracteristica(ActionEvent event) {
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
    }
    
}
