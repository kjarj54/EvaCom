package cr.ac.una.evacomuna.controller;

import com.jfoenix.controls.JFXTextField;
import cr.ac.una.evacomuna.model.TarUsuarioDto;
import cr.ac.una.evacomuna.service.TarUsuarioService;
import cr.ac.una.evacomuna.util.Mensaje;
import cr.ac.una.evacomuna.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P03_1_BuscadorRegistroViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField txfCedula;
    @FXML
    private JFXTextField txfNombre;
    @FXML
    private JFXTextField txfUsuario;
    @FXML
    private JFXTextField txfPuesto;
    @FXML
    private TableView<TarUsuarioDto> tbvResultados;
    @FXML
    private MFXButton btnFiltrar;
    @FXML
    private MFXButton onActionBtnAceptar;

    private EventHandler<KeyEvent> keyEnter;
    private ObservableList<TarUsuarioDto> usuarios = FXCollections.observableArrayList();
    Object resultado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbvResultados.getColumns().clear();
        tbvResultados.getItems().clear();

        TableColumn<TarUsuarioDto, String> tbcId = new TableColumn<>("Id");
        tbcId.setPrefWidth(25);
        tbcId.setCellValueFactory(cd -> cd.getValue().usuId);

        TableColumn<TarUsuarioDto, String> tbcCedula = new TableColumn<>("Cedula");
        tbcCedula.setPrefWidth(75);
        tbcCedula.setCellValueFactory(cd -> cd.getValue().usuCedula);

        TableColumn<TarUsuarioDto, String> tbcNombre = new TableColumn<>("Nombre");
        tbcNombre.setPrefWidth(100);
        tbcNombre.setCellValueFactory(cd -> cd.getValue().usuNombre);

        TableColumn<TarUsuarioDto, String> tbcApellido = new TableColumn<>("Primer Apellido");
        tbcApellido.setPrefWidth(150);
        tbcApellido.setCellValueFactory(cd -> cd.getValue().usuApellido);

        tbvResultados.getColumns().add(tbcId);
        tbvResultados.getColumns().add(tbcCedula);
        tbvResultados.getColumns().add(tbcNombre);
        tbvResultados.getColumns().add(tbcApellido);
        tbvResultados.refresh();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnFiltrar(ActionEvent event) {
        String cedula = "%" + txfCedula.getText() + "%";

        String nombre = "%" + txfNombre.getText() + "%";

        String pApellido = "%" + txfUsuario.getText() + "%";

        String sApellido = "%" + txfPuesto.getText() + "%";

        cargarUsuarios(cedula, nombre, pApellido, sApellido);
    }

    @FXML
    private void onActionBtnAceptar(ActionEvent event) {
        resultado = tbvResultados.getSelectionModel().getSelectedItem();
        getStage().close();
    }

    @FXML
    private void onMousePressenTbvResultados(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            onActionBtnAceptar(null);
        }
    }

    private void cargarUsuarios(String cedula, String nombre, String usuario, String puesto) {
        TarUsuarioService service = new TarUsuarioService();
        Respuesta respuesta = service.getUsuarios(cedula.toUpperCase(), nombre.toUpperCase(), usuario.toUpperCase(), puesto.toUpperCase());

        if (respuesta.getEstado()) {
            usuarios.clear();
            usuarios.addAll((List<TarUsuarioDto>) respuesta.getResultado("Usuarios"));
            tbvResultados.setItems(usuarios);
            tbvResultados.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Usuarios", getStage(), respuesta.getMensaje());
        }
    }

}
