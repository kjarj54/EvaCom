/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author kevin
 */
public class TarCompetenciaevaluarDto {
    public SimpleStringProperty coeId;
    public SimpleStringProperty coeCalificacion;
    private Long coeVersion;
    private Boolean modficado;
    public TarCompetenciaDto comId;
    public TarEvaluadorDto evaluId;
    
    public TarCompetenciaevaluarDto() {
        this.coeId = new SimpleStringProperty();
        this.coeCalificacion = new SimpleStringProperty();
        this.modficado = false;
        this.comId = new TarCompetenciaDto();
        this.evaluId = new TarEvaluadorDto();
    }
    
    public TarCompetenciaevaluarDto(cr.ac.una.evacomunaws.controller.TarCompetenciaevaluarDto tarCaracteristicaDto) {
        this();
        this.coeCalificacion.set(tarCaracteristicaDto.getCoeCalificacion());
    }
    
    public cr.ac.una.evacomunaws.controller.TarCompetenciaevaluarDto consultas(){
        cr.ac.una.evacomunaws.controller.TarCompetenciaevaluarDto tarCompetenciaevaluarDtoSoap = new cr.ac.una.evacomunaws.controller.TarCompetenciaevaluarDto();
        tarCompetenciaevaluarDtoSoap.setCoeId(this.getCoeId());
        tarCompetenciaevaluarDtoSoap.setCoeCalificacion(this.getCoeCalificacion());
        return tarCompetenciaevaluarDtoSoap;
    }

    public Long getCoeId() {
        if (this.coeId.get()!= null && !this.coeId.get().isEmpty()) {
            return Long.valueOf(this.coeId.get());
        } else {
            return null;
        }
    }

    public void setCoeId(Long coeId) {
        this.coeId.set(coeId.toString());
    }

    public String getCoeCalificacion() {
        return coeCalificacion.get();
    }

    public void setCoeCalificacion(String coeCalificacion) {
        this.coeCalificacion.set(coeCalificacion);
    }

    public Long getCoeVersion() {
        return coeVersion;
    }

    public void setCoeVersion(Long coeVersion) {
        this.coeVersion = coeVersion;
    }

    public Boolean getModficado() {
        return modficado;
    }

    public void setModficado(Boolean modficado) {
        this.modficado = modficado;
    }

    public TarCompetenciaDto getComId() {
        return comId;
    }

    public void setComId(TarCompetenciaDto comId) {
        this.comId = comId;
    }

    public TarEvaluadorDto getEvaluId() {
        return evaluId;
    }

    public void setEvaluId(TarEvaluadorDto evaluId) {
        this.evaluId = evaluId;
    }
    
    
    
}
