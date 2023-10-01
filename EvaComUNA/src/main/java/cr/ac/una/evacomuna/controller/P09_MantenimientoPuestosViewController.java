package cr.ac.una.evacomuna.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.evacomuna.model.TarCompetenciaDto;
import cr.ac.una.evacomuna.model.TarPuestoDto;
import cr.ac.una.evacomuna.model.TarUsuarioDto;
import cr.ac.una.evacomuna.service.TarCompetenciaService;
import cr.ac.una.evacomuna.service.TarPuestoService;
import cr.ac.una.evacomuna.util.Formato;
import cr.ac.una.evacomuna.util.Mensaje;
import cr.ac.una.evacomuna.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P09_MantenimientoPuestosViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField txfPuesto;
    @FXML
    private JFXCheckBox chkActiva;
    @FXML
    private MFXButton btnEliminarPuesto;
    @FXML
    private MFXButton btnLimpiarPuesto;
    @FXML
    private MFXButton btnAgregarPuesto;
    @FXML
    private TableView<TarCompetenciaDto> tbvCompetencias;
    @FXML
    private TableView<TarCompetenciaDto> tbvCompetenciasBusqueda;
    @FXML
    private JFXTextField txfBuscarNombre;
    @FXML
    private JFXCheckBox chkBuscarActivas;
    @FXML
    private MFXButton btnFiltrar;
    @FXML
    private JFXTextField txfBuscarCompetencia;
    @FXML
    private MFXButton btnAgregarCompetencia;
    @FXML
    private TableView<TarPuestoDto> tbvPuestos;
    @FXML
    private MFXButton btnSalir;

    private ObservableList<TarPuestoDto> puestos = FXCollections.observableArrayList();
    private ObservableList<TarCompetenciaDto> competencias = FXCollections.observableArrayList();
    TarPuestoDto tarPuestoDto;
    TarCompetenciaDto tarCompetenciaDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txfPuesto.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txfBuscarNombre.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txfBuscarCompetencia.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        tbvPuestos.getColumns().clear();
        tbvPuestos.getItems().clear();

        TableColumn<TarPuestoDto, String> tbcId = new TableColumn<>("Id");
        tbcId.setPrefWidth(30);
        tbcId.setCellValueFactory(cd -> cd.getValue().pueId);

        TableColumn<TarPuestoDto, String> tbcNombre = new TableColumn<>("Nombre");
        tbcNombre.setPrefWidth(100);
        tbcNombre.setCellValueFactory(cd -> cd.getValue().pueNombre);

        TableColumn<TarPuestoDto, String> tbcEstado = new TableColumn<>("Estado");
        tbcEstado.setPrefWidth(50);
        tbcEstado.setCellValueFactory(cd -> {
            if ("A".equals(cd.getValue().getPueEstado())) {
                return new SimpleStringProperty("Activo");
            } else {
                return new SimpleStringProperty("Inactivo");
            }
        });

        tbvPuestos.getColumns().add(tbcId);
        tbvPuestos.getColumns().add(tbcNombre);
        tbvPuestos.getColumns().add(tbcEstado);
        tbvPuestos.refresh();
        
        tbvCompetenciasBusqueda.getColumns().clear();
        tbvCompetenciasBusqueda.getItems().clear();

        TableColumn<TarCompetenciaDto, String> tbcComIdBus = new TableColumn<>("Id");
        tbcComIdBus.setPrefWidth(30);
        tbcComIdBus.setCellValueFactory(cd -> cd.getValue().comId);

        TableColumn<TarCompetenciaDto, String> tbcComNombreBus = new TableColumn<>("Nombre");
        tbcComNombreBus.setPrefWidth(100);
        tbcComNombreBus.setCellValueFactory(cd -> cd.getValue().comNombre);

        TableColumn<TarCompetenciaDto, String> tbcComEstadoBus = new TableColumn<>("Estado");
        tbcComEstadoBus.setPrefWidth(50);
        tbcComEstadoBus.setCellValueFactory(cd -> {
            if ("A".equals(cd.getValue().getComEstado())) {
                return new SimpleStringProperty("Activo");
            } else {
                return new SimpleStringProperty("Inactivo");
            }
        });
        
        tbvCompetenciasBusqueda.getColumns().add(tbcComIdBus);
        tbvCompetenciasBusqueda.getColumns().add(tbcComNombreBus);
        tbvCompetenciasBusqueda.getColumns().add(tbcComEstadoBus);
        tbvCompetenciasBusqueda.refresh();

        tbvCompetenciasBusqueda.getColumns().clear();
        tbvCompetenciasBusqueda.getItems().clear();

        TableColumn<TarCompetenciaDto, String> tbcComId = new TableColumn<>("Id");
        tbcComId.setPrefWidth(30);
        tbcComId.setCellValueFactory(cd -> cd.getValue().comId);

        TableColumn<TarCompetenciaDto, String> tbcComNombre = new TableColumn<>("Nombre");
        tbcComNombre.setPrefWidth(100);
        tbcComNombre.setCellValueFactory(cd -> cd.getValue().comNombre);

        TableColumn<TarCompetenciaDto, String> tbcComEstado = new TableColumn<>("Estado");
        tbcComEstado.setPrefWidth(50);
        tbcComEstado.setCellValueFactory(cd -> {
            if ("A".equals(cd.getValue().getComEstado())) {
                return new SimpleStringProperty("Activo");
            } else {
                return new SimpleStringProperty("Inactivo");
            }
        });
        
        tbvCompetencias.getColumns().add(tbcComId);
        tbvCompetencias.getColumns().add(tbcComNombre);
        tbvCompetencias.getColumns().add(tbcComEstado);
        tbvCompetencias.refresh();

        this.tarPuestoDto = new TarPuestoDto();
        this.tarCompetenciaDto = new TarCompetenciaDto();
        nuevoPuesto();

        tbvPuestos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                unbindPuesto();
                tarPuestoDto = newValue;
                bindPuesto(false);
                cargarCompetencias();
            } else {
                nuevoPuesto();
            }
        });
        
        tbvCompetenciasBusqueda.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                unbindCompetencia();
                tarCompetenciaDto = newValue;
                bindCompetencia(false);
            } else {
                nuevoCompetencia();
            }
        });
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnEliminarPuesto(ActionEvent event) {
        try {
            if (tarPuestoDto.getPueId() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Puesto", getStage(), "Debe cargar el Puesto que desea eliminar.");
            } else {
                TarPuestoService service = new TarPuestoService();
                Respuesta respuesta = service.eliminarPuesto(tarPuestoDto.getPueId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Puesto", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Puesto", getStage(), "Puesto eliminado correctamente.");
                    nuevoPuesto();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P09_MantenimientoPuestosViewController.class.getName()).log(Level.SEVERE, "Error eliminando el Puesto.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Puesto", getStage(), "Ocurrio un error eliminando el Puesto.");
        }
    }

    @FXML
    private void onActionBtnLimpiarPuesto(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar Puesto", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            nuevoPuesto();
        }
    }

    @FXML
    private void onActionBtnAgregarPuesto(ActionEvent event) {
        try {
//            String invalidos = validarRequeridos();
//            if (!invalidos.isEmpty()) {
//                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tipo planilla", getStage(), invalidos);
//            } else {
            TarPuestoService service = new TarPuestoService();
            Respuesta respuesta = service.guardarTarPuesto(tarPuestoDto.consultas());
            if (!respuesta.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Puesto", getStage(), respuesta.getMensaje());
            } else {
                unbindPuesto();
                tarPuestoDto = (TarPuestoDto) respuesta.getResultado("Puesto");
                bindPuesto(false);
                nuevoCompetencia();
                onActionBtnFiltrar(event);
                cargarCompetencias();
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Puesto", getStage(), "Puesto actualizado correctamente.");
            }
//            }
        } catch (Exception ex) {
            Logger.getLogger(P09_MantenimientoPuestosViewController.class.getName()).log(Level.SEVERE, "Error guardando el Puesto.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Puesto", getStage(), "Ocurrio un error guardando el Puesto.");
        }
    }

    @FXML
    private void onActionBtnAgregarCompetencia(ActionEvent event) {
        if (tarCompetenciaDto.getComId() == null || tarCompetenciaDto.getComNombre().isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Agregar Competencia", getStage(), "Es necesario cargar una Competencia para agregarla a la lista.");
        } else if (tbvCompetencias.getItems() == null || !tbvCompetencias.getItems().stream().anyMatch(a -> a.equals(tarCompetenciaDto))) {
            tarCompetenciaDto.setModificado(true);
            tbvCompetencias.getItems().add(tarCompetenciaDto);
            tbvCompetencias.refresh();
        }
        nuevoCompetencia();
    }

    @FXML
    private void onActionBtnFiltrar(ActionEvent event) {
        TarPuestoService service = new TarPuestoService();
        Respuesta respuesta = service.getPuestos(txfBuscarNombre.getText(), chkBuscarActivas.isSelected() ? "S" : "N");

        if (respuesta.getEstado()) {
            tbvPuestos.getItems().clear();
            puestos.clear();
            puestos.addAll((List<TarPuestoDto>) respuesta.getResultado("TarPuestos"));
            tbvPuestos.setItems(puestos);
            tbvPuestos.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Usuarios", getStage(), respuesta.getMensaje());
        }
    }

    private void nuevoPuesto() {
        unbindPuesto();
        tarPuestoDto = new TarPuestoDto();
        bindPuesto(true);
        nuevoCompetencia();
        txfPuesto.clear();
        txfPuesto.requestFocus();
    }

    private void bindPuesto(Boolean nuevo) {
        txfPuesto.textProperty().bindBidirectional(tarPuestoDto.pueNombre);
        chkActiva.selectedProperty().bindBidirectional(tarPuestoDto.pueEstado);
    }

    private void unbindPuesto() {
        txfPuesto.textProperty().unbindBidirectional(tarPuestoDto.pueNombre);
        chkActiva.selectedProperty().unbindBidirectional(tarPuestoDto.pueEstado);
    }

    private void cargarCompetencias(String nombre) {
        TarCompetenciaService service = new TarCompetenciaService();
        Respuesta respuesta = service.getCompetencias(nombre);

        if (respuesta.getEstado()) {
            unbindCompetencia();
            tarCompetenciaDto = (TarCompetenciaDto) respuesta.getResultado("TarCompetencia");
            bindCompetencia(false);
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Competencia", getStage(), respuesta.getMensaje());
        }
    }

    private void cargarCompetencias() {
        tbvCompetencias.getItems().clear();
        if (tarPuestoDto.getTarCompetenciaList() != null) {
            tbvCompetencias.setItems((ObservableList<TarCompetenciaDto>) tarPuestoDto.getTarCompetenciaList());
        }
        tbvCompetencias.refresh();
    }

    private void nuevoCompetencia() {
        unbindCompetencia();
        tarCompetenciaDto = new TarCompetenciaDto();
        bindCompetencia(true);
        txfBuscarCompetencia.clear();
    }

    private void bindCompetencia(Boolean nuevo) {
        txfBuscarNombre.textProperty().bindBidirectional(this.tarCompetenciaDto.comNombre);
    }

    private void unbindCompetencia() {
        txfBuscarNombre.textProperty().unbindBidirectional(this.tarCompetenciaDto.comNombre);
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
    }

}
