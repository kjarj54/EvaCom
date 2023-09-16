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
public class TarTrabajadorevaluarDto {
    public SimpleStringProperty traId;
    public SimpleStringProperty traResultado;
    private Long traVersion;
    private Boolean modificado;
    
    
    public TarTrabajadorevaluarDto() {
        this.traId = new SimpleStringProperty();
        this.traResultado = new SimpleStringProperty();
        this.modificado = false;
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
    
    
}
