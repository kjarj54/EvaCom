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

    private ObservableList<TarPuestoDto> puestos = FXCollections.observableArrayList();
    private ObservableList<TarCompetenciaDto> ceompetencias = FXCollections.observableArrayList();
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
        tbcEstado.setCellValueFactory(cd -> cd.getValue().pueEstado);

        tbvPuestos.getColumns().add(tbcId);
        tbvPuestos.getColumns().add(tbcNombre);
        tbvPuestos.getColumns().add(tbcEstado);
        tbvPuestos.refresh();

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
        tbvCompetencias.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
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
    }

    @FXML
    private void onActionBtnLimpiarPuesto(ActionEvent event) {
    }

    @FXML
    private void onActionBtnAgregarPuesto(ActionEvent event) {
    }

    @FXML
    private void onActionBtnAgregarCompetencia(ActionEvent event) {
    }

    @FXML
    private void onActionBtnFiltrar(ActionEvent event) {
        cargarPuestos(txfBuscarNombre.getText());
    }

    private void cargarPuestos(String nombre) {
        TarPuestoService service = new TarPuestoService();
        Respuesta respuesta = service.getPuestos(nombre);

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
        cargarCompetencias();
        txfPuesto.clear();
        txfPuesto.requestFocus();
    }

    private void bindPuesto(Boolean nuevo) {
        txfPuesto.textProperty().bindBidirectional(tarPuestoDto.pueNombre);
//        chkActiva.selectedProperty().bindBidirectional(tarPuestoDto.pueEstado);
    }

    private void unbindPuesto() {
        txfPuesto.textProperty().unbindBidirectional(tarPuestoDto.pueNombre);
//        chkActiva.selectedProperty().unbindBidirectional(tarPuestoDto.estado);
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
        txfBuscarNombre.clear();
        txfBuscarNombre.requestFocus();
    }

    private void bindCompetencia(Boolean nuevo) {
        txfBuscarNombre.textProperty().bindBidirectional(this.tarCompetenciaDto.comNombre);
    }

    private void unbindCompetencia() {
        txfBuscarNombre.textProperty().unbindBidirectional(this.tarCompetenciaDto.comNombre);
    }
}
