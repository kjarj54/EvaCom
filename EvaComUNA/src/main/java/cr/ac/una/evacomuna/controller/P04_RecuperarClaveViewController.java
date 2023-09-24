/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.evacomuna.controller;

import cr.ac.una.evacomuna.service.TarUsuarioService;
import cr.ac.una.evacomuna.util.FlowController;
import cr.ac.una.evacomuna.util.Mensaje;
import cr.ac.una.evacomuna.util.Respuesta;
import cr.ac.una.evacomuna.util.SoundUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
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
 * @author Luvara
 */
public class P04_RecuperarClaveViewController extends Controller implements Initializable {

    @FXML
    private MFXTextField txfCorreo;
    @FXML
    private MFXButton btnAceptar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadSounds();
    }

    @Override
    public void initialize() {
        loadSounds();
    }

    @FXML
    private void onActionBtnAceptar(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        try {
            if (txfCorreo.getText() == null || txfCorreo.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Recuperación de clave", getStage(), "Es necesario digitar un correo para ingresar al sistema.");
            } else {
                TarUsuarioService tarUsuarioService = new TarUsuarioService();
                Respuesta respuesta = tarUsuarioService.recuperarClave(txfCorreo.getText());
                if (respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Recuperación de clave", getStage(), "El correo fue enviado correctamente.");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Recuperación de clave", getStage(), "Ocurrio un error enviando el correo de recuperacion.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P04_RecuperarClaveViewController.class.getName()).log(Level.SEVERE, "Error enviando el correo.", ex);
        }
    }

    private void loadSounds() {
        // Botones
        btnAceptar.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
        Stage stage1 = FlowController.getInstance().getMainStage();
        stage1.setOnShown(event -> {
            // Luego de que la escena se muestre, solicitar el enfoque a otro nodo
            btnAceptar.requestFocus();
        });
    }
}
