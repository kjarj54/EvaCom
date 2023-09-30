package cr.ac.una.evacomuna.controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.evacomuna.util.Mensaje;
import cr.ac.una.evacomuna.util.SoundUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.compress.utils.IOUtils;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P07_MantenimientoGeneralesViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView imvFotoEmpresa;
    @FXML
    private JFXTextField txfNombre;
    @FXML
    private JFXTextArea txaInformacion;
    @FXML
    private JFXTextField txfCorreo;
    @FXML
    private JFXTextField txfPlantilla;
    @FXML
    private MFXButton btnAgregarPlantilla;
    @FXML
    private MFXButton btnGuardar;
    @FXML
    private MFXButton btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        onActionsBotones();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnAgregarPlantilla(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        //Inicializa el FileChooser y le da un titulo a la nueva ventana
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("JPG", "*.jpg", "PNG", "*.png", "GIF", "*.gif"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            txfPlantilla.setText(file.getAbsolutePath());
        }
//            try {
//                if (file != null) {
//                   tarUsuarioDto.setUsuFoto(SaveImage(file));
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, null, ex);
//            }
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        SoundUtil.mouseEnterSound();
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        SoundUtil.mouseEnterSound();
    }

    private void onActionsBotones() {
        // Botones
        btnAgregarPlantilla.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
        btnGuardar.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
        btnSalir.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
        imvFotoEmpresa.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
        imvFotoEmpresa.setOnMouseClicked(event -> {
            SoundUtil.mouseEnterSound();

            //Inicializa el FileChooser y le da un titulo a la nueva ventana
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar imagen");

            // Agregar filtros para facilitar la busqueda
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JPG", "*.jpg", "PNG", "*.png", "GIF", "*.gif"),
                    new FileChooser.ExtensionFilter("All Images", "*.*")
            );

            File file = fileChooser.showOpenDialog(null);
//            try {
//                if (file != null) {
//                   tarUsuarioDto.setUsuFoto(SaveImage(file));
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, null, ex);
//            }

            loadImages(imvFotoEmpresa, file);
        });
    }

    //Metodo para cargar imagenes al icono de empresa
    public void loadImages(ImageView imgview, File file_) {
        if (file_ != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file_);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);

                imgview.setImage(image);
//                System.out.println(file_.toString());
            } catch (IOException ex) {
                new Mensaje().show(Alert.AlertType.ERROR, "Imagen", "Error cargando imagen");
            }
        }
    }

    private byte[] SaveImage(File file) throws IOException {
        FileInputStream fiStream = new FileInputStream(file.getAbsolutePath());
        byte[] imageInBytes = IOUtils.toByteArray(fiStream);
        return imageInBytes;
    }

}
