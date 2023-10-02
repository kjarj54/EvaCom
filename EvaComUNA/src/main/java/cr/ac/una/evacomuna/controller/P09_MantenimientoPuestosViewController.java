package cr.ac.una.evacomuna.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.evacomuna.model.TarCompetenciaDto;
import cr.ac.una.evacomuna.model.TarPuestoDto;
import cr.ac.una.evacomuna.service.TarCompetenciaService;
import cr.ac.una.evacomuna.service.TarPuestoService;
import cr.ac.una.evacomuna.util.FlowController;
import cr.ac.una.evacomuna.util.Formato;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P09_MantenimientoPuestosViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txfPuesto;
    @FXML
    private JFXCheckBox chkActiva;
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

        cargarTablaPuestos();
        cargarTablaCompetenciasBusqueda();
        disableNodes(true);

        tbvCompetencias.getColumns().clear();
        tbvCompetencias.getItems().clear();

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

//        TableColumn<TarCompetenciaDto, Boolean> tbcEliminar = new TableColumn<>("Eliminar");
//        tbcEliminar.setPrefWidth(150);
//        tbcEliminar.setCellValueFactory(cd -> new SimpleObjectProperty(cd.getValue() != null));
//         tbcEliminar.setCellFactory((TableColumn<TarUsuarioDto, Boolean> p) -> new ButtonCell());
        tbvCompetencias.getColumns().add(tbcComId);
        tbvCompetencias.getColumns().add(tbcComNombre);
        tbvCompetencias.getColumns().add(tbcComEstado);
//        tbvCompetencias.getColumns().add(tbcEliminar);
        tbvCompetencias.refresh();

        this.tarPuestoDto = new TarPuestoDto();
        this.tarCompetenciaDto = new TarCompetenciaDto();
        nuevoPuesto();

        cargarPuestosAutomatico();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnAgregarPuesto(ActionEvent event) {
        try {
            if (btnAgregarPuesto.getText().isBlank() || btnAgregarPuesto.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar puesto", getStage(), "Campo de texto de nombre de puesto vacia");
            } else {
                TarPuestoService service = new TarPuestoService();
                Respuesta respuesta = service.guardarTarPuesto(tarPuestoDto.consultas());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Puesto", getStage(), respuesta.getMensaje());
                } else {
                    tbvPuestos.getItems().clear();
                    tbvPuestos.refresh();
                    unbindPuesto();
                    tarPuestoDto = (TarPuestoDto) respuesta.getResultado("Puesto");
                    bindPuesto();
                    cargarPuestosAutomatico();
                    nuevoCompetencia();
                    cargarCompetencias();
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Puesto", getStage(), "Puesto guardado/actualizado correctamente.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P09_MantenimientoPuestosViewController.class.getName()).log(Level.SEVERE, "Error guardando el Puesto.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Puesto", getStage(), "Ocurrio un error guardando el Puesto.");
        }
    }

    @FXML
    private void onActionBtnLimpiarPuesto(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar Puesto", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            nuevoPuesto();
            disableNodes(true);
            cargarPuestosAutomatico();
        }
    }

    @FXML
    private void onActionBtnAgregarCompetencia(ActionEvent event) {
        if (tarCompetenciaDto.getComId() == null || tarPuestoDto.getPueId() == null) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Agregar Competencia", getStage(), "Es necesario cargar una Puesto para agregarla a la lista.");
        } else if (tbvCompetencias.getItems() == null || !tbvCompetencias.getItems().stream().anyMatch(a -> a.getComNombre().equals(tarCompetenciaDto.getComNombre()))) {
            tarCompetenciaDto.setModificado(true);
            tarPuestoDto.getTarCompetenciaList().add(tarCompetenciaDto);
            tbvCompetencias.getItems().add(tarCompetenciaDto);
            tbvCompetencias.refresh();
        }
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    @FXML
    private void onActionBtnFiltrar(ActionEvent event) {
        TarPuestoService service = new TarPuestoService();
        Respuesta respuesta = service.getPuestos(txfBuscarNombre.getText(), chkBuscarActivas.isSelected() ? "S" : "N");

        if (respuesta.getEstado()) {
            tbvPuestos.getItems().clear();
            tbvCompetencias.getItems().clear();
            puestos.clear();
            puestos.addAll((List<TarPuestoDto>) respuesta.getResultado("TarPuestos"));
            tbvPuestos.setItems(puestos);
            tbvPuestos.refresh();
            tbvCompetencias.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Puestos", getStage(), respuesta.getMensaje());
        }
    }

    private void cargarTablaPuestos() {
        tbvPuestos.getColumns().clear();
        tbvPuestos.getItems().clear();

        TableColumn<TarPuestoDto, String> tbcId = new TableColumn<>("Id");
        tbcId.setPrefWidth(40);
        tbcId.setCellValueFactory(cd -> cd.getValue().pueId);

        TableColumn<TarPuestoDto, String> tbcNombre = new TableColumn<>("Nombre");
        tbcNombre.setPrefWidth(250);
        tbcNombre.setCellValueFactory(cd -> cd.getValue().pueNombre);

        TableColumn<TarPuestoDto, String> tbcEstado = new TableColumn<>("Estado");
        tbcEstado.setPrefWidth(70);
        tbcEstado.setCellValueFactory(cd -> {
            if ("A".equals(cd.getValue().getPueEstado())) {
                return new SimpleStringProperty("Activo");
            } else {
                return new SimpleStringProperty("Inactivo");
            }
        });

        TableColumn<TarPuestoDto, Boolean> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellValueFactory((TableColumn.CellDataFeatures<TarPuestoDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
        tbcEliminar.setCellFactory((TableColumn<TarPuestoDto, Boolean> p) -> new ButtonCell());

        tbvPuestos.getColumns().add(tbcId);
        tbvPuestos.getColumns().add(tbcNombre);
        tbvPuestos.getColumns().add(tbcEstado);
        tbvPuestos.getColumns().add(tbcEliminar);
        tbvPuestos.refresh();

        tbvPuestos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                unbindPuesto();
                tarPuestoDto = newValue;
                bindPuesto();
                disableNodes(false);

                cargarCompetencias();
                nuevoCompetencia();
            } else {
                nuevoPuesto();
            }
        });
    }

    private void cargarTablaCompetenciasBusqueda() {
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

        tbvCompetenciasBusqueda.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                tarCompetenciaDto = newValue;
            } else {
                nuevoCompetencia();
            }
        });
    }

    private void cargarPuestosAutomatico() {
        TarPuestoService service = new TarPuestoService();
        Respuesta respuesta = service.getListaPuestos();

        if (respuesta.getEstado()) {
            tbvPuestos.getItems().clear();
            tbvCompetencias.getItems().clear();
            puestos.clear();
            puestos.addAll((List<TarPuestoDto>) respuesta.getResultado("TarPuestos"));
            tbvPuestos.setItems(puestos);
            tbvPuestos.refresh();
            tbvCompetencias.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Puestos", getStage(), respuesta.getMensaje());
        }
    }

    private void eliminarPuesto() {
        if (new Mensaje().showConfirmation("Eliminar Puesto", getStage(), "¿Esta seguro que desea eliminar el registro?")) {
            try {
                TarPuestoService service = new TarPuestoService();
                Respuesta respuesta = service.eliminarPuesto(tarPuestoDto.getPueId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Puesto", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Puesto", getStage(), "Puesto eliminado correctamente.");
                    nuevoPuesto();
                }
            } catch (Exception ex) {
                Logger.getLogger(P09_MantenimientoPuestosViewController.class.getName()).log(Level.SEVERE, "Error eliminando el Puesto.", ex);
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Puesto", getStage(), "Ocurrio un error eliminando el Puesto.");
            }
        }
        cargarPuestosAutomatico();
    }

    private void nuevoPuesto() {
        unbindPuesto();
        tarPuestoDto = new TarPuestoDto();
        bindPuesto();
        nuevoCompetencia();
        txfPuesto.clear();
        txfBuscarNombre.clear();
        txfPuesto.requestFocus();
    }

    private void bindPuesto() {
        txfPuesto.textProperty().bindBidirectional(tarPuestoDto.pueNombre);
        chkActiva.selectedProperty().bindBidirectional(tarPuestoDto.pueEstado);
    }

    private void unbindPuesto() {
        txfPuesto.textProperty().unbindBidirectional(tarPuestoDto.pueNombre);
        chkActiva.selectedProperty().unbindBidirectional(tarPuestoDto.pueEstado);
    }

    private void disableNodes(Boolean activo) {
        txfBuscarCompetencia.setDisable(activo);
        btnAgregarCompetencia.setDisable(activo);
        tbvCompetenciasBusqueda.setDisable(activo);
        tbvCompetencias.setDisable(activo);
    }

    @FXML
    private void onkeyPressedCompetencia(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            cargarCompetencias(txfBuscarCompetencia.getText());
        }
    }

    private void cargarCompetencias(String nombre) {
        TarCompetenciaService service = new TarCompetenciaService();
        Respuesta respuesta = service.getCompetencias(nombre, "");

        if (respuesta.getEstado()) {
            tbvCompetenciasBusqueda.getItems().clear();
            competencias.clear();
            competencias.addAll((List<TarCompetenciaDto>) respuesta.getResultado("TarCompetencia"));
            tbvCompetenciasBusqueda.setItems(competencias);
            tbvCompetenciasBusqueda.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Competencia", getStage(), respuesta.getMensaje());
        }
    }

    private void cargarCompetencias() {
        ObservableList<TarCompetenciaDto> compe = FXCollections.observableArrayList();
        compe.addAll(tarPuestoDto.getTarCompetenciaList());

        tbvCompetencias.getItems().clear();
        tbvCompetencias.setItems(compe);
        tbvCompetencias.refresh();
    }

    private void nuevoCompetencia() {
        tarCompetenciaDto = new TarCompetenciaDto();
        txfBuscarCompetencia.clear();
        tbvCompetenciasBusqueda.getItems().clear();
        tbvCompetenciasBusqueda.refresh();
    }

    private class ButtonCell extends TableCell<TarPuestoDto, Boolean> {

        final MFXButton cellButton = new MFXButton("X");

        ButtonCell() {
            cellButton.setPrefWidth(100);
            cellButton.getStyleClass().add("mfx-button-menuSalir");
//            cellButton.setDisable(true);

            cellButton.setOnAction((ActionEvent t) -> {
                int index = ButtonCell.this.getIndex();
                tarPuestoDto = (TarPuestoDto) ButtonCell.this.getTableView().getItems().get(index);
                eliminarPuesto();
                tbvCompetencias.getItems().remove(tarPuestoDto);
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
}
