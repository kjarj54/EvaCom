package cr.ac.una.evacomuna.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.evacomuna.model.TarCompetenciaDto;
import cr.ac.una.evacomuna.model.TarCompetenciaDto;
import cr.ac.una.evacomuna.util.FlowController;
import cr.ac.una.evacomuna.util.Formato;
import cr.ac.una.evacomuna.model.TarCaracteristicaDto;
import cr.ac.una.evacomuna.model.TarCompetenciaDto;
import cr.ac.una.evacomuna.service.TarCaracteristicaService;
import cr.ac.una.evacomuna.service.TarCompetenciaService;
import cr.ac.una.evacomuna.service.TarCompetenciaService;
import cr.ac.una.evacomuna.service.TarCompetenciaService;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P08_MantenimientoCompetenciasController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txfCompetencia;
    @FXML
    private JFXCheckBox chkActiva;
    @FXML
    private MFXButton btnLimpiarCompetencia;
    @FXML
    private MFXButton btnAgregarCompetencia;
    @FXML
    private TableView<TarCompetenciaDto> tbvCompetencias;
    @FXML
    private JFXTextField txfBuscarNombre;
    @FXML
    private JFXCheckBox chkBuscarActivas;
    @FXML
    private MFXButton btnFiltrar;
    @FXML
    private JFXTextField txfCaracteristica;
    @FXML
    private TableView<TarCaracteristicaDto> tbvCaracteristicas;
    @FXML
    private MFXButton btnCrearCaracteristica;
    @FXML
    private MFXButton btnFiltrarCaracteristica;
    @FXML
    private JFXTextField txfCaracteristicaNueva;
    @FXML
    private TableView<TarCaracteristicaDto> tbvCaracteristicasBusqueda;
    @FXML
    private MFXButton btnSalir;

    private ObservableList<TarCompetenciaDto> competencias = FXCollections.observableArrayList();
    private ObservableList<TarCaracteristicaDto> caracteristicas = FXCollections.observableArrayList();
    TarCompetenciaDto tarCompetenciaDto;
    TarCaracteristicaDto tarCaracteristicaDto;
    @FXML
    private MFXButton btnActualizarCompetencia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);

        txfCompetencia.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txfBuscarNombre.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txfCaracteristica.setTextFormatter(Formato.getInstance().maxLengthFormat(80));
        txfCaracteristicaNueva.setTextFormatter(Formato.getInstance().maxLengthFormat(80));

        cargarTablaCompetencias();
        cargarTablaCaracteristicasBusqueda();
        cargarTablaCaracteristicasAsignadas();

        this.tarCompetenciaDto = new TarCompetenciaDto();
        this.tarCaracteristicaDto = new TarCaracteristicaDto();
        nuevoCompetencia();
        cargarPuestos("", "");
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnAgregarCompetencia(ActionEvent event) {
        crearActualizarCompetencia();
    }

    @FXML
    private void onActionBtnLimpiarCompetencia(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar Competencia", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            nuevoCompetencia();
            tbvCaracteristicas.getItems().clear();
            tbvCaracteristicas.refresh();
        }
    }

    @FXML
    private void onActionBtnFiltrar(ActionEvent event) {
        cargarPuestos(txfBuscarNombre.getText(), chkBuscarActivas.isSelected() ? "S" : "N");
    }

    private void cargarTablaCompetencias() {
        tbvCompetencias.getColumns().clear();
        tbvCompetencias.getItems().clear();

        TableColumn<TarCompetenciaDto, String> tbcId = new TableColumn<>("Id");
        tbcId.setPrefWidth(40);
        tbcId.setCellValueFactory(cd -> cd.getValue().comId);

        TableColumn<TarCompetenciaDto, String> tbcNombre = new TableColumn<>("Nombre");
        tbcNombre.setPrefWidth(250);
        tbcNombre.setCellValueFactory(cd -> cd.getValue().comNombre);

        TableColumn<TarCompetenciaDto, String> tbcEstado = new TableColumn<>("Estado");
        tbcEstado.setPrefWidth(70);
        tbcEstado.setCellValueFactory(cd -> {
            if ("A".equals(cd.getValue().getComEstado())) {
                return new SimpleStringProperty("Activo");
            } else {
                return new SimpleStringProperty("Inactivo");
            }
        });

        TableColumn<TarCompetenciaDto, Boolean> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setPrefWidth(100);
        tbcEliminar.setCellValueFactory((TableColumn.CellDataFeatures<TarCompetenciaDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
        tbcEliminar.setCellFactory((TableColumn<TarCompetenciaDto, Boolean> p) -> new ButtonCellEliminarCompetencia());

        tbvCompetencias.getColumns().add(tbcId);
        tbvCompetencias.getColumns().add(tbcNombre);
        tbvCompetencias.getColumns().add(tbcEstado);
        tbvCompetencias.getColumns().add(tbcEliminar);
        tbvCompetencias.refresh();

        tbvCompetencias.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                unbindCompetencia();
                tarCompetenciaDto = newValue;
                bindCompetencia();
                cargarCaracteristicas("");
                cargarCaracteristicasAsignadas();
                nuevoCaracteristica();
            } else {
                nuevoCompetencia();
            }
        });
    }

    private void cargarTablaCaracteristicasBusqueda() {
        tbvCaracteristicasBusqueda.getColumns().clear();
        tbvCaracteristicasBusqueda.getItems().clear();

        TableColumn<TarCaracteristicaDto, String> tbcCarIdBus = new TableColumn<>("Id");
        tbcCarIdBus.setPrefWidth(40);
        tbcCarIdBus.setCellValueFactory(cd -> cd.getValue().carId);

        TableColumn<TarCaracteristicaDto, String> tbcCarNombreBus = new TableColumn<>("Descripcion");
        tbcCarNombreBus.setPrefWidth(250);
        tbcCarNombreBus.setCellValueFactory(cd -> cd.getValue().carDescripcion);

        TableColumn<TarCaracteristicaDto, Boolean> tbcAgregar = new TableColumn<>("Agregar");
        tbcAgregar.setPrefWidth(130);
        tbcAgregar.setCellValueFactory((TableColumn.CellDataFeatures<TarCaracteristicaDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
        tbcAgregar.setCellFactory((TableColumn<TarCaracteristicaDto, Boolean> p) -> new ButtonCellAgregarCaracteristica());

        TableColumn<TarCaracteristicaDto, Boolean> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setPrefWidth(100);
        tbcEliminar.setCellValueFactory((TableColumn.CellDataFeatures<TarCaracteristicaDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
        tbcEliminar.setCellFactory((TableColumn<TarCaracteristicaDto, Boolean> p) -> new ButtonCellEliminarCaracteristica());

        tbvCaracteristicasBusqueda.getColumns().add(tbcCarIdBus);
        tbvCaracteristicasBusqueda.getColumns().add(tbcCarNombreBus);
        tbvCaracteristicasBusqueda.getColumns().add(tbcAgregar);
        tbvCaracteristicasBusqueda.getColumns().add(tbcEliminar);
        tbvCaracteristicasBusqueda.refresh();

        tbvCaracteristicasBusqueda.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                tarCaracteristicaDto = newValue;
            } else {
                nuevoCaracteristica();
            }
        });
    }

    private void cargarTablaCaracteristicasAsignadas() {
        tbvCaracteristicas.getColumns().clear();
        tbvCaracteristicas.getItems().clear();

        TableColumn<TarCaracteristicaDto, String> tbcCarId = new TableColumn<>("Id");
        tbcCarId.setPrefWidth(30);
        tbcCarId.setCellValueFactory(cd -> cd.getValue().carId);

        TableColumn<TarCaracteristicaDto, String> tbcCarNombre = new TableColumn<>("Descripcion");
        tbcCarNombre.setPrefWidth(200);
        tbcCarNombre.setCellValueFactory(cd -> cd.getValue().carDescripcion);

        TableColumn<TarCaracteristicaDto, Boolean> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setPrefWidth(75);
        tbcEliminar.setCellValueFactory(cd -> new SimpleObjectProperty(cd.getValue() != null));
        tbcEliminar.setCellFactory(cd -> new ButtonCell());

        tbvCaracteristicas.getColumns().add(tbcCarId);
        tbvCaracteristicas.getColumns().add(tbcCarNombre);
        tbvCaracteristicas.getColumns().add(tbcEliminar);
        tbvCaracteristicas.refresh();

    }

    private void cargarPuestos(String nombre, String activo) {
        TarCompetenciaService service = new TarCompetenciaService();
        Respuesta respuesta = service.getCompetencias(nombre, activo);

        if (respuesta.getEstado()) {
            tbvCompetencias.getItems().clear();
            tbvCaracteristicas.getItems().clear();
            competencias.clear();
            competencias.addAll((List<TarCompetenciaDto>) respuesta.getResultado("TarCompetencia"));
            tbvCompetencias.setItems(competencias);
            tbvCompetencias.refresh();
            tbvCaracteristicas.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Competencias", getStage(), respuesta.getMensaje());
        }
    }

    private void crearActualizarCompetencia() {
        try {
            if (txfCompetencia.getText().isBlank() || txfCompetencia.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Competencia", getStage(), " Campo de texto de nombre de competencia vacio");
            } else {
                TarCompetenciaService service = new TarCompetenciaService();
                Respuesta respuesta = service.guardarTarCompetencia(tarCompetenciaDto.consultas());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Competencia", getStage(), respuesta.getMensaje());
                } else {
                    tbvCompetencias.getItems().clear();
                    tbvCompetencias.refresh();
                    unbindCompetencia();
                    tarCompetenciaDto = (TarCompetenciaDto) respuesta.getResultado("Competencia");
                    bindCompetencia();
                    tbvCaracteristicasBusqueda.getItems().clear();
                    tbvCaracteristicasBusqueda.refresh();
                    nuevoCaracteristica();
                    cargarPuestos("", "");
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Competencia", getStage(), "Competencia actualizado correctamente.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P08_MantenimientoCompetenciasController.class.getName()).log(Level.SEVERE, "Error guardando la Competencia.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Competencia", getStage(), "Ocurrio un error guardando la Competencia.");
        }
    }

    private void eliminarPuesto() {
        if (new Mensaje().showConfirmation("Eliminar Competencia", getStage(), "¿Esta seguro que desea eliminar la competencia?")) {
            try {
                if (tarCompetenciaDto.getComId() == null) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Competencia", getStage(), "Debe cargar la Competencia que desea eliminar.");
                } else {
                    TarCompetenciaService service = new TarCompetenciaService();
                    Respuesta respuesta = service.eliminarCompetencia(tarCompetenciaDto.getComId());
                    if (!respuesta.getEstado()) {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Competencia", getStage(), respuesta.getMensaje());
                    } else {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Competencia", getStage(), "Competencia eliminado correctamente.");
                        nuevoCompetencia();
                        cargarPuestos("", "");
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(P08_MantenimientoCompetenciasController.class.getName()).log(Level.SEVERE, "Error eliminando el Competencia.", ex);
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Competencia", getStage(), "Ocurrio un error eliminando el Competencia.");
            }
        }
    }

    private void nuevoCompetencia() {
        unbindCompetencia();
        tarCompetenciaDto = new TarCompetenciaDto();
        bindCompetencia();
        nuevoCaracteristica();
        txfCompetencia.clear();
        txfCompetencia.requestFocus();
    }

    private void bindCompetencia() {
        txfCompetencia.textProperty().bindBidirectional(tarCompetenciaDto.comNombre);
        chkActiva.selectedProperty().bindBidirectional(tarCompetenciaDto.comEstado);
    }

    private void unbindCompetencia() {
        txfCompetencia.textProperty().unbindBidirectional(tarCompetenciaDto.comNombre);
        chkActiva.selectedProperty().unbindBidirectional(tarCompetenciaDto.comEstado);
    }

    @FXML
    private void onActionBtnCrearCaracteristica(ActionEvent event) {
        try {
            tarCaracteristicaDto.setCarDescripcion(txfCaracteristicaNueva.getText());
//            String invalidos = validarRequeridos();
//            if (!invalidos.isEmpty()) {
//                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Competencia", getStage(), invalidos);
//            } else {
            TarCaracteristicaService service = new TarCaracteristicaService();
            Respuesta respuesta = service.guardarTarCaracteristica(tarCaracteristicaDto.consultas());
            if (!respuesta.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Caracteristica", getStage(), respuesta.getMensaje());
            } else {
                tarCaracteristicaDto = (TarCaracteristicaDto) respuesta.getResultado("Caracteristica");
                nuevoCaracteristica();
                tbvCaracteristicasBusqueda.getItems().clear();
                tbvCaracteristicas.getItems().clear();
                tbvCaracteristicasBusqueda.refresh();
                tbvCaracteristicas.refresh();
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Caracteristica", getStage(), "Caracteristica actualizado correctamente.");
            }
//            }
        } catch (Exception ex) {
            Logger.getLogger(P08_MantenimientoCompetenciasController.class.getName()).log(Level.SEVERE, "Error guardando la Caracteristica.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Caracteristica", getStage(), "Ocurrio un error guardando la Caracteristica.");
        }
    }

    @FXML
    private void onActionBtnFiltrarCaracteristica(ActionEvent event) {
        cargarCaracteristicas(txfCaracteristica.getText());
    }

    @FXML
    private void onActionBtnActualizarCompetencia(ActionEvent event) {
        crearActualizarCompetencia();
    }

    private void nuevoCaracteristica() {
        tarCaracteristicaDto = new TarCaracteristicaDto();
        txfCaracteristica.clear();
        txfCaracteristicaNueva.clear();
    }

    private void agregarCaracteristica() {
        if (tarCompetenciaDto.getComId() == null || tarCaracteristicaDto.getCarId() == null) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Agregar Caracteristica", getStage(), "Es necesario cargar una Competencia para agregarla a la lista.");
        } else if (tbvCaracteristicas.getItems() == null
                || !tbvCaracteristicas.getItems().stream().anyMatch(a -> a.getCarId().equals(tarCaracteristicaDto.getCarId()))) {
            tarCaracteristicaDto.setModificado(true);
            tarCompetenciaDto.getTarCaracteristicaList().add(tarCaracteristicaDto);
            tbvCaracteristicas.getItems().add(tarCaracteristicaDto);
            tbvCaracteristicas.refresh();
        }
    }

    private void cargarCaracteristicasAsignadas() {
        ObservableList<TarCaracteristicaDto> compe = FXCollections.observableArrayList();
        compe.addAll(tarCompetenciaDto.getTarCaracteristicaList());

        tbvCaracteristicas.getItems().clear();
        tbvCaracteristicas.setItems(compe);
        tbvCaracteristicas.refresh();
    }

    private void cargarCaracteristicas(String nombre) {
        TarCaracteristicaService service = new TarCaracteristicaService();
        Respuesta respuesta = service.getCaracteristicas(nombre);

        if (respuesta.getEstado()) {
            tbvCaracteristicasBusqueda.getItems().clear();
            caracteristicas.clear();
            caracteristicas.addAll((List<TarCaracteristicaDto>) respuesta.getResultado("Caracteristica"));
            tbvCaracteristicasBusqueda.setItems(caracteristicas);
            tbvCaracteristicasBusqueda.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Caracteristicas", getStage(), respuesta.getMensaje());
        }
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    private class ButtonCellEliminarCompetencia extends TableCell<TarCompetenciaDto, Boolean> {

        final MFXButton cellButton = new MFXButton("X");

        ButtonCellEliminarCompetencia() {
            cellButton.setPrefWidth(100);
            cellButton.getStyleClass().add("mfx-button-menuSalir");

            cellButton.setOnAction((ActionEvent t) -> {
                int index = ButtonCellEliminarCompetencia.this.getIndex();
                tarCompetenciaDto = (TarCompetenciaDto) ButtonCellEliminarCompetencia.this.getTableView().getItems().get(index);
                eliminarPuesto();
                tbvCompetencias.getItems().remove(tarCompetenciaDto);
                tbvCompetencias.refresh();
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

    private class ButtonCellEliminarCaracteristica extends TableCell<TarCaracteristicaDto, Boolean> {

        final MFXButton cellButton = new MFXButton("X");

        ButtonCellEliminarCaracteristica() {
            cellButton.setPrefWidth(100);
            cellButton.getStyleClass().add("mfx-button-menuSalir");

            cellButton.setOnAction((ActionEvent t) -> {
                int index = ButtonCellEliminarCaracteristica.this.getIndex();
                tarCaracteristicaDto = (TarCaracteristicaDto) ButtonCellEliminarCaracteristica.this.getTableView().getItems().get(index);
                //eliminarCaracteristica();
                tbvCaracteristicasBusqueda.getItems().remove(tarCaracteristicaDto);
                tbvCaracteristicasBusqueda.refresh();
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

    private class ButtonCellAgregarCaracteristica extends TableCell<TarCaracteristicaDto, Boolean> {

        final MFXButton cellButton = new MFXButton("Agregar");

        ButtonCellAgregarCaracteristica() {
            cellButton.setPrefWidth(100);
            cellButton.getStyleClass().add("mfx-button-menuPrincipal");

            cellButton.setOnAction((ActionEvent t) -> {
                int index = ButtonCellAgregarCaracteristica.this.getIndex();
                tarCaracteristicaDto = (TarCaracteristicaDto) ButtonCellAgregarCaracteristica.this.getTableView().getItems().get(index);
                agregarCaracteristica();
                tbvCaracteristicasBusqueda.refresh();
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

    private class ButtonCell extends TableCell<TarCaracteristicaDto, Boolean> {

        final MFXButton cellButton = new MFXButton();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.setText("X");
            cellButton.getStyleClass().add("mfx-button-menuSalir");

            cellButton.setOnAction((ActionEvent t) -> {
                TarCaracteristicaDto car = (TarCaracteristicaDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                if (!car.getModificado()) {
                    tarCompetenciaDto.getTarCaracteristicaEliminados().add(car);
                }
                tbvCaracteristicas.getItems().remove(car);
                tbvCaracteristicas.refresh();
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
