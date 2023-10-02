package cr.ac.una.evacomuna.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.evacomuna.model.TarParametrosDto;
import cr.ac.una.evacomuna.model.TarUsuarioDto;
import cr.ac.una.evacomuna.service.TarParametrosService;
import cr.ac.una.evacomuna.service.TarUsuarioService;
import cr.ac.una.evacomuna.util.FlowController;
import cr.ac.una.evacomuna.util.Formato;
import cr.ac.una.evacomuna.util.Mensaje;
import cr.ac.una.evacomuna.util.Respuesta;
import cr.ac.una.evacomuna.util.SoundUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
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
    private JFXTextField txfClave;
    @FXML
    private JFXTextField txfPlantilla;
    @FXML
    private MFXButton btnAgregarPlantilla;
    @FXML
    private MFXButton btnGuardar;
    @FXML
    private MFXButton btnSalir;

    File file;
    TarParametrosDto tarParametrosDto;
    List<Node> requeridos = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);

        txfNombre.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
        txaInformacion.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txfCorreo.setTextFormatter(Formato.getInstance().maxLengthFormat(80));
        txfClave.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
        this.tarParametrosDto = new TarParametrosDto();
        cargarParametros();
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
        try {
            if (file != null) {
                tarParametrosDto.setParHtml(fileToByte(file));
            }
        } catch (IOException ex) {
            Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        try {
//            String invalidos = validarRequeridos();
//            if (!invalidos.isEmpty()) {
//                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar empleado", getStage(), invalidos);
//            } else {
//            }
            TarParametrosService parametrosService = new TarParametrosService();
            Respuesta respuesta = parametrosService.guardarTarParametros(tarParametrosDto.consultas());
            if (!respuesta.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Parametros", getStage(), respuesta.getMensaje());
            } else {
                unbindParametro();
                this.tarParametrosDto = (TarParametrosDto) respuesta.getResultado("Parametros");
                bindParametro(false);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Parametros", getStage(), "Parametros actualizados correctamente.");
                initialize(null, null);
            }

        } catch (Exception ex) {
            Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, "Error guardando los Parametros.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Parametros", getStage(), "Ocurrio un error guardando los Parametros.");
        }
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    private void cargarParametros() {
        TarParametrosService service = new TarParametrosService();
        Respuesta respuesta = service.getParametrosList();

        List<TarParametrosDto> tarParametrosDtosList = new ArrayList<>();
        tarParametrosDtosList = (List<TarParametrosDto>) respuesta.getResultado("Parametros");
        if (!tarParametrosDtosList.isEmpty()) {
            if (respuesta.getEstado()) {
                unbindParametro();
                this.tarParametrosDto = tarParametrosDtosList.get(0);
                imvFotoEmpresa.setImage(byteToImage(this.tarParametrosDto.getParLogo()));
                bindParametro(false);
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Parametros", getStage(), respuesta.getMensaje());
            }
        } else {
            nuevoParametro();
        }
    }

    private void nuevoParametro() {
        unbindParametro();
        this.tarParametrosDto = new TarParametrosDto();
        bindParametro(true);
    }

    private void bindParametro(Boolean nuevo) {
        txfNombre.textProperty().bindBidirectional(tarParametrosDto.parNombre);
        txaInformacion.textProperty().bindBidirectional(tarParametrosDto.parDescripcion);
        txfCorreo.textProperty().bindBidirectional(tarParametrosDto.parEmail);
        txfClave.textProperty().bindBidirectional(tarParametrosDto.parClave);
    }

    private void unbindParametro() {
        txfNombre.textProperty().unbindBidirectional(tarParametrosDto.parNombre);
        txaInformacion.textProperty().unbindBidirectional(tarParametrosDto.parDescripcion);
        txfCorreo.textProperty().unbindBidirectional(tarParametrosDto.parEmail);
        txfClave.textProperty().unbindBidirectional(tarParametrosDto.parClave);
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
            try {
                if (file != null) {
                    this.tarParametrosDto.setParLogo(fileToByte(file));
                }
            } catch (IOException ex) {
                Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, null, ex);
            }

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
            } catch (IOException ex) {
                new Mensaje().show(Alert.AlertType.ERROR, "Imagen", "Error cargando imagen");
            }
        }
    }

    private byte[] fileToByte(File file) throws IOException {
        FileInputStream fiStream = new FileInputStream(file.getAbsolutePath());
        byte[] imageInBytes = IOUtils.toByteArray(fiStream);
        return imageInBytes;
    }

    private Image byteToImage(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        return new Image(bis);
    }
}
