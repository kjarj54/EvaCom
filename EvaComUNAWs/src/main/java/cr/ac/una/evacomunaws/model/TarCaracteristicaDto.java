/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.model;

/**
 *
 * @author kevin
 */
public class TarCaracteristicaDto {

    private Long carId;
    private String carDescripcion;
    private Long carVersion;
    private Boolean modificado;
    private TarCompetenciaDto competenciaDto;

    public TarCaracteristicaDto() {
        this.modificado = false;
        this.competenciaDto = new TarCompetenciaDto();
    }

    public TarCaracteristicaDto(TarCaracteristica tarCaracteristica) {
        this();
        this.carId = tarCaracteristica.getCarId();
        this.carDescripcion = tarCaracteristica.getCarDescripcion();
        this.carVersion = tarCaracteristica.getCarVersion();
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarDescripcion() {
        return carDescripcion;
    }

    public void setCarDescripcion(String carDescripcion) {
        this.carDescripcion = carDescripcion;
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
