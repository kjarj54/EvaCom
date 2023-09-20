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
    public TarProcesoevaluacionDto procesoDto;
    public TarUsuarioDto usuarioDto;
    
    
    public TarTrabajadorevaluarDto() {
        this.traId = new SimpleStringProperty();
        this.traResultado = new SimpleStringProperty();
        this.modificado = false;
        this.procesoDto = new TarProcesoevaluacionDto();
        this.usuarioDto = new TarUsuarioDto();
        tarEvaluadorList = FXCollections.observableArrayList();
        tarEvaluadorListEliminados = new ArrayList<>();
        
    }
    
    public TarTrabajadorevaluarDto(cr.ac.una.evacomunaws.controller.TarTrabajadorevaluarDto tarTrabajadorevaluarDto) {
        this();
        this.traId.set(tarTrabajadorevaluarDto.getTraId().toString());
        this.traResultado.set(tarTrabajadorevaluarDto.getTraResultado());
        this.usuarioDto = new TarUsuarioDto(tarTrabajadorevaluarDto.getUsuarioDto());
        this.procesoDto = new TarProcesoevaluacionDto(tarTrabajadorevaluarDto.getProcesoDto());
        
        List<cr.ac.una.evacomunaws.controller.TarEvaluadorDto> evaluadorDtos = tarTrabajadorevaluarDto.getTarEvaluadorList();
        for (cr.ac.una.evacomunaws.controller.TarEvaluadorDto item : evaluadorDtos) {
            TarEvaluadorDto convertedItem = new TarEvaluadorDto(item);
            this.tarEvaluadorList.add(convertedItem);
        }
        
    }
    
    public cr.ac.una.evacomunaws.controller.TarTrabajadorevaluarDto consultas(){
        cr.ac.una.evacomunaws.controller.TarTrabajadorevaluarDto tarTrabajadorevaluarDtoSoap = new cr.ac.una.evacomunaws.controller.TarTrabajadorevaluarDto();
        tarTrabajadorevaluarDtoSoap.setTraId(this.getTraId());
        tarTrabajadorevaluarDtoSoap.setTraResultado(this.getTraResultado());
        tarTrabajadorevaluarDtoSoap.setProcesoDto(this.procesoDto.consultas());
        tarTrabajadorevaluarDtoSoap.setUsuarioDto(this.usuarioDto.consultas());
        
        List<TarEvaluadorDto> tarUsuarioDtos = this.tarEvaluadorListEliminados;
        for (TarEvaluadorDto item : tarUsuarioDtos) {         
            tarTrabajadorevaluarDtoSoap.getTarEvaluadorListEliminados().add(item.consultas());
        }
        
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

    public TarProcesoevaluacionDto getProcesoDto() {
        return procesoDto;
    }

    public void setProcesoDto(TarProcesoevaluacionDto procesoDto) {
        this.procesoDto = procesoDto;
    }

    public TarUsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(TarUsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }
    
    
}
