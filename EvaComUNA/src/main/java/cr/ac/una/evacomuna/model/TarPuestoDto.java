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
public class TarPuestoDto {
    public SimpleStringProperty pueId;
    public SimpleStringProperty pueNombre;
    public SimpleStringProperty pueEstado;
    private Long pueVersion;
    private Boolean modificado;
    
       
    public TarPuestoDto() {
        this.pueId = new SimpleStringProperty();
        this.pueNombre = new SimpleStringProperty();
        this.pueEstado = new SimpleStringProperty();
        this.modificado = false;
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
     
    
}
