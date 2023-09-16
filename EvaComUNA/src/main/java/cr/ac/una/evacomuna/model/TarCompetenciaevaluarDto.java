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
    
    public TarCompetenciaevaluarDto() {
        this.coeId = new SimpleStringProperty();
        this.coeCalificacion = new SimpleStringProperty();
        this.modficado = false;
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
    
    
    
}
