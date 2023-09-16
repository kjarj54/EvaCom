/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

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


    public TarCompetenciaDto() {
        this.comId = new SimpleStringProperty();
        this.comNombre = new SimpleStringProperty();
        this.comEstado = new SimpleBooleanProperty(false);
        this.modificado = false;
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
        return comEstado.get()?"A":"I";
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

}
