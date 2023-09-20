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
    public TarCompetenciaDto competenciaDto;
    

    public TarCaracteristicaDto() {
        this.carId = new SimpleStringProperty();
        this.carDescripcion = new SimpleStringProperty();
        this.modificado = false;
        this.competenciaDto = new TarCompetenciaDto();
    }
    
    public TarCaracteristicaDto(cr.ac.una.evacomunaws.controller.TarCaracteristicaDto tarCaracteristicaDto) {
        this();
        this.carId.set(tarCaracteristicaDto.getCarId().toString());
        this.carDescripcion.set(tarCaracteristicaDto.getCarDescripcion());
        this.competenciaDto = new TarCompetenciaDto(tarCaracteristicaDto.getCompetenciaDto());
    }
    
    public cr.ac.una.evacomunaws.controller.TarCaracteristicaDto consultas(){
        cr.ac.una.evacomunaws.controller.TarCaracteristicaDto tarCaracteristicaDtoSoap = new cr.ac.una.evacomunaws.controller.TarCaracteristicaDto();
        tarCaracteristicaDtoSoap.setCarId(this.getCarId());
        tarCaracteristicaDtoSoap.setCarDescripcion(this.getCarDescripcion());
        tarCaracteristicaDtoSoap.setCompetenciaDto(this.getCompetenciaDto().consultas());
        return tarCaracteristicaDtoSoap;
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

    public TarCompetenciaDto getCompetenciaDto() {
        return competenciaDto;
    }

    public void setCompetenciaDto(TarCompetenciaDto competenciaDto) {
        this.competenciaDto = competenciaDto;
    }
    
    
    
}
