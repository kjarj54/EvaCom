/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

/**
 *
 * @author kevin
 */
public class TarPuestoDto {

    public SimpleStringProperty pueId;
    public SimpleStringProperty pueNombre;
    public SimpleStringProperty pueEstado;
    private Long pueVersion;
    private Boolean modificado;
    List<TarCompetenciaDto> tarCompetenciaList;
    List<TarCompetenciaDto> tarCompetenciaListEliminados;
    List<TarUsuarioDto> tarUsuarioList;
    List<TarUsuarioDto> tarUsuarioListEliminados;

    public TarPuestoDto() {
        this.pueId = new SimpleStringProperty();
        this.pueNombre = new SimpleStringProperty();
        this.pueEstado = new SimpleStringProperty();
        this.modificado = false;
        tarCompetenciaList = FXCollections.observableArrayList();
        tarCompetenciaListEliminados = new ArrayList<>();
        tarUsuarioList = FXCollections.observableArrayList();
        tarUsuarioListEliminados = new ArrayList<>();
    }
    
    public TarPuestoDto(cr.ac.una.evacomunaws.controller.TarPuestoDto tarPuestoDto) {
        this();
    }
    
    public cr.ac.una.evacomunaws.controller.TarPuestoDto consultas(){
        cr.ac.una.evacomunaws.controller.TarPuestoDto tarPuestoDtoSoap = new cr.ac.una.evacomunaws.controller.TarPuestoDto();
        return tarPuestoDtoSoap;
    }

    public Long getPueId() {
        if (this.pueId.get() != null && !this.pueId.get().isEmpty()) {
            return Long.valueOf(this.pueId.get());
        } else {
            return null;
        }
    }

    public void setPueId(Long pueId) {
        this.pueId.set(pueId.toString());
    }

    public String getPueNombre() {
        return pueNombre.get();
    }

    public void setPueNombre(String pueNombre) {
        this.pueNombre.set(pueNombre);
    }

    public String getPueEstado() {
        return pueEstado.get();
    }

    public void setPueEstado(String pueEstado) {
        this.pueEstado.set(pueEstado);
    }

    public Long getPueVersion() {
        return pueVersion;
    }

    public void setPueVersion(Long pueVersion) {
        this.pueVersion = pueVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public List<TarCompetenciaDto> getTarCompetenciaList() {
        return tarCompetenciaList;
    }

    public void setTarCompetenciaList(List<TarCompetenciaDto> tarCompetenciaList) {
        this.tarCompetenciaList = tarCompetenciaList;
    }

    public List<TarCompetenciaDto> getTarCompetenciaListEliminados() {
        return tarCompetenciaListEliminados;
    }

    public void setTarCompetenciaListEliminados(List<TarCompetenciaDto> tarCompetenciaListEliminados) {
        this.tarCompetenciaListEliminados = tarCompetenciaListEliminados;
    }

    public List<TarUsuarioDto> getTarUsuarioList() {
        return tarUsuarioList;
    }

    public void setTarUsuarioList(List<TarUsuarioDto> tarUsuarioList) {
        this.tarUsuarioList = tarUsuarioList;
    }

    public List<TarUsuarioDto> getTarUsuarioListEliminados() {
        return tarUsuarioListEliminados;
    }

    public void setTarUsuarioListEliminados(List<TarUsuarioDto> tarUsuarioListEliminados) {
        this.tarUsuarioListEliminados = tarUsuarioListEliminados;
    }
    
    
}
