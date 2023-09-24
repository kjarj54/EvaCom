package cr.ac.una.evacomuna.controller;

import cr.ac.una.evacomuna.util.FlowController;
import cr.ac.una.evacomuna.util.Mensaje;
import cr.ac.una.evacomuna.util.SoundUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P03_RegistroViewController extends Controller implements Initializable {

    @FXML
    private MFXTextField txfNombre;
    @FXML
    private MFXTextField txfApellidos;
    @FXML
    private MFXTextField txfCedula;
    @FXML
    private MFXTextField txfCorreo;
    @FXML
    private MFXTextField txfContrasena;
    @FXML
    private MFXTextField txfTelefono;
    @FXML
    private MFXTextField txfCelular;
    @FXML
    private MFXButton btnRegistrar;
    @FXML
    private ImageView imvFotoPerfil;

    // Para cargar la imagen
    File file;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadSounds();
       Stage stage1 = FlowController.getInstance().getMainStage();
        stage1.setOnShown(event -> {
            // Luego de que la escena se muestre, solicitar el enfoque a otro nodo
            btnRegistrar.requestFocus();
        });
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnRegistrar(ActionEvent event) {
        SoundUtil.mouseEnterSound();
    }

    private void loadSounds() {
        // Botones
        btnRegistrar.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
        imvFotoPerfil.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
        imvFotoPerfil.setOnMouseClicked(event -> {
            SoundUtil.pressButtonSound();

            //Inicializa el FileChooser y le da un titulo a la nueva ventana
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar imagen");

            // Agregar filtros para facilitar la busqueda
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JPG", "*.jpg", "PNG", "*.png", "GIF", "*.gif"),
                    new FileChooser.ExtensionFilter("All Images", "*.*")
            );

            file = fileChooser.showOpenDialog(null);
            loadImages(imvFotoPerfil, file);
        });
    }

    //Metodo para cargar imagenes al icono de empresa
    public void loadImages(ImageView imgview, File file_) {
        if (file_ != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file_);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                imgview.setImage(image);
                System.out.println(file_.toString());
            } catch (IOException ex) {
                new Mensaje().show(Alert.AlertType.ERROR, "Imagen", "Error cargando imagen");
            }
        }
    }
}
