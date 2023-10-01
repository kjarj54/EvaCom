package cr.ac.una.evacomuna.controller;

import cr.ac.una.evacomuna.model.TarUsuarioDto;
import cr.ac.una.evacomuna.service.TarUsuarioService;
import cr.ac.una.evacomuna.util.AppContext;
import cr.ac.una.evacomuna.util.FlowController;
import cr.ac.una.evacomuna.util.Mensaje;
import cr.ac.una.evacomuna.util.Respuesta;
import cr.ac.una.evacomuna.util.SoundUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.File;
import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P02_LogInViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private MediaView mdvFondoLogIn;
    @FXML
    private MFXTextField txfCorreo;
    @FXML
    private MFXPasswordField txfContrasena;
    @FXML
    private MFXButton btnIngresar;
    @FXML
    private MFXButton btnAcercaDe;
    @FXML
    private MFXButton btnSalir;
    @FXML
    private MFXButton btnNuevaCuenta;
    @FXML
    private MFXButton btnRecuperarContra;
    @FXML
    private ImageView imvLogo;

    Timeline timeline;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Para el responsive
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);

        loadVideo();
        loadSounds();
        efectoLogo();
    }

    @Override
    public void initialize() {
        loadVideo();
        efectoLogo();
    }

    @FXML
    private void onActionBtnIngresar(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        try {
            if (txfCorreo.getText() == null || txfContrasena.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validación de usuario", getStage(), "Es necesario digitar un correo o usuario para ingresar al sistema.");
            } else if (txfCorreo.getText() == null || txfContrasena.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validación de usuario", getStage(), "Es necesario digitar la clave para ingresar al sistema.");
            } else {
                TarUsuarioService tarUsuarioService = new TarUsuarioService();
                Respuesta respuesta = tarUsuarioService.getUsuario(txfCorreo.getText(), txfContrasena.getText());
                if (respuesta.getEstado()) {
                    TarUsuarioDto tarUsuarioDto = (TarUsuarioDto) respuesta.getResultado("TarUsuario");
                    AppContext.getInstance().set("UsuarioId", tarUsuarioDto.getUsuId());
                    AppContext.getInstance().set("UsuarioClass", tarUsuarioDto);                   
                    if (tarUsuarioDto.getUsuClave().equals(tarUsuarioDto.getUsuTempclave())) {
                        new Mensaje().showModal(Alert.AlertType.WARNING, "Validación de usuario", getStage(), "Es necesario cambiar la clave para ingresar al sistema.");
                        FlowController.getInstance().goViewInWindowModal("P05_CambioClaveView", stage, false);
                    } else {
                        if ("S".equals(tarUsuarioDto.getUsuAdmin()) && "A".equals(tarUsuarioDto.getUsuActivo())) {//compruba que el usuario este activo
                            FlowController.getInstance().goView("P06_MenuPrincipalView");
                            //getStage().close();
                        } else if ("N".equals(tarUsuarioDto.getUsuAdmin()) && "A".equals(tarUsuarioDto.getUsuActivo())) {//compruba que el usuario este activo
                            //FlowController.getInstance() TODO
                            //getStage().close();

                        } else {
                            new Mensaje().showModal(Alert.AlertType.ERROR, "Validación de usuario", getStage(), "Es necesario que su cuenta este activada.");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P02_LogInViewController.class.getName()).log(Level.SEVERE, "Error ingresando.", ex);
        }
    }

    @FXML
    private void onActionBtnNuevaCuenta(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        AppContext.getInstance().set("Padre", "LogInView");
        FlowController.getInstance().goViewInWindowModal("P03_RegistroView", stage, false);
    }

    @FXML
    private void onActionBtnRecuperarContra(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().goViewInWindowModal("P04_RecuperarClaveView", stage, false);
    }

    @FXML
    private void onActionBtnAcercaDe(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        AppContext.getInstance().set("Padre", "Other");
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().salir();
    }

    private void loadVideo() {
        String videoFile = new File("src/main/resources/cr/ac/una/evacomuna/resources/media/video/logInVideo.mp4").getAbsolutePath();

        Media media = new Media(new File(videoFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mdvFondoLogIn.setMediaPlayer(mediaPlayer);

        // Configura el ciclo infinito
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // Vincula el tamaño del MediaView al tamaño de la escena
        mdvFondoLogIn.fitWidthProperty().bind(root.widthProperty());
        mdvFondoLogIn.fitHeightProperty().bind(root.heightProperty());
        mdvFondoLogIn.setPreserveRatio(false);
        mediaPlayer.setAutoPlay(true);
    }

    private void efectoLogo() {
        // Crear el efecto DropShadow y asignarlo a imvLogo
        DropShadow dropShadow = new DropShadow();
        imvLogo.setEffect(dropShadow);

        // Configurar el efecto DropShadow
        dropShadow.setColor(Color.BLUE);
        dropShadow.setRadius(15);
        dropShadow.setSpread(0.7);
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(0.0);

        // Crear una animación para cambiar el color
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(2), new KeyValue(dropShadow.colorProperty(), Color.RED)),
                new KeyFrame(Duration.seconds(4), new KeyValue(dropShadow.colorProperty(), Color.GREEN)),
                new KeyFrame(Duration.seconds(6), new KeyValue(dropShadow.colorProperty(), Color.BLUE))
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // Repetir la animación indefinidamente

        // Iniciar la animación
        timeline.play();
    }

    private void loadSounds() {
        // Botones
        btnIngresar.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
        btnNuevaCuenta.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
        btnAcercaDe.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
        btnRecuperarContra.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
        btnSalir.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
        // Cuadros texto

        txfCorreo.setOnMousePressed(event -> {
            SoundUtil.mouseEnterSound();
        });
        txfContrasena.setOnMouseClicked(event -> {
            SoundUtil.mouseEnterSound();
        });
    }

}
