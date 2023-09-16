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
public class TarEvaluadorDto {

    public SimpleStringProperty evaluId;
    public SimpleStringProperty evaluRetroalimentacion;
    private Long evaluVersion;
    private Boolean modificado;

    public TarEvaluadorDto() {
        this.evaluId = new SimpleStringProperty();
        this.evaluRetroalimentacion = new SimpleStringProperty();
        this.modificado = false;

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

}
