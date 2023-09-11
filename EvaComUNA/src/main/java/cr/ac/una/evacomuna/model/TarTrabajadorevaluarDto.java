/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.model;

/**
 *
 * @author kevin
 */
public class TarTrabajadorevaluarDto {
    private Long traId;
    private String traResultado;
    private Long traVersion;
    private Boolean modificado;
    
    
    public TarTrabajadorevaluarDto() {
        this.modificado = false;
    }
    public TarTrabajadorevaluarDto(TarTrabajadorevaluar tarTrabajadorevaluar) {
        this.traId = tarTrabajadorevaluar.getTraId();
        this.traResultado = tarTrabajadorevaluar.getTraResultado();
        this.traVersion = tarTrabajadorevaluar.getTraVersion();
    }

    public Long getTraId() {
        return traId;
    }

    public void setTraId(Long traId) {
        this.traId = traId;
    }

    public String getTraResultado() {
        return traResultado;
    }

    public void setTraResultado(String traResultado) {
        this.traResultado = traResultado;
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
