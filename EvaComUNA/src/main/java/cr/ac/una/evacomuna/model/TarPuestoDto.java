/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.model;

/**
 *
 * @author kevin
 *
public class TarPuestoDto {
    private Long pueId;
    private String pueNombre;
    private String pueEstado;
    private Long pueVersion;
    private Boolean modificado;
    
    public TarPuestoDto() {
        this.modificado = false;
    }
    
    public TarPuestoDto(TarPuesto tarPuesto) {
        this.pueId = tarPuesto.getPueId();
        this.pueNombre = tarPuesto.getPueNombre();
        this.pueEstado = tarPuesto.getPueEstado();
        this.pueVersion = tarPuesto.getPueVersion();
    }

    public Long getPueId() {
        return pueId;
    }

    public void setPueId(Long pueId) {
        this.pueId = pueId;
    }

    public String getPueNombre() {
        return pueNombre;
    }

    public void setPueNombre(String pueNombre) {
        this.pueNombre = pueNombre;
    }

    public String getPueEstado() {
        return pueEstado;
    }

    public void setPueEstado(String pueEstado) {
        this.pueEstado = pueEstado;
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
*/