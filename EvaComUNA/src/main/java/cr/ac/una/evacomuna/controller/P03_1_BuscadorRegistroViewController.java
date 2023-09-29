package cr.ac.una.evacomuna.controller;

import com.jfoenix.controls.JFXTextField;
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
public class P03_1_BuscadorRegistroViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField txfCedula;
    @FXML
    private JFXTextField txfNombre;
    @FXML
    private JFXTextField txfUsuario;
    @FXML
    private JFXTextField txfPuesto;
    @FXML
    private MFXButton btnFiltrar;
    @FXML
    private MFXButton onActionBtnAceptar;

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
    private void onActionBtnFiltrar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnAceptar(ActionEvent event) {
    }

}
