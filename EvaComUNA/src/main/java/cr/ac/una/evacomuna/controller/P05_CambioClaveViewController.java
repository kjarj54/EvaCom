/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.evacomuna.controller;

import cr.ac.una.evacomuna.model.TarUsuarioDto;
import cr.ac.una.evacomuna.service.TarUsuarioService;
import cr.ac.una.evacomuna.util.AppContext;
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
    
    TarUsuarioDto tarUsuarioDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadSounds();
        this.tarUsuarioDto = new TarUsuarioDto();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnAceptar(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        try {
            TarUsuarioService tarUsuarioService = new TarUsuarioService();
            tarUsuarioDto =  (TarUsuarioDto) AppContext.getInstance().get("UsuarioClass");
            tarUsuarioDto.setUsuClave(txfCorreo.getText());
            Respuesta respuesta = tarUsuarioService.guardarUsuario(tarUsuarioDto.consultas());
            if (!respuesta.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Usuario", getStage(), respuesta.getMensaje());
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Recuperación de clave", getStage(), "Se cambió la contraseña correctamente.");
                getStage().close();
            }

        } catch (Exception ex) {
            Logger.getLogger(P05_CambioClaveViewController.class.getName()).log(Level.SEVERE, "Error ingresando.", ex);
        }

    }

    private void loadSounds() {
        // Botones
        btnAceptar.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
    }
    
    
    

}
