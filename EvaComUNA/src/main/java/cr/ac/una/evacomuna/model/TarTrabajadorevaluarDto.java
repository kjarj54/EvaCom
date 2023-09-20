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
public class TarTrabajadorevaluarDto {
    public SimpleStringProperty traId;
    public SimpleStringProperty traResultado;
    private Long traVersion;
    private Boolean modificado;
    List<TarEvaluadorDto> tarEvaluadorList;
    List<TarEvaluadorDto> tarEvaluadorListEliminados;
    public TarProcesoevaluacionDto proId;
    public TarUsuarioDto usuId;
    
    
    public TarTrabajadorevaluarDto() {
        this.traId = new SimpleStringProperty();
        this.traResultado = new SimpleStringProperty();
        this.modificado = false;
        this.proId = new TarProcesoevaluacionDto();
        this.usuId = new TarUsuarioDto();
        tarEvaluadorList = FXCollections.observableArrayList();
        tarEvaluadorListEliminados = new ArrayList<>();
        
    }
    
    public TarTrabajadorevaluarDto(cr.ac.una.evacomunaws.controller.TarTrabajadorevaluarDto tarTrabajadorevaluarDto) {
        this();
        this.traId.set(tarTrabajadorevaluarDto.getTraId().toString());
        this.traResultado.set(tarTrabajadorevaluarDto.getTraResultado());
    }
    
    public cr.ac.una.evacomunaws.controller.TarTrabajadorevaluarDto consultas(){
        cr.ac.una.evacomunaws.controller.TarTrabajadorevaluarDto tarTrabajadorevaluarDtoSoap = new cr.ac.una.evacomunaws.controller.TarTrabajadorevaluarDto();
        tarTrabajadorevaluarDtoSoap.setTraId(this.getTraId());
        tarTrabajadorevaluarDtoSoap.setTraResultado(this.getTraResultado());
        return tarTrabajadorevaluarDtoSoap;
    }

    public Long getTraId() {
        if (this.traId.get() != null && !this.traId.get().isEmpty()) {
            return Long.valueOf(this.traId.get());
        } else {
            return null;
        }
    }

    public void setTraId(Long traId) {
        this.traId.set(traId.toString());
    }

    public String getTraResultado() {
        return traResultado.get();
    }

    public void setTraResultado(String traResultado) {
        this.traResultado.set(traResultado);
    }

    public Long getTraVersion() {
        return traVersion;
    }

    public void setTraVersion(Long traVersion) {
        this.traVersion = traVersion;
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

    public TarProcesoevaluacionDto getProId() {
        return proId;
    }

    public void setProId(TarProcesoevaluacionDto proId) {
        this.proId = proId;
    }

    public TarUsuarioDto getUsuId() {
        return usuId;
    }

    public void setUsuId(TarUsuarioDto usuId) {
        this.usuId = usuId;
    }
    
    
}
