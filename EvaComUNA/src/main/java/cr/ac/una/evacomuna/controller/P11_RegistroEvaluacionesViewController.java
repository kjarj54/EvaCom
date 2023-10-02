package cr.ac.una.evacomuna.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.evacomuna.model.TarUsuarioDto;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
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
    private MFXButton btnLimpiarEvaluacion;
    @FXML
    private MFXButton btnAgregarEvaluacion;
    private JFXTextField txfBuscarTrabajador;
    private TableView<TarUsuarioDto> tbvTrabajadores;

    private ObservableList<TarUsuarioDto> usuarios = FXCollections.observableArrayList();
    @FXML
    private MFXButton btnSalir;
    @FXML
    private TabPane tapPaneEvaluacion;
    @FXML
    private Tab tab1;
    @FXML
    private TableView<?> tbvEvaluaciones;
    @FXML
    private JFXTextField txfBuscarEvaluacion;
    @FXML
    private MFXButton btnFiltrarEvaluacion;
    @FXML
    private JFXTextField txfBuscarPuestosAEvaluar;
    @FXML
    private MFXButton btnFiltroPuestosAEvaluar;
    @FXML
    private TableView<?> tbvPuestosFiltro;
    @FXML
    private TableView<?> tbvPuestosAsignados;
    @FXML
    private MFXButton btnActualizarEvaluacionPuestos;
    @FXML
    private Tab tab2;
    @FXML
    private JFXTextField txfBuscarTrabajadorAEvaluar;
    @FXML
    private MFXButton btnFiltrarTrabajadorAEvaluar;
    @FXML
    private TableView<?> tbvTrabajadorAEvaluarFiltro;
    @FXML
    private TableView<?> tbvTrabajadorAEvaluar;
    @FXML
    private MFXButton btnActualizarEvaluacionTrabEvaluados;
    @FXML
    private JFXTextField txfBuscarTrabajadorARealizarEva;
    @FXML
    private MFXButton btnFitrarTrabajadorARealizarEva;
    @FXML
    private TableView<?> tbvtnTrabajadorARealizarEvaFiltro;
    @FXML
    private TableView<?> tbvtnTrabajadorARealizarEva;
    @FXML
    private MFXButton btnActualizarEvaluacionRealizaEva;

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

        // Metodos para las tablas
        cargarTablaEvaluacion();
        cargarTablaFiltroPuestos();
        cargarTablaPuestosAsignados();
        cargarTablaTrabajadoresAEvaluarFiltro();
        cargarTablaTrabajadoresAEvaluar();
        cargarTablaTrabajadoresRealizaEvaFiltro();
        cargarTablaTrabajadoresRealizaEva();

        estadosEvaluacion();

        // Crear el ComboBox y establecer los valores para la tabla
//        comboBoxColumn.setCellFactory(param -> {
//            ComboBox<String> comboBox = new ComboBox<>();
//            comboBox.getItems().addAll("Cliente", "Compañero", "Jefatura");
//            return new TableCell<TarUsuarioDto, String>() {
//                @Override
//                protected void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//                    if (empty) {
//                        setGraphic(null);
//                    } else {
//                        comboBox.getSelectionModel().select(item);
//                        setGraphic(comboBox);
//                    }
//                }
//            };
//        });
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnAgregarEvaluacion(ActionEvent event) {
        crearActualizarEvaluacion();
    }

    @FXML
    private void onActionBtnLimpiarEvaluacion(ActionEvent event) {
    }

    @FXML
    private void onActionBtnFiltrarEvaluacion(ActionEvent event) {
    }

    //11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
    // Crear metodo para cargar las evaluaciones
    private void cargarTablaEvaluacion() {
    }

    // Crear metodo para crear/actualizar las evaluaciones se llama en varios botones
    private void crearActualizarEvaluacion() {
    }

    // Crear Metodo para eliminar Evaluacion despues se llama en el boton de la celda
    private void eliminarEvaluacion() {
    }

    private void nuevoEvaluacion() {
    }

    private void bindEvaluacion() {
    }

    private void unbindEvaluacion() {
    }

    //11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
    @FXML
    private void onActionBtnFiltroPuestosAEvaluar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnActualizarEvaluacionPuestos(ActionEvent event) {
        crearActualizarEvaluacion();
    }

    //222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222
    // Crear metodo para cargar el FILTRO de puestos
    private void cargarTablaFiltroPuestos() {
    }

    // Crear metodo para cargar los puestos asignados a la evaluacion
    private void cargarTablaPuestosAsignados() {
    }

    // Crear Metodo para eliminar Evaluacion despues se llama en el boton de la celda
    private void eliminarPuestos() {
    }
    //2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222

    @FXML
    private void onActionBtnFiltrarTrabajadorAEvaluar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnActualizarEvaluacionTrabEvaluados(ActionEvent event) {
        crearActualizarEvaluacion();
    }

    //3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333
    // Crear metodo para cargar el FILTRO de los trabajadorea a evaluar
    private void cargarTablaTrabajadoresAEvaluarFiltro() {
    }

    // Crear metodo para cargar los trabajadorea a evaluar
    private void cargarTablaTrabajadoresAEvaluar() {
    }

    // Crear Metodo para eliminar Evaluacion despues se llama en el boton de la celda
    private void eliminarTrabajadorAEvaluar() {
    }
    //-33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333

    @FXML
    private void onActionBtnFitrarTrabajadorARealizarEva(ActionEvent event) {
    }

    @FXML
    private void onActionBtnActualizarEvaluacionRealizaEva(ActionEvent event) {
        crearActualizarEvaluacion();
    }

    //444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444
    // Crear metodo para cargar el FILTRO de los trabajadorea que realizan evaluacion
    private void cargarTablaTrabajadoresRealizaEvaFiltro() {
    }

    // Crear metodo para cargar los trabajadorea que realizan evaluacion
    private void cargarTablaTrabajadoresRealizaEva() {

    }

    // Crear Metodo para eliminar Evaluacion despues se llama en el boton de la celda
    private void eliminarTTrabajadorARealizarEva() {
    }
    //4444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444

    @FXML
    private void onSelectionTab2(Event event) {
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
    }

    private void estadosEvaluacion() {
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

//    private class ButtonCellEliminarEvaluacion extends TableCell<TarProcesoevaluacionDto, Boolean> {
//
//        final MFXButton cellButton = new MFXButton();
//
//        ButtonCellEliminarEvaluacion() {
//            cellButton.setPrefWidth(500);
//            cellButton.setText("Eliminar");
//            cellButton.getStyleClass().add("mfx-button-menuSalir");
//
//            cellButton.setOnAction((ActionEvent t) -> {
//                TarProcesoevaluacionDto emp = (TarProcesoevaluacionDto) ButtonCellEliminarEvaluacion.this.getTableView().getItems().get(ButtonCellEliminarEvaluacion.this.getIndex());
//                //tbvTrabajadores.getItems().remove(emp);
//                tbvTrabajadores.refresh();
//            });
//        }
//
//        @Override
//        protected void updateItem(Boolean t, boolean empty) {
//            super.updateItem(t, empty);
//            if (!empty) {
//                setGraphic(cellButton);
//            }
//        }
//    }
}
