/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

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

    public TarProcesoevaluacionDto() {
        this.proId = new SimpleStringProperty();
        this.proFini = new SimpleObjectProperty();
        this.proFfin = new SimpleObjectProperty();
        this.proTitulo = new SimpleStringProperty();
        this.proEstado = new SimpleObjectProperty("a");//definir no recuerdo que tenia de default
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

}
