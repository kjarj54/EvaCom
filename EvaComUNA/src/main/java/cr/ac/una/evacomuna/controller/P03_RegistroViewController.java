package cr.ac.una.evacomuna.controller;

import com.jfoenix.controls.JFXCheckBox;
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
import cr.ac.una.evacomuna.util.AppContext;
import cr.ac.una.evacomuna.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXButton;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class P03_RegistroViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField txfNombre;
    @FXML
    private JFXTextField txfApellidos;
    @FXML
    private JFXTextField txfCedula;
    @FXML
    private JFXTextField txfCorreo;
    @FXML
    private JFXTextField txfUsuario;
    @FXML
    private JFXTextField txfContrasena;
    @FXML
    private JFXTextField txfTelefono;
    @FXML
    private JFXTextField txfCelular;
    @FXML
    private MFXButton btnRegistrar;
    @FXML
    private ImageView imvFotoPerfil;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXCheckBox chkAdministrador;
    @FXML
    private MFXButton btnSalir;
    @FXML
    private MFXButton btnBuscar;
    @FXML
    private MFXButton btnEliminar;
    @FXML
    private MFXButton btnLimpiarCampos;
    @FXML
    private JFXComboBox<String> cboxPuesto;
    

    // Para cargar la imagen
    File file;
    TarUsuarioDto tarUsuarioDto;
    List<Node> requeridos = new ArrayList<>();
    
    

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
        
        // Crear una lista de elementos
        ObservableList<String> elementos = FXCollections.observableArrayList(
                "Opción 1",
                "Opción 2",
                "Opción 3"
        );

        // Agregar la lista de elementos al ComboBox
        cboxPuesto.setItems(elementos);

        txfNombre.setTextFormatter(Formato.getInstance().letrasFormat(25));
        txfApellidos.setTextFormatter(Formato.getInstance().letrasFormat(35));
        txfCedula.setTextFormatter(Formato.getInstance().cedulaFormat(30));
        txfCorreo.setTextFormatter(Formato.getInstance().maxLengthFormat(80));
        txfUsuario.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
        txfContrasena.setTextFormatter(Formato.getInstance().letrasFormat(15));
        txfTelefono.setTextFormatter(Formato.getInstance().integerFormat());
        txfCelular.setTextFormatter(Formato.getInstance().integerFormat());
        this.tarUsuarioDto = new TarUsuarioDto();
        nuevoUsuario();
//        indicarRequeridos();
        cargarInterfaz();
        loadSounds();
    }

    @Override
    public void initialize() {
        cargarInterfaz();
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
    
    @FXML
    private void onActionBtnBuscar(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().goViewInWindowModal("P03_1_BuscadorRegistroView", stage, false);
    }
    
    @FXML
    private void onActionBtnLimpiarCampos(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        nuevoUsuario();
    }
    
     @FXML
    private void onActionBtnEliminarUsuario(ActionEvent event) {
        SoundUtil.mouseEnterSound();
    }
    
    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    private void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txfNombre, txfApellidos, txfCedula, txfCorreo, txfUsuario, txfContrasena, txfCelular));
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
        chkAdministrador.selectedProperty().bindBidirectional(tarUsuarioDto.usuAdmin);
        cboxPuesto.valueProperty().bindBidirectional(tarUsuarioDto.puestoDto.pueNombre);

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
        chkAdministrador.selectedProperty().unbindBidirectional(tarUsuarioDto.usuAdmin);
        cboxPuesto.valueProperty().unbindBidirectional(tarUsuarioDto.puestoDto.pueNombre);
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
    
    public void bindBuscar() {
        P03_1_BuscadorRegistroViewController buscadorRegistroController = (P03_1_BuscadorRegistroViewController) FlowController.getInstance().getController("P03_1_BuscadorRegistroView");
        unbindEmpleado();
        tarUsuarioDto = (TarUsuarioDto) buscadorRegistroController.getSeleccionado();
        bindEmpleado(false);

    }
    
    public void cargarInterfaz() {
        String padre = (String) AppContext.getInstance().get("Padre");
        TarUsuarioDto usuario = (TarUsuarioDto) AppContext.getInstance().get("UsuarioClass");
        
        
        if ("LogInView".equals(padre)) {
            root.setPrefWidth(600);
            root.getStyleClass().add("fondo-registro");
            chkAdministrador.setVisible(false);
            btnBuscar.setVisible(false);
            btnEliminar.setVisible(false);
            btnLimpiarCampos.setVisible(false);
            btnSalir.setVisible(false);
            cboxPuesto.setVisible(false);
        } else if (usuario.getUsuAdmin().equals("S")){
            root.setPrefWidth(1280);
            root.getStyleClass().add("fondo-registro-completa");
            btnRegistrar.setText("Registrar/Actualizar");
            chkAdministrador.setVisible(true);
            btnBuscar.setVisible(true);
            btnEliminar.setVisible(true);
            btnLimpiarCampos.setVisible(true);
            btnSalir.setVisible(true);
            cboxPuesto.setVisible(true);
        } else{
            tarUsuarioDto = usuario;
            bindEmpleado(false);
            root.setPrefWidth(1280);
            root.getStyleClass().add("fondo-registro-completa");
            btnRegistrar.setText("Registrar/Actualizar");
            chkAdministrador.setDisable(true);
            btnBuscar.setDisable(true);
            btnEliminar.setDisable(true);
            btnLimpiarCampos.setVisible(true);
            btnSalir.setVisible(true);
            cboxPuesto.setDisable(true);
        }
    }

    private void loadSounds() {
        // Botones
        btnBuscar.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
        btnEliminar.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
        btnLimpiarCampos.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
        btnRegistrar.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
        btnSalir.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
        imvFotoPerfil.setOnMouseEntered(event -> {
            SoundUtil.mouseHoverSound();
        });
        imvFotoPerfil.setOnMouseClicked(event -> {
            SoundUtil.mouseEnterSound();

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
                if (file != null) {
                    tarUsuarioDto.setUsuFoto(SaveImage(file));
                }
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
