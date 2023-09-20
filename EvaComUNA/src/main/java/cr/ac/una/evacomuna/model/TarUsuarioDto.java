/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

/**
 *
 * @author kevin
 */
public class TarUsuarioDto {

    public SimpleStringProperty usuId;
    public SimpleStringProperty usuNombre;
    public SimpleStringProperty usuApellido;
    public SimpleStringProperty usuCedula;
    public SimpleStringProperty usuCorreo;
    public SimpleStringProperty usuTelefono;
    public SimpleStringProperty usuCelular;
    public ObjectProperty<Byte[]> usuFoto;
    public SimpleStringProperty usuUsu;
    public SimpleStringProperty usuClave;
    public SimpleStringProperty usuTempclave;
    public SimpleBooleanProperty usuActivo;
    public SimpleBooleanProperty usuAdmin;
    private Long usuVersion;
    private Boolean modificado;
    List<TarEvaluadorDto> tarEvaluadorList;
    List<TarEvaluadorDto> tarEvaluadorListEliminados;
    List<TarTrabajadorevaluarDto> tarTrabajadorevaluarList;
    List<TarTrabajadorevaluarDto> tarTrabajadorevaluarListEliminados;
    public TarPuestoDto pueId;

    public TarUsuarioDto() {
        this.usuId = new SimpleStringProperty();
        this.usuNombre = new SimpleStringProperty();
        this.usuApellido = new SimpleStringProperty();
        this.usuCedula = new SimpleStringProperty();
        this.usuCorreo = new SimpleStringProperty();
        this.usuTelefono = new SimpleStringProperty();
        this.usuCelular = new SimpleStringProperty();
        this.usuFoto = new SimpleObjectProperty();
        this.usuClave = new SimpleStringProperty();
        this.usuTempclave = new SimpleStringProperty();
        this.usuUsu = new SimpleStringProperty();
        this.usuActivo = new SimpleBooleanProperty(false);
        this.usuAdmin = new SimpleBooleanProperty(false);
        this.modificado = false;
        this.pueId = new TarPuestoDto();
        tarEvaluadorList = FXCollections.observableArrayList();
        tarEvaluadorListEliminados = new ArrayList<>();
        tarTrabajadorevaluarList = FXCollections.observableArrayList();
        tarTrabajadorevaluarListEliminados = new ArrayList<>();
    }
    
    public TarUsuarioDto(cr.ac.una.evacomunaws.controller.TarUsuarioDto tarUsuarioDto) {
        this();
        this.usuId.set(tarUsuarioDto.getUsuId().toString());
        this.usuNombre.set(tarUsuarioDto.getUsuNombre());
        this.usuApellido.set(tarUsuarioDto.getUsuApellido());
        this.usuCedula.set(tarUsuarioDto.getUsuCedula());
        this.usuCorreo.set(tarUsuarioDto.getUsuCorreo());
        this.usuTelefono.set(tarUsuarioDto.getUsuTelefono().toString());
        this.usuCelular.set(tarUsuarioDto.getUsuCelular().toString());
        List<Byte> usuFotoList = tarUsuarioDto.getUsuFoto();
        Byte[] usuFotoArray = usuFotoList.toArray(new Byte[usuFotoList.size()]);
        this.usuFoto.set(usuFotoArray);
        this.usuClave.set(tarUsuarioDto.getUsuClave());
        this.usuTempclave.set(tarUsuarioDto.getUsuTempclave());
        this.usuUsu.set(tarUsuarioDto.getUsuUsu());
        this.usuActivo.set(tarUsuarioDto.getUsuActivo().equals("A"));
        this.usuAdmin.set(tarUsuarioDto.getUsuAdmin().equals("S"));
    }
    
    public cr.ac.una.evacomunaws.controller.TarUsuarioDto consultas(){
        cr.ac.una.evacomunaws.controller.TarUsuarioDto tarUsuarioDtoSoap = new cr.ac.una.evacomunaws.controller.TarUsuarioDto();
        tarUsuarioDtoSoap.setUsuActivo(this.getUsuActivo());
        tarUsuarioDtoSoap.setUsuAdmin(this.getUsuAdmin());
        tarUsuarioDtoSoap.setUsuApellido(this.getUsuApellido());
        tarUsuarioDtoSoap.setUsuCedula(this.getUsuCedula());
        tarUsuarioDtoSoap.setUsuCelular(this.getUsuCelular());
        tarUsuarioDtoSoap.setUsuClave(this.getUsuClave());
        tarUsuarioDtoSoap.setUsuCorreo(this.getUsuCorreo());
        tarUsuarioDtoSoap.setUsuId(this.getUsuId());
        tarUsuarioDtoSoap.setUsuNombre(this.getUsuNombre());
        tarUsuarioDtoSoap.setUsuTelefono(this.getUsuTelefono());
        tarUsuarioDtoSoap.setUsuUsu(this.getUsuUsu());
        Byte[] usuFotoArray = this.getUsuFoto();
        List<Byte> usuFotoList = new ArrayList<>(Arrays.asList(usuFotoArray));
        tarUsuarioDtoSoap.getUsuFoto().addAll(usuFotoList);
        return tarUsuarioDtoSoap;
    }

    public Long getUsuId() {
        if (this.usuId.get() != null && !this.usuId.get().isEmpty()) {
            return Long.valueOf(this.usuId.get());
        } else {
            return null;
        }
    }

    public void setUsuId(Long usuId) {
        this.usuId.set(usuId.toString());
    }

    public String getUsuNombre() {
        return usuNombre.get();
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre.set(usuNombre);
    }

    public String getUsuApellido() {
        return usuApellido.get();
    }

    public void setUsuApellido(String usuApellido) {
        this.usuApellido.set(usuApellido);
    }

    public String getUsuCedula() {
        return usuCedula.get();
    }

    public void setUsuCedula(String usuCedula) {
        this.usuCedula.set(usuCedula);
    }

    public String getUsuCorreo() {
        return usuCorreo.get();
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo.set(usuCorreo);
    }

    public Long getUsuTelefono() {
        if (this.usuTelefono.get() != null && !this.usuTelefono.get().isEmpty()) {
            return Long.valueOf(this.usuTelefono.get());
        } else {
            return null;
        }
    }

    public void setUsuTelefono(Long usuTelefono) {
        this.usuTelefono.set(usuTelefono.toString());

    }

    public Long getUsuCelular() {
        if (this.usuCelular.get() != null && !this.usuCelular.get().isEmpty()) {
            return Long.valueOf(this.usuCelular.get());
        } else {
            return null;
        }
    }

    public void setUsuCelular(Long usuCelular) {
        this.usuCelular.set(usuCelular.toString());
    }

    public Byte[] getUsuFoto() {
        return usuFoto.get();
    }

    public void setUsuFoto(Byte[] usuFoto) {
        this.usuFoto.set(usuFoto);
    }

    public String getUsuClave() {
        return usuClave.get();
    }

    public void setUsuClave(String usuClave) {
        this.usuClave.set(usuClave);
    }

    public String getUsuActivo() {
        return usuActivo.get() ? "A" : "I";
    }

    public void setUsuActivo(String usuActivo) {
        this.usuActivo.set(usuActivo.equals("I"));
    }

    public String getUsuAdmin() {
        return usuAdmin.get() ? "S" : "N";
    }

    public void setUsuAdmin(String usuAdmin) {
        this.usuAdmin.set(usuAdmin.equals("S"));
    }

    public Long getUsuVersion() {
        return usuVersion;
    }

    public void setUsuVersion(Long usuVersion) {
        this.usuVersion = usuVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public List<TarEvaluadorDto> getTarEvaluadorList() {
        return tarEvaluadorList;
    }

    public void setTarEvaluadorList(List<TarEvaluadorDto> tarEvaluadorList) {
        this.tarEvaluadorList = tarEvaluadorList;
    }

    public List<TarEvaluadorDto> getTarEvaluadorListEliminados() {
        return tarEvaluadorListEliminados;
    }

    public void setTarEvaluadorListEliminados(List<TarEvaluadorDto> tarEvaluadorListEliminados) {
        this.tarEvaluadorListEliminados = tarEvaluadorListEliminados;
    }

    public List<TarTrabajadorevaluarDto> getTarTrabajadorevaluarList() {
        return tarTrabajadorevaluarList;
    }

    public void setTarTrabajadorevaluarList(List<TarTrabajadorevaluarDto> tarTrabajadorevaluarList) {
        this.tarTrabajadorevaluarList = tarTrabajadorevaluarList;
    }

    public List<TarTrabajadorevaluarDto> getTarTrabajadorevaluarListEliminados() {
        return tarTrabajadorevaluarListEliminados;
    }

    public void setTarTrabajadorevaluarListEliminados(List<TarTrabajadorevaluarDto> tarTrabajadorevaluarListEliminados) {
        this.tarTrabajadorevaluarListEliminados = tarTrabajadorevaluarListEliminados;
    }

    public TarPuestoDto getPueId() {
        return pueId;
    }

    public void setPueId(TarPuestoDto pueId) {
        this.pueId = pueId;
    }
    
    public String getUsuUsu() {
        return usuUsu.get();
    }

    public void setUsuUsu(String usuUsu) {
        this.usuUsu.set(usuUsu);
    }
    public String getUsuTempclave() {
        return usuTempclave.get();
    }

    public void setUsuTempclave(String usuTempclave) {
        this.usuTempclave.set(usuTempclave);
    }
}
