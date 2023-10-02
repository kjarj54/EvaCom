/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

/**
 *
 * @author kevin
 */
public class TarProcesoevaluacionDto {

    public SimpleStringProperty proId;
    public ObjectProperty<LocalDate> proFini;
    public ObjectProperty<LocalDate> proFfin;
    public SimpleStringProperty proTitulo;
    public ObjectProperty<String> proEstado;
    public Long proVersion;
    public Boolean modificado;
    List<TarTrabajadorevaluarDto> tarTrabajadorevaluarList;
    List<TarTrabajadorevaluarDto> tarTrabajadorevaluarListEliminados;

    public TarProcesoevaluacionDto() {
        this.proId = new SimpleStringProperty();
        this.proFini = new SimpleObjectProperty(LocalDate.now());
        this.proFfin = new SimpleObjectProperty();
        this.proTitulo = new SimpleStringProperty();
        this.proEstado = new SimpleObjectProperty("C");//definir no recuerdo que tenia de default
        tarTrabajadorevaluarList = FXCollections.observableArrayList();
        tarTrabajadorevaluarListEliminados = new ArrayList<>();
    }

    public TarProcesoevaluacionDto(cr.ac.una.evacomunaws.controller.TarProcesoevaluacionDto tarProcesoevaluacionDto) {
        this();
        this.proEstado.set(tarProcesoevaluacionDto.getProEstado());
//        this.proFfin.set(java.time.LocalDate.parse(tarProcesoevaluacionDto.getProFfin().toString()));
//        this.proFini.set(java.time.LocalDate.parse(tarProcesoevaluacionDto.getProFini().toString()));
        this.proId.set(tarProcesoevaluacionDto.getProId().toString());
        this.proTitulo.set(tarProcesoevaluacionDto.getProTitulo());

        if (!tarProcesoevaluacionDto.getTarTrabajadorevaluarList().isEmpty()) {
            List<cr.ac.una.evacomunaws.controller.TarTrabajadorevaluarDto> tarCompetenciaevaluarList = tarProcesoevaluacionDto.getTarTrabajadorevaluarList();
            for (cr.ac.una.evacomunaws.controller.TarTrabajadorevaluarDto item : tarCompetenciaevaluarList) {
                TarTrabajadorevaluarDto convertedItem = new TarTrabajadorevaluarDto(item);
                this.tarTrabajadorevaluarList.add(convertedItem);
            }
        }
        
        this.proVersion = tarProcesoevaluacionDto.getProVersion();
        this.modificado = tarProcesoevaluacionDto.isModificado();
    }

    public cr.ac.una.evacomunaws.controller.TarProcesoevaluacionDto consultas() {
        cr.ac.una.evacomunaws.controller.TarProcesoevaluacionDto tarProcesoevaluacionDtoSoap = new cr.ac.una.evacomunaws.controller.TarProcesoevaluacionDto();
        tarProcesoevaluacionDtoSoap.setProEstado(this.getProEstado());
//        tarProcesoevaluacionDtoSoap.setProFfin(cr.ac.una.evacomunaws.controller.LocalDate.class.cast(this.getProFfin()));
//        tarProcesoevaluacionDtoSoap.setProFini(cr.ac.una.evacomunaws.controller.LocalDate.class.cast(this.getProFini()));
        tarProcesoevaluacionDtoSoap.setProId(this.getProId());
        tarProcesoevaluacionDtoSoap.setProTitulo(this.getProTitulo());
        if (!this.tarTrabajadorevaluarListEliminados.isEmpty()) {
            List<TarTrabajadorevaluarDto> tarCompetenciaevaluarElimimados = this.tarTrabajadorevaluarListEliminados;
            for (TarTrabajadorevaluarDto item : tarCompetenciaevaluarElimimados) {
                tarProcesoevaluacionDtoSoap.getTarTrabajadorevaluarListEliminados().add(item.consultas());
            }
        }
        if (!this.tarTrabajadorevaluarList.isEmpty()) {
            List<TarTrabajadorevaluarDto> tarCompetenciaevaluarList = this.tarTrabajadorevaluarList;
            for (TarTrabajadorevaluarDto item : tarCompetenciaevaluarList) {
                tarProcesoevaluacionDtoSoap.getTarTrabajadorevaluarList().add(item.consultas());
            }
        }
        
        tarProcesoevaluacionDtoSoap.setProVersion(this.getProVersion());
        tarProcesoevaluacionDtoSoap.setModificado(this.getModificado());
        return tarProcesoevaluacionDtoSoap;
    }

    public Long getProId() {
        if (this.proId.get() != null && !this.proId.get().isEmpty()) {
            return Long.valueOf(this.proId.get());
        } else {
            return null;
        }
    }

    public void setProId(Long proId) {
        this.proId.set(proId.toString());
    }

    public LocalDate getProFini() {
        return proFini.get();
    }

    public void setProFini(LocalDate proFini) {
        this.proFini.set(proFini);
    }

    public LocalDate getProFfin() {
        return proFfin.get();
    }

    public void setProFfin(LocalDate proFfin) {
        this.proFfin.set(proFfin);
    }

    public String getProTitulo() {
        return proTitulo.get();
    }

    public void setProTitulo(String proTitulo) {
        this.proTitulo.set(proTitulo);
    }

    public String getProEstado() {
        return proEstado.get();
    }

    public void setProEstado(String proEstado) {
        this.proEstado.set(proEstado);
    }

    public Long getProVersion() {
        return proVersion;
    }

    public void setProVersion(Long proVersion) {
        this.proVersion = proVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
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

}
