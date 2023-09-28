package cr.ac.una.evacomuna.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.evacomuna.util.FlowController;
import cr.ac.una.evacomuna.util.Formato;
import cr.ac.una.evacomuna.util.Mensaje;
import cr.ac.una.evacomuna.util.SoundUtil;
import cr.ac.una.evacomuna.model.TarUsuarioDto;
import cr.ac.una.evacomuna.service.TarUsuarioService;
import cr.ac.una.evacomuna.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.compress.utils.IOUtils;

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
    private MFXTextField txfUsuario;
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
    TarUsuarioDto tarUsuarioDto;
    List<Node> requeridos = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txfNombre.setTextFormatter(Formato.getInstance().letrasFormat(25));
        txfApellidos.setTextFormatter(Formato.getInstance().letrasFormat(25));
        txfCedula.setTextFormatter(Formato.getInstance().cedulaFormat(9));
        txfCorreo.setTextFormatter(Formato.getInstance().maxLengthFormat(80));
        txfUsuario.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
        txfContrasena.setTextFormatter(Formato.getInstance().letrasFormat(15));
        txfTelefono.setTextFormatter(Formato.getInstance().maxLengthFormat(8));
        txfCelular.setTextFormatter(Formato.getInstance().maxLengthFormat(8));
        this.tarUsuarioDto = new TarUsuarioDto();
        nuevoUsuario();
//        indicarRequeridos();
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
        try {
//            String invalidos = validarRequeridos();
//            if (!invalidos.isEmpty()) {
//                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar empleado", getStage(), invalidos);
//            } else {
            TarUsuarioService empleadoService = new TarUsuarioService();
            Respuesta respuesta = empleadoService.guardarUsuario(tarUsuarioDto.consultas());
            if (!respuesta.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Usuario", getStage(), respuesta.getMensaje());
            } else {
                unbindEmpleado();
                this.tarUsuarioDto = (TarUsuarioDto) respuesta.getResultado("TarUsuario");
                bindEmpleado(false);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Usuario", getStage(), "Usuario actualizado correctamente.");
            }
//            }
        } catch (Exception ex) {
            Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, "Error guardando el Usuario.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Usuario", getStage(), "Ocurrio un error guardando el Usuario.");
        }
    }
    
    private void indicarRequeridos() {
        requeridos.clear();
//        requeridos.addAll(Arrays.asList(txtNombre, txtCedula, txtPApellido, dtpFIngreso));
    }

    private void nuevoUsuario() {
        unbindEmpleado();
        this.tarUsuarioDto = new TarUsuarioDto();
        bindEmpleado(true);
    }

    private void bindEmpleado(Boolean nuevo) {
        txfNombre.textProperty().bindBidirectional(tarUsuarioDto.usuNombre);
        txfApellidos.textProperty().bindBidirectional(tarUsuarioDto.usuApellido);
        txfCedula.textProperty().bindBidirectional(tarUsuarioDto.usuCedula);
        txfCorreo.textProperty().bindBidirectional(tarUsuarioDto.usuCorreo);
        txfUsuario.textProperty().bindBidirectional(tarUsuarioDto.usuUsu);
        txfContrasena.textProperty().bindBidirectional(tarUsuarioDto.usuClave);
        txfTelefono.textProperty().bindBidirectional(tarUsuarioDto.usuTelefono);
        txfCelular.textProperty().bindBidirectional(tarUsuarioDto.usuCelular);
    }

    private void unbindEmpleado() {
        txfNombre.textProperty().unbindBidirectional(tarUsuarioDto.usuNombre);
        txfApellidos.textProperty().unbindBidirectional(tarUsuarioDto.usuApellido);
        txfCedula.textProperty().unbindBidirectional(tarUsuarioDto.usuCedula);
        txfCorreo.textProperty().unbindBidirectional(tarUsuarioDto.usuCorreo);
        txfUsuario.textProperty().unbindBidirectional(tarUsuarioDto.usuUsu);
        txfContrasena.textProperty().unbindBidirectional(tarUsuarioDto.usuClave);
        txfTelefono.textProperty().unbindBidirectional(tarUsuarioDto.usuTelefono);
        txfCelular.textProperty().unbindBidirectional(tarUsuarioDto.usuCelular);
    }

    public String validarRequeridos() {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridos) {
            if (node instanceof JFXTextField && (((JFXTextField) node).getText() == null || ((JFXTextField) node).getText().isBlank())) {
                if (validos) {
                    invalidos += ((JFXTextField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXTextField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXPasswordField && (((JFXPasswordField) node).getText() == null || ((JFXPasswordField) node).getText().isBlank())) {
                if (validos) {
                    invalidos += ((JFXPasswordField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXPasswordField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXDatePicker && ((JFXDatePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((JFXDatePicker) node).getAccessibleText();
                } else {
                    invalidos += "," + ((JFXDatePicker) node).getAccessibleText();
                }
                validos = false;
            } else if (node instanceof JFXComboBox && ((JFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (validos) {
                    invalidos += ((JFXComboBox) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXComboBox) node).getPromptText();
                }
                validos = false;
            }
        }
        if (validos) {
            return "";
        } else {
            return "Campos requeridos o con problemas de formato [" + invalidos + "].";
        }
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
            try {
                tarUsuarioDto.setUsuFoto(SaveImage(file));
            } catch (IOException ex) {
                Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    
    private byte[] SaveImage(File file) throws IOException {
        FileInputStream fiStream = new FileInputStream(file.getAbsolutePath());
        byte[] imageInBytes = IOUtils.toByteArray(fiStream);
        return imageInBytes;
    }
}
