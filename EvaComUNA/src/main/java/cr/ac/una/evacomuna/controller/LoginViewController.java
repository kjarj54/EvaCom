/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.evacomuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.evacomuna.model.TarUsuarioDto;
import cr.ac.una.evacomuna.service.TarUsuarioService;
import cr.ac.una.evacomuna.util.AppContext;
import cr.ac.una.evacomuna.util.FlowController;
import cr.ac.una.evacomuna.util.Mensaje;
import cr.ac.una.evacomuna.util.Respuesta;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class LoginViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXPasswordField txtClave;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnOlvidarClave;

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
    private void onActionBtnLogin(ActionEvent event) {
        try {
            if (txtUsuario.getText() == null || txtUsuario.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validación de usuario", (Stage) btnLogin.getScene().getWindow(), "Es necesario digitar un usuario para ingresar al sistema.");
            } else if (txtClave.getText() == null || txtClave.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validación de usuario", (Stage) btnLogin.getScene().getWindow(), "Es necesario digitar la clave para ingresar al sistema.");
            } else {
                TarUsuarioService tarUsuarioService = new TarUsuarioService();
                Respuesta respuesta = tarUsuarioService.getUsuario(txtUsuario.getText(), txtClave.getText());
                if (respuesta.getEstado()) {
                    TarUsuarioDto tarUsuarioDto = (TarUsuarioDto) respuesta.getResultado("TarUsuario");
                    AppContext.getInstance().set("UsuarioId", tarUsuarioDto.getUsuId());
                    if (tarUsuarioDto.getUsuClave().equals(tarUsuarioDto.getUsuTempclave())) {
                        new Mensaje().showModal(Alert.AlertType.WARNING, "Validación de usuario", (Stage) btnLogin.getScene().getWindow(), "Es necesario cambiar la clave para ingresar al sistema.");
                        FlowController.getInstance().goViewInWindowModal("P02_CambioClaveView", getStage(), true);

                    } else {
                        if ("S".equals(tarUsuarioDto.getUsuAdmin()) && "A".equals(tarUsuarioDto.getUsuActivo())) {//compruba que el usuario este activo
                            //FlowController.getInstance()//TODO
                            getStage().close();
                        } else if ("N".equals(tarUsuarioDto.getUsuAdmin()) && "A".equals(tarUsuarioDto.getUsuActivo())) {//compruba que el usuario este activo
                            //FlowController.getInstance() TODO
                            getStage().close();

                        } else {
                            new Mensaje().showModal(Alert.AlertType.ERROR, "Validación de usuario", (Stage) btnLogin.getScene().getWindow(), "Es necesario que su cuenta este activada.");
                        }
                    }

                }
            }

        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error ingresando.", ex);
        }

    }

    @FXML
    private void onActionBtnCancelar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnOlvidarClave(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("P01_RecuClaveView");
    }

}
