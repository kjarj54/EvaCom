/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
public class P10_MenuEvaluacionesViewController extends Controller implements Initializable {
    @FXML
    private MFXButton btnSalir;
    @FXML
    private AnchorPane root;
    @FXML
    private MFXButton btnCrearEvaluacion;
    @FXML
    private MFXButton btnRegistroEvaluaciones;
    @FXML
    private MFXButton btnRevision;
    @FXML
    private MFXButton btnResumen;

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
    private void onActionBtnSalir(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    @FXML
    private void onActionBtnCrearEvaluacion(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().goView("P11_RegistroEvaluacionesView");
    }

    @FXML
    private void onActionBtnRegistroEvaluaciones(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().goView("P12_AplicarEvaluacionView");
    }

    @FXML
    private void onActionBtnRevision(ActionEvent event) {
    }

    @FXML
    private void onActionBtnResumen(ActionEvent event) {
    }

}
