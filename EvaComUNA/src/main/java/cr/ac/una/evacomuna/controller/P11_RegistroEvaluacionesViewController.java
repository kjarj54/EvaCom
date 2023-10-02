package cr.ac.una.evacomuna.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.evacomuna.model.TarCompetenciaDto;
import cr.ac.una.evacomuna.model.TarProcesoevaluacionDto;
import cr.ac.una.evacomuna.model.TarPuestoDto;
import cr.ac.una.evacomuna.model.TarTrabajadorevaluarDto;
import cr.ac.una.evacomuna.model.TarUsuarioDto;
import cr.ac.una.evacomuna.service.TarProcesoevaluacionService;
import cr.ac.una.evacomuna.service.TarPuestoService;
import cr.ac.una.evacomuna.service.TarUsuarioService;
import cr.ac.una.evacomuna.util.Mensaje;
import cr.ac.una.evacomuna.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
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
    @FXML
    private MFXButton btnSalir;
    @FXML
    private TabPane tapPaneEvaluacion;
    @FXML
    private Tab tab1;
    @FXML
    private TableView<TarProcesoevaluacionDto> tbvEvaluaciones;
    @FXML
    private JFXTextField txfBuscarEvaluacion;
    @FXML
    private MFXButton btnFiltrarEvaluacion;
    @FXML
    private Tab tab2;
    @FXML
    private JFXTextField txfBuscarTrabajadorAEvaluar;
    @FXML
    private MFXButton btnFiltrarTrabajadorAEvaluar;
    @FXML
    private TableView<TarUsuarioDto> tbvTrabajadorAEvaluarFiltro;
    @FXML
    private TableView<TarTrabajadorevaluarDto> tbvTrabajadorAEvaluar;
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

    private ObservableList<TarProcesoevaluacionDto> evaluaciones = FXCollections.observableArrayList();
    private ObservableList<TarUsuarioDto> usuariosEva = FXCollections.observableArrayList();
    private ObservableList<TarUsuarioDto> evaUsuarios = FXCollections.observableArrayList();

    TarProcesoevaluacionDto tarEvaluacionDto;
    TarUsuarioDto tarUsuarioEvaDto;
    TarUsuarioDto tarEvaUsuarioDto;
    @FXML
    private JFXTextField txfBuscarPuestosAEvaluar;

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
        cargarTablaFiltroProcesoEvaluacions();
        cargarTablaProcesoEvaluacionsAsignados();
        cargarTablaTrabajadoresAEvaluarFiltro();
        cargarTablaTrabajadoresAEvaluar();
        cargarTablaTrabajadoresRealizaEvaFiltro();
        cargarTablaTrabajadoresRealizaEva();

        estadosEvaluacion();

        this.tarEvaluacionDto = new TarProcesoevaluacionDto();
        nuevoProcesoEvaluacion();
        this.tarUsuarioEvaDto = new TarUsuarioDto();
        this.tarEvaUsuarioDto = new TarUsuarioDto();

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
        TarProcesoevaluacionService service = new TarProcesoevaluacionService();
        Respuesta respuesta = service.getProcesosevaluacion();

        if (respuesta.getEstado()) {
            tbvEvaluaciones.getItems().clear();
//            tbvCompetencias.getItems().clear();
            evaluaciones.clear();
            evaluaciones.addAll((List<TarProcesoevaluacionDto>) respuesta.getResultado("Procesoevaluacion"));
            tbvEvaluaciones.setItems(evaluaciones);
            tbvEvaluaciones.refresh();
//            tbvCompetencias.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Proceso Evaluacion", getStage(), respuesta.getMensaje());
        }
    }

    //11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
    // Crear metodo para cargar las evaluaciones
    private void cargarTablaEvaluacion() {
        tbvEvaluaciones.getColumns().clear();
        tbvEvaluaciones.getItems().clear();

        TableColumn<TarProcesoevaluacionDto, String> tbcId = new TableColumn<>("Id");
        tbcId.setPrefWidth(40);
        tbcId.setCellValueFactory(cd -> cd.getValue().proId);

        TableColumn<TarProcesoevaluacionDto, String> tbcNombre = new TableColumn<>("Nombre");
        tbcNombre.setPrefWidth(250);
        tbcNombre.setCellValueFactory(cd -> cd.getValue().proTitulo);

        TableColumn<TarProcesoevaluacionDto, Boolean> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setPrefWidth(100);
        tbcEliminar.setCellValueFactory((TableColumn.CellDataFeatures<TarProcesoevaluacionDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
        tbcEliminar.setCellFactory((TableColumn<TarProcesoevaluacionDto, Boolean> p) -> new ButtonCell());

        tbvEvaluaciones.getColumns().add(tbcId);
        tbvEvaluaciones.getColumns().add(tbcNombre);
        tbvEvaluaciones.getColumns().add(tbcEliminar);
        tbvEvaluaciones.refresh();

        tbvEvaluaciones.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                unbindProcesoEvaluacion();
                tarEvaluacionDto = newValue;
                bindProcesoEvaluacion();
//                disableNodes(false);

//                cargarCompetencias("");
//                cargarCompetencias();
//                nuevoCompetencia();
            } else {
                nuevoProcesoEvaluacion();
            }
        });
    }

    // Crear metodo para crear/actualizar las evaluaciones se llama en varios botones
    private void crearActualizarEvaluacion() {
        try {
            tarEvaluacionDto.setProEstado(getEstado());
            if (txfTituloEvaluacion.getText().isBlank()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Evaluacion", getStage(), "Campo de texto de nombre de Evaluacion vacia");
            } else {
                TarProcesoevaluacionService service = new TarProcesoevaluacionService();
                Respuesta respuesta = service.guardarTarProcesoevaluacion(tarEvaluacionDto.consultas());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Evaluacion", getStage(), respuesta.getMensaje());
                } else {
                    tbvEvaluaciones.getItems().clear();
                    tbvEvaluaciones.refresh();
                    unbindProcesoEvaluacion();
                    tarEvaluacionDto = (TarProcesoevaluacionDto) respuesta.getResultado("Procesoevaluacion");
                    bindProcesoEvaluacion();
//                    cargarPuestosAutomatico();
//                    nuevoCompetencia();
//                    cargarCompetencias("");
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Evaluacion", getStage(), "Evaluacion guardado/actualizado correctamente.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P09_MantenimientoPuestosViewController.class.getName()).log(Level.SEVERE, "Error guardando el Evaluacion.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Evaluacion", getStage(), "Ocurrio un error guardando el Evaluacion.");
        }
    }

    // Crear Metodo para eliminar Evaluacion despues se llama en el boton de la celda
    private void eliminarEvaluacion() {
    }

    private void nuevoProcesoEvaluacion() {
        unbindProcesoEvaluacion();
        tarEvaluacionDto = new TarProcesoevaluacionDto();
        bindProcesoEvaluacion();
//        nuevoCompetencia();
        txfBuscarEvaluacion.clear();
        txfBuscarEvaluacion.requestFocus();
    }

    private void bindProcesoEvaluacion() {
        txfTituloEvaluacion.textProperty().bindBidirectional(tarEvaluacionDto.proTitulo);
        dtpFechaInicio.valueProperty().bindBidirectional(tarEvaluacionDto.proFfin);
        dtpFechaFinaliza.valueProperty().bindBidirectional(tarEvaluacionDto.proFfin);
//        dtpFechaAplicar.valueProperty().bindBidirectional(tarEvaluacionDto.);
        estadosEvaluacion();
        if (tarEvaluacionDto.getProEstado() != null) {
            seleccionarEstado(tarEvaluacionDto.getProEstado());
            System.out.println("Bind " + cboxEstado.getValue());
        }
//        cboxEstado.valueProperty().bindBidirectional(tarEvaluacionDto.proEstado);
    }

    private void unbindProcesoEvaluacion() {
        txfTituloEvaluacion.textProperty().unbindBidirectional(tarEvaluacionDto.proTitulo);
        dtpFechaInicio.valueProperty().unbindBidirectional(tarEvaluacionDto.proFfin);
        dtpFechaFinaliza.valueProperty().unbindBidirectional(tarEvaluacionDto.proFfin);
//        dtpFechaAplicar.valueProperty().unbindBidirectional(tarEvaluacionDto.);
        cboxEstado.valueProperty().unbindBidirectional(tarEvaluacionDto.proEstado);
    }

    private void onActionBtnActualizarEvaluacionProcesoEvaluacions(ActionEvent event) {
        crearActualizarEvaluacion();
    }

    //222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222
    // Crear metodo para cargar el FILTRO de puestos
    private void cargarTablaFiltroProcesoEvaluacions() {
    }

    // Crear metodo para cargar los puestos asignados a la evaluacion
    private void cargarTablaProcesoEvaluacionsAsignados() {
    }

    // Crear Metodo para eliminar Evaluacion despues se llama en el boton de la celda
    private void eliminarProcesoEvaluacions() {
    }
    //2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222

    @FXML
    private void onActionBtnFiltrarTrabajadorAEvaluar(ActionEvent event) {
        TarUsuarioService service = new TarUsuarioService();
        Respuesta respuesta = service.getUsuarios("", txfBuscarTrabajadorAEvaluar.getText(), "", txfBuscarPuestosAEvaluar.getText());

        if (respuesta.getEstado()) {
            tbvTrabajadorAEvaluarFiltro.getItems().clear();
//            tbvCompetencias.getItems().clear();
            usuariosEva.clear();
            usuariosEva.addAll((List<TarUsuarioDto>) respuesta.getResultado("TarUsuario"));
            tbvTrabajadorAEvaluarFiltro.setItems(usuariosEva);
            tbvTrabajadorAEvaluarFiltro.refresh();
//            tbvCompetencias.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Proceso Evaluacion", getStage(), respuesta.getMensaje());
        }
    }

    @FXML
    private void onActionBtnActualizarEvaluacionTrabEvaluados(ActionEvent event) {
        crearActualizarEvaluacion();
    }

    //3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333
    // Crear metodo para cargar el FILTRO de los trabajadorea a evaluar
    private void cargarTablaTrabajadoresAEvaluarFiltro() {
        tbvTrabajadorAEvaluarFiltro.getColumns().clear();
        tbvTrabajadorAEvaluarFiltro.getItems().clear();

        TableColumn<TarUsuarioDto, String> tbcId = new TableColumn<>("Id");
        tbcId.setPrefWidth(30);
        tbcId.setCellValueFactory(cd -> cd.getValue().usuId);

        TableColumn<TarUsuarioDto, String> tbcNombre = new TableColumn<>("Nombre");
        tbcNombre.setPrefWidth(150);
        tbcNombre.setCellValueFactory(cd -> cd.getValue().usuNombre);

        TableColumn<TarUsuarioDto, String> tbcApellido = new TableColumn<>("Apellido");
        tbcApellido.setPrefWidth(200);
        tbcApellido.setCellValueFactory(cd -> cd.getValue().usuApellido);

        TableColumn<TarUsuarioDto, String> tbcEstado = new TableColumn<>("Estado");
        tbcEstado.setPrefWidth(70);
        tbcEstado.setCellValueFactory(cd -> {
            if ("A".equals(cd.getValue().getUsuActivo())) {
                return new SimpleStringProperty("Activo");
            } else {
                return new SimpleStringProperty("Inactivo");
            }
        });

        TableColumn<TarUsuarioDto, String> tbcAdmin = new TableColumn<>("Admin");
        tbcAdmin.setPrefWidth(80);
        tbcAdmin.setCellValueFactory(cd -> {
            if ("S".equals(cd.getValue().getUsuActivo())) {
                return new SimpleStringProperty("Admin");
            } else {
                return new SimpleStringProperty("No Admin");
            }
        });

        TableColumn<TarUsuarioDto, Boolean> tbcAgregar = new TableColumn<>("Agregar");
        tbcAgregar.setPrefWidth(100);
        tbcAgregar.setCellValueFactory(cd -> new SimpleObjectProperty(cd.getValue() != null));
        tbcAgregar.setCellFactory((TableColumn<TarUsuarioDto, Boolean> p) -> new ButtonCell2());

        tbvTrabajadorAEvaluarFiltro.getColumns().add(tbcId);
        tbvTrabajadorAEvaluarFiltro.getColumns().add(tbcNombre);
        tbvTrabajadorAEvaluarFiltro.getColumns().add(tbcApellido);
        tbvTrabajadorAEvaluarFiltro.getColumns().add(tbcEstado);
        tbvTrabajadorAEvaluarFiltro.getColumns().add(tbcAdmin);
        tbvTrabajadorAEvaluarFiltro.getColumns().add(tbcAgregar);
        tbvTrabajadorAEvaluarFiltro.refresh();

        tbvTrabajadorAEvaluarFiltro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                tarUsuarioEvaDto = newValue;
            } else {
                nuevoUsuarioEva();
            }
        });
    }

    private void agregarTrabajadoresAEvaluar() {
        if (tarEvaluacionDto.getProId() == null || tarUsuarioEvaDto.getUsuId() == null) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Agregar Competencia", getStage(), "Es necesario cargar una Puesto para agregarla a la lista.");
        } else if (tbvTrabajadorAEvaluar.getItems() == null || !tbvTrabajadorAEvaluar.getItems().stream().anyMatch(a -> a.getUsuarioDto().getUsuNombre().equals(tarUsuarioEvaDto.getUsuNombre()))) {

//            tarUsuarioEvaDto.setModificado(true);
            TarTrabajadorevaluarDto tarTrabajadorevaluarDto = new TarTrabajadorevaluarDto();
            tarTrabajadorevaluarDto.setUsuarioDto(tarUsuarioEvaDto);
            tarTrabajadorevaluarDto.setProcesoDto(tarEvaluacionDto);
            tarTrabajadorevaluarDto.setModificado(true);

            tarEvaluacionDto.getTarTrabajadorevaluarList().add(tarTrabajadorevaluarDto);
            tbvTrabajadorAEvaluar.getItems().add(tarTrabajadorevaluarDto);
            tbvTrabajadorAEvaluar.refresh();
        }
    }

    // Crear metodo para cargar los trabajadorea a evaluar
    private void cargarTablaTrabajadoresAEvaluar() {
        tbvTrabajadorAEvaluar.getColumns().clear();
        tbvTrabajadorAEvaluar.getItems().clear();

        TableColumn<TarTrabajadorevaluarDto, String> tbcId = new TableColumn<>("Id");
        tbcId.setPrefWidth(30);
        tbcId.setCellValueFactory(cd -> cd.getValue().getUsuarioDto().usuId);

        TableColumn<TarTrabajadorevaluarDto, String> tbcNombre = new TableColumn<>("Nombre");
        tbcNombre.setPrefWidth(150);
        tbcNombre.setCellValueFactory(cd -> cd.getValue().getUsuarioDto().usuNombre);

        TableColumn<TarTrabajadorevaluarDto, String> tbcApellido = new TableColumn<>("Apellido");
        tbcApellido.setPrefWidth(200);
        tbcApellido.setCellValueFactory(cd -> cd.getValue().getUsuarioDto().usuApellido);

        TableColumn<TarTrabajadorevaluarDto, String> tbcEstado = new TableColumn<>("Estado");
        tbcEstado.setPrefWidth(70);
        tbcEstado.setCellValueFactory(cd -> {
            if ("A".equals(cd.getValue().getUsuarioDto().getUsuActivo())) {
                return new SimpleStringProperty("Activo");
            } else {
                return new SimpleStringProperty("Inactivo");
            }
        });

        TableColumn<TarTrabajadorevaluarDto, String> tbcAdmin = new TableColumn<>("Admin");
        tbcAdmin.setPrefWidth(80);
        tbcAdmin.setCellValueFactory(cd -> {
            if ("S".equals(cd.getValue().getUsuarioDto().getUsuActivo())) {
                return new SimpleStringProperty("Admin");
            } else {
                return new SimpleStringProperty("No Admin");
            }
        });

        tbvTrabajadorAEvaluar.getColumns().add(tbcId);
        tbvTrabajadorAEvaluar.getColumns().add(tbcNombre);
        tbvTrabajadorAEvaluar.getColumns().add(tbcApellido);
        tbvTrabajadorAEvaluar.getColumns().add(tbcEstado);
        tbvTrabajadorAEvaluar.getColumns().add(tbcAdmin);
        tbvTrabajadorAEvaluar.refresh();
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
        cboxEstado.getSelectionModel().clearSelection();
        cboxEstado.getItems().clear();
        ObservableList<String> elementos = FXCollections.observableArrayList(
                "En construcción",
                "En aplicación",
                "En revisión",
                "Finalizada"
        );

        // Agregar la lista de elementos al ComboBox
        cboxEstado.setItems(elementos);
    }

    private void seleccionarEstado(String nombrePuesto) {
        for (String proceso : cboxEstado.getItems()) {
            if (proceso.equals("En construcción") && nombrePuesto.equals("C")) {
                cboxEstado.getSelectionModel().select(proceso);
                break;
            } else if (proceso.equals("En aplicación") && nombrePuesto.equals("A")) {
                cboxEstado.getSelectionModel().select(proceso);
                break;
            } else if (proceso.equals("En revisión") && nombrePuesto.equals("R")) {
                cboxEstado.getSelectionModel().select(proceso);
                break;
            } else if (proceso.equals("Finalizada") && nombrePuesto.equals("F")) {
                cboxEstado.getSelectionModel().select(proceso);
                break;
            }

        }
    }

    private String getEstado() {
        String proceso = cboxEstado.getValue();
        switch (proceso) {
            case "En construcción" -> {
                return "C";
            }
            case "En aplicación" -> {
                return "A";
            }
            case "En revisión" -> {
                return "R";
            }
            case "Finalizada" -> {
                return "F";
            }
            default -> {
            }
        }
        return null;
    }

    private void nuevoUsuarioEva() {
        tarUsuarioEvaDto = new TarUsuarioDto();
        txfBuscarPuestosAEvaluar.clear();
        txfBuscarTrabajadorAEvaluar.clear();
//        tbvCompetenciasBusqueda.getItems().clear();
//        tbvCompetenciasBusqueda.refresh();
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
    private class ButtonCell extends TableCell<TarProcesoevaluacionDto, Boolean> {

        final MFXButton cellButton = new MFXButton("X");

        ButtonCell() {
            cellButton.setPrefWidth(100);
            cellButton.getStyleClass().add("mfx-button-menuSalir");

            cellButton.setOnAction((ActionEvent t) -> {

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

    private class ButtonCell2 extends TableCell<TarUsuarioDto, Boolean> {

        final MFXButton cellButton2 = new MFXButton("Agregar");

        ButtonCell2() {
            cellButton2.setPrefWidth(120);
            cellButton2.getStyleClass().add("mfx-button-menuSalir");

            cellButton2.setOnAction((ActionEvent t) -> {
                int index = ButtonCell2.this.getIndex();
                tarUsuarioEvaDto = (TarUsuarioDto) ButtonCell2.this.getTableView().getItems().get(index);
                agregarTrabajadoresAEvaluar();
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton2);
            }
        }
    }
}
