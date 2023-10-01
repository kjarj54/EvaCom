package cr.ac.una.evacomuna.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.evacomuna.model.TarUsuarioDto;
import cr.ac.una.evacomuna.service.TarUsuarioService;
import cr.ac.una.evacomuna.util.Mensaje;
import cr.ac.una.evacomuna.util.Respuesta;
import cr.ac.una.evacomunaws.controller.TarProcesoevaluacionDto;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P11_RegistroEvaluacionesViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txfTituloEvaluacion;
    @FXML
    private JFXDatePicker dtpFechaInicio;
    @FXML
    private JFXDatePicker dtpFechaAplicar;
    @FXML
    private JFXDatePicker dtpFechaFinaliza;
    @FXML
    private JFXComboBox<String> cboxEstado;
    @FXML
    private MFXButton btnEliminarEvaluacion;
    @FXML
    private MFXButton btnLimpiarEvaluacion;
    @FXML
    private MFXButton btnAgregarEvaluacion;
    @FXML
    private JFXTextField txfBuscarPuesto;
    @FXML
    private MFXButton btnAgregarPuesto;
    @FXML
    private JFXTextField txfBuscarTrabajador;
    @FXML
    private MFXButton btnAgregarTrabajador;
    @FXML
    private TableView<TarUsuarioDto> tbvTrabajadores;

    
    private ObservableList<TarUsuarioDto> usuarios = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        estadosEvaluacion();
        
        tbvTrabajadores.getColumns().clear();
        tbvTrabajadores.getItems().clear();

        TableColumn<TarUsuarioDto, String> tbcId = new TableColumn<>("Id");
        tbcId.setPrefWidth(25);
        tbcId.setCellValueFactory(cd -> cd.getValue().usuId);

        TableColumn<TarUsuarioDto, String> tbcCedula = new TableColumn<>("Cedula");
        tbcCedula.setPrefWidth(75);
        tbcCedula.setCellValueFactory(cd -> cd.getValue().usuCedula);

        TableColumn<TarUsuarioDto, String> tbcNombre = new TableColumn<>("Nombre");
        tbcNombre.setPrefWidth(100);
        tbcNombre.setCellValueFactory(cd -> cd.getValue().usuNombre);

        TableColumn<TarUsuarioDto, String> tbcApellido = new TableColumn<>("Apellidos");
        tbcApellido.setPrefWidth(150);
        tbcApellido.setCellValueFactory(cd -> cd.getValue().usuApellido);
        
        TableColumn<TarUsuarioDto, Boolean> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellValueFactory((TableColumn.CellDataFeatures<TarUsuarioDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
        tbcEliminar.setCellFactory((TableColumn<TarUsuarioDto, Boolean> p) -> new ButtonCell());
        
        TableColumn<TarUsuarioDto, String> comboBoxColumn = new TableColumn<>("relacion");
        comboBoxColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        // Crear el ComboBox y establecer los valores
        comboBoxColumn.setCellFactory(param -> {
            ComboBox<String> comboBox = new ComboBox<>();
            comboBox.getItems().addAll("Cliente", "Compañero", "Jefatura");
            return new TableCell<TarUsuarioDto, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        comboBox.getSelectionModel().select(item);
                        setGraphic(comboBox);
                    }
                }
            };
        });


        tbvTrabajadores.getColumns().add(tbcId);
        tbvTrabajadores.getColumns().add(tbcCedula);
        tbvTrabajadores.getColumns().add(tbcNombre);
        tbvTrabajadores.getColumns().add(tbcApellido);
        tbvTrabajadores.getColumns().add(comboBoxColumn);
        tbvTrabajadores.getColumns().add(tbcEliminar);
        tbvTrabajadores.refresh();
    }    

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnEliminarEvaluacion(ActionEvent event) {
    }

    @FXML
    private void onActionBtnLimpiarEvaluacion(ActionEvent event) {
    }

    @FXML
    private void onActionBtnAgregarEvaluacion(ActionEvent event) {
    }

    @FXML
    private void onkeyPressedCompetencia(KeyEvent event) {
    }

    @FXML
    private void onActionBtnAgregarPuesto(ActionEvent event) {
    }

    @FXML
    private void onActionBtnAgregarTrabajador(ActionEvent event) {
        String cedula = txfBuscarTrabajador.getText();


        cargarUsuarios(cedula, "", "", "");
    }
    
    private void estadosEvaluacion(){
        // Crear una lista de elementos
        ObservableList<String> elementos = FXCollections.observableArrayList(
                "En construcción",
                "En aplicación",
                "En revisión",
                "Finalizada"
        );

        // Agregar la lista de elementos al ComboBox
        cboxEstado.setItems(elementos);
    }
    
     private void cargarUsuarios(String cedula, String nombre, String usuario, String puesto) {
        TarUsuarioService service = new TarUsuarioService();
        Respuesta respuesta = service.getUsuarios(cedula.toUpperCase(), nombre.toUpperCase(), usuario.toUpperCase(), puesto.toUpperCase());

        if (respuesta.getEstado()) {
            tbvTrabajadores.getItems().clear();
            usuarios.clear();
            usuarios.addAll((List<TarUsuarioDto>) respuesta.getResultado("TarUsuario"));
            tbvTrabajadores.setItems(usuarios);
            tbvTrabajadores.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Usuarios", getStage(), respuesta.getMensaje());
        }
    }
    
    private class ButtonCell extends TableCell<TarUsuarioDto, Boolean> {

        final MFXButton cellButton = new MFXButton();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.setText("Eliminar");
            cellButton.getStyleClass().add("mfx-button-menuSalir");

            cellButton.setOnAction((ActionEvent t) -> {
                TarUsuarioDto emp = (TarUsuarioDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                tbvTrabajadores.getItems().remove(emp);
                tbvTrabajadores.refresh();
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }
}
