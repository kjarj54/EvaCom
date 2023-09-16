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
public class TarCaracteristicaDto {
    public SimpleStringProperty carId;
    public SimpleStringProperty carDescripcion;
    private Long carVersion;
    private Boolean modificado;
    

    public TarCaracteristicaDto() {
        this.carId = new SimpleStringProperty();
        this.carDescripcion = new SimpleStringProperty();
        this.modificado = false;
    }

    
    
    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }  
    
    public Long getCarId() {
        if (this.carId.get() != null && !this.carId.get().isEmpty()) {
            return Long.valueOf(this.carId.get());
        } else {
            return null;
        }
    }

    public void setCarId(Long carId) {
        this.carId.set(carId.toString());
    }

    public String getCarDescripcion() {
        return carDescripcion.get();
    }

    public void setCarDescripcion(String carDescripcion) {
        this.carDescripcion.set(carDescripcion);
    }

    public Long getCarVersion() {
        return carVersion;
    }

    public void setCarVersion(Long carVersion) {
        this.carVersion = carVersion;
    }
    
    
    
}
