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
public class TarEvaluadorDto {

    public SimpleStringProperty evaluId;
    public SimpleStringProperty evaluRetroalimentacion;
    private Long evaluVersion;
    private Boolean modificado;
    public TarTrabajadorevaluarDto traId;
    public TarUsuarioDto usuId;
    List<TarCompetenciaevaluarDto> tarCompetenciaevaluarList;
    List<TarCompetenciaevaluarDto> tarCompetenciaevaluarListEliminados;

    public TarEvaluadorDto() {
        this.evaluId = new SimpleStringProperty();
        this.evaluRetroalimentacion = new SimpleStringProperty();
        this.modificado = false;
        this.traId = new TarTrabajadorevaluarDto();
        this.usuId = new TarUsuarioDto();
        tarCompetenciaevaluarList = FXCollections.observableArrayList();
        tarCompetenciaevaluarListEliminados = new ArrayList<>();
    }
    
    public TarEvaluadorDto(cr.ac.una.evacomunaws.controller.TarEvaluadorDto tarEvaluadorDto) {
        this();
        this.evaluRetroalimentacion.set(tarEvaluadorDto.getEvaluRetroalimentacion());
    }
    
    public cr.ac.una.evacomunaws.controller.TarEvaluadorDto consultas(){
        cr.ac.una.evacomunaws.controller.TarEvaluadorDto tarEvaluadorDtoSoap = new cr.ac.una.evacomunaws.controller.TarEvaluadorDto();
        tarEvaluadorDtoSoap.setEvaluId(this.getEvaluId());
        tarEvaluadorDtoSoap.setEvaluRetroalimentacion(this.getEvaluRetroalimentacion());
        return tarEvaluadorDtoSoap;
    }

    public Long getEvaluId() {
        if (this.evaluId.get() != null && !this.evaluId.get().isEmpty()) {
            return Long.valueOf(this.evaluId.get());
        } else {
            return null;
        }
    }

    public void setEvaluId(Long evaluId) {
        this.evaluId.set(evaluId.toString());
    }

    public String getEvaluRetroalimentacion() {
        return evaluRetroalimentacion.get();
    }

    public void setEvaluRetroalimentacion(String evaluRetroalimentacion) {
        this.evaluRetroalimentacion.set(evaluRetroalimentacion);
    }

    public Long getEvaluVersion() {
        return evaluVersion;
    }

    public void setEvaluVersion(Long evaluVersion) {
        this.evaluVersion = evaluVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public TarTrabajadorevaluarDto getTraId() {
        return traId;
    }

    public void setTraId(TarTrabajadorevaluarDto traId) {
        this.traId = traId;
    }

    public TarUsuarioDto getUsuId() {
        return usuId;
    }

    public void setUsuId(TarUsuarioDto usuId) {
        this.usuId = usuId;
    }

    public List<TarCompetenciaevaluarDto> getTarCompetenciaevaluarList() {
        return tarCompetenciaevaluarList;
    }

    public void setTarCompetenciaevaluarList(List<TarCompetenciaevaluarDto> tarCompetenciaevaluarList) {
        this.tarCompetenciaevaluarList = tarCompetenciaevaluarList;
    }

    public List<TarCompetenciaevaluarDto> getTarCompetenciaevaluarListEliminados() {
        return tarCompetenciaevaluarListEliminados;
    }

    public void setTarCompetenciaevaluarListEliminados(List<TarCompetenciaevaluarDto> tarCompetenciaevaluarListEliminados) {
        this.tarCompetenciaevaluarListEliminados = tarCompetenciaevaluarListEliminados;
    }
    
}
