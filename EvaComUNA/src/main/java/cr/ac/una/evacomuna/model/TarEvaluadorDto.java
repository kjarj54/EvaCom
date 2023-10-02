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
    public SimpleStringProperty evaluCalificacion;
    private Long evaluVersion;
    private Boolean modificado;
    public TarTrabajadorevaluarDto trabajadorevaluarDto;
    public TarUsuarioDto usuarioDto;
    List<TarCompetenciaevaluarDto> tarCompetenciaevaluarList;
    List<TarCompetenciaevaluarDto> tarCompetenciaevaluarListEliminados;

    public TarEvaluadorDto() {
        this.evaluId = new SimpleStringProperty();
        this.evaluRetroalimentacion = new SimpleStringProperty();
        this.modificado = false;
        this.trabajadorevaluarDto = new TarTrabajadorevaluarDto();
        this.usuarioDto = new TarUsuarioDto();
        tarCompetenciaevaluarList = FXCollections.observableArrayList();
        tarCompetenciaevaluarListEliminados = new ArrayList<>();
    }

    public TarEvaluadorDto(cr.ac.una.evacomunaws.controller.TarEvaluadorDto tarEvaluadorDto) {
        this();
        this.evaluId.set(tarEvaluadorDto.getEvaluId().toString());
        this.evaluRetroalimentacion.set(tarEvaluadorDto.getEvaluRetroalimentacion());
        if (tarEvaluadorDto.getTrabajadorevaluarDto() != null) {
            this.trabajadorevaluarDto = new TarTrabajadorevaluarDto(tarEvaluadorDto.getTrabajadorevaluarDto());
        }
        if (tarEvaluadorDto.getUsuarioDto() != null) {
            this.usuarioDto = new TarUsuarioDto(tarEvaluadorDto.getUsuarioDto());
        }
        if (!tarEvaluadorDto.getTarCompetenciaevaluarList().isEmpty()) {
            List<cr.ac.una.evacomunaws.controller.TarCompetenciaevaluarDto> tarCompetenciaevaluarList = tarEvaluadorDto.getTarCompetenciaevaluarList();
            for (cr.ac.una.evacomunaws.controller.TarCompetenciaevaluarDto item : tarCompetenciaevaluarList) {
                TarCompetenciaevaluarDto convertedItem = new TarCompetenciaevaluarDto(item);
                this.tarCompetenciaevaluarList.add(convertedItem);
            }
        }
//        this.evaluCalificacion.set(tarEvaluadorDto.getEvaluCalificacion());
        this.modificado = tarEvaluadorDto.isModificado();
        this.evaluVersion = tarEvaluadorDto.getEvaluVersion();
    }

    public cr.ac.una.evacomunaws.controller.TarEvaluadorDto consultas() {
        cr.ac.una.evacomunaws.controller.TarEvaluadorDto tarEvaluadorDtoSoap = new cr.ac.una.evacomunaws.controller.TarEvaluadorDto();
        tarEvaluadorDtoSoap.setEvaluId(this.getEvaluId());
        tarEvaluadorDtoSoap.setEvaluRetroalimentacion(this.getEvaluRetroalimentacion());
        if (!this.tarCompetenciaevaluarList.isEmpty()) {
            List<TarCompetenciaevaluarDto> tarCompetenciaevaluarList = this.tarCompetenciaevaluarList;
            for (TarCompetenciaevaluarDto item : tarCompetenciaevaluarList) {
                tarEvaluadorDtoSoap.getTarCompetenciaevaluarList().add(item.consultas());
            }
        }
        if (!this.tarCompetenciaevaluarListEliminados.isEmpty()) {
            List<TarCompetenciaevaluarDto> tarCompetenciaevaluarElimimados = this.tarCompetenciaevaluarListEliminados;
            for (TarCompetenciaevaluarDto item : tarCompetenciaevaluarElimimados) {
                tarEvaluadorDtoSoap.getTarCompetenciaevaluarListEliminados().add(item.consultas());
            }
        }
        tarEvaluadorDtoSoap.setEvaluVersion(this.getEvaluVersion());
        tarEvaluadorDtoSoap.setModificado(this.getModificado());
        //tarEvaluadorDtoSoap.setEvaluCalificacion(this.getEvaluCalificacion());
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

    public TarTrabajadorevaluarDto getTrabajadorevaluarDto() {
        return trabajadorevaluarDto;
    }

    public void setTrabajadorevaluarDto(TarTrabajadorevaluarDto trabajadorevaluarDto) {
        this.trabajadorevaluarDto = trabajadorevaluarDto;
    }

    public TarUsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(TarUsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
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

    public String getEvaluCalificacion() {
        return evaluCalificacion.get();
    }

    public void setEvaluCalificacion(String evaluCalificacion) {
        this.evaluCalificacion.set(evaluCalificacion);
    }
    
    
    
}
