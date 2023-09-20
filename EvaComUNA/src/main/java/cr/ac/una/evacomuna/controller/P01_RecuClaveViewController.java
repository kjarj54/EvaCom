/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.evacomuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.evacomuna.service.TarUsuarioService;
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
public class P01_RecuClaveViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField txtCorreo;
    @FXML
    private JFXButton btnEnviar;

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
    private void onActionBtnEnviar(ActionEvent event) {
        try {
            if (txtCorreo.getText() == null || txtCorreo.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Recuperacion de clave", (Stage) btnEnviar.getScene().getWindow(), "Es necesario digitar un correo para enviar la restauracion");
            } else {
                TarUsuarioService tarUsuarioService = new TarUsuarioService();
                Respuesta respuesta = tarUsuarioService.recuperarClave(txtCorreo.getText());
                if (respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Recuperacion de clave", (Stage) btnEnviar.getScene().getWindow(), "El correo fue enviado correctamente.");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Recuperacion de clave", (Stage) btnEnviar.getScene().getWindow(), "Ocurrio un error enviando el correo de recuperacion.");
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error enviando el correo.", ex);
        }
    }
    
}
