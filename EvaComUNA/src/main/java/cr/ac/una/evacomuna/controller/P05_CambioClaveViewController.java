/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.evacomuna.controller;

import cr.ac.una.evacomuna.util.Mensaje;
import cr.ac.una.evacomuna.util.SoundUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P05_CambioClaveViewController extends Controller implements Initializable {

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
    }

    @FXML
    private void onActionBtnAceptar(ActionEvent event) {
        SoundUtil.mouseEnterSound();
         new Mensaje().showModal(Alert.AlertType.INFORMATION, "Recuperación de clave", getStage(), "Se cambió la contraseña correctamente.");
    }
    
    private void loadSounds() {
        // Botones
        btnAceptar.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
    }

}
