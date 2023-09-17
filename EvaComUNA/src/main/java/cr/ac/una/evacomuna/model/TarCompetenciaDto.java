/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

/**
 *
 * @author kevin
 */
public class TarCompetenciaDto {

    public SimpleStringProperty comId;
    public SimpleStringProperty comNombre;
    public SimpleBooleanProperty comEstado;
    private Long comVersion;
    private Boolean modificado;
    List<TarPuestoDto> tarPuestoList;
    List<TarPuestoDto> tarPuestoEliminados;
    List<TarCaracteristicaDto> tarCaracteristicaList;
    List<TarCaracteristicaDto> tarCaracteristicaEliminados;
    List<TarCompetenciaevaluarDto> tarCompetenciaevaluarList;
    List<TarCompetenciaevaluarDto> tarCompetenciaevaluarElimimados;

    public TarCompetenciaDto() {
        this.comId = new SimpleStringProperty();
        this.comNombre = new SimpleStringProperty();
        this.comEstado = new SimpleBooleanProperty(false);
        this.modificado = false;
        tarPuestoList = FXCollections.observableArrayList();
        tarPuestoEliminados = new ArrayList<>();
        tarCaracteristicaList = FXCollections.observableArrayList();
        tarCaracteristicaEliminados= new ArrayList<>();
        tarCompetenciaevaluarList=FXCollections.observableArrayList();
        tarCompetenciaevaluarElimimados= new ArrayList<>();
    }

    public Long getComId() {
        if (this.comId.get() != null && !this.comId.get().isEmpty()) {
            return Long.valueOf(this.comId.get());
        } else {
            return null;
        }
    }

    public void setComId(Long comId) {
        this.comId.set(comId.toString());
    }

    public String getComNombre() {
        return comNombre.get();
    }

    public void setComNombre(String comNombre) {
        this.comNombre.set(comNombre);
    }

    public String getComEstado() {
        return comEstado.get() ? "A" : "I";
    }

    public void setComEstado(String comEstado) {
        this.comEstado.set(comEstado.equals("A"));
    }

    public Long getComVersion() {
        return comVersion;
    }

    public void setComVersion(Long comVersion) {
        this.comVersion = comVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public List<TarPuestoDto> getTarPuestoList() {
        return tarPuestoList;
    }

    public void setTarPuestoList(List<TarPuestoDto> tarPuestoList) {
        this.tarPuestoList = tarPuestoList;
    }

    public List<TarPuestoDto> getTarPuestoEliminados() {
        return tarPuestoEliminados;
    }

    public void setTarPuestoEliminados(List<TarPuestoDto> tarPuestoEliminados) {
        this.tarPuestoEliminados = tarPuestoEliminados;
    }

    public List<TarCaracteristicaDto> getTarCaracteristicaList() {
        return tarCaracteristicaList;
    }

    public void setTarCaracteristicaList(List<TarCaracteristicaDto> tarCaracteristicaList) {
        this.tarCaracteristicaList = tarCaracteristicaList;
    }

    public List<TarCaracteristicaDto> getTarCaracteristicaEliminados() {
        return tarCaracteristicaEliminados;
    }

    public void setTarCaracteristicaEliminados(List<TarCaracteristicaDto> tarCaracteristicaEliminados) {
        this.tarCaracteristicaEliminados = tarCaracteristicaEliminados;
    }

    public List<TarCompetenciaevaluarDto> getTarCompetenciaevaluarList() {
        return tarCompetenciaevaluarList;
    }

    public void setTarCompetenciaevaluarList(List<TarCompetenciaevaluarDto> tarCompetenciaevaluarList) {
        this.tarCompetenciaevaluarList = tarCompetenciaevaluarList;
    }

    public List<TarCompetenciaevaluarDto> getTarCompetenciaevaluarElimimados() {
        return tarCompetenciaevaluarElimimados;
    }

    public void setTarCompetenciaevaluarElimimados(List<TarCompetenciaevaluarDto> tarCompetenciaevaluarElimimados) {
        this.tarCompetenciaevaluarElimimados = tarCompetenciaevaluarElimimados;
    }
    
    
    
}
