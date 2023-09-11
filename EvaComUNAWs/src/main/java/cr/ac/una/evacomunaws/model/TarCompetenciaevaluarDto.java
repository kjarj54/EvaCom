/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.model;

/**
 *
 * @author kevin
 */
public class TarCompetenciaevaluarDto {
    private Long coeId;
    private String coeCalificacion;
    private Long coeVersion;
    private Boolean modficado;
    
    public TarCompetenciaevaluarDto() {
        this.modficado = false;
    }
    
    public TarCompetenciaevaluarDto(TarCompetenciaevaluar tarCompetenciaevaluar) {
        this.coeId = tarCompetenciaevaluar.getCoeId();
        this.coeCalificacion = tarCompetenciaevaluar.getCoeCalificacion();
        this.coeVersion = tarCompetenciaevaluar.getCoeVersion();
    }

    public Long getCoeId() {
        return coeId;
    }

    public void setCoeId(Long coeId) {
        this.coeId = coeId;
    }

    public String getCoeCalificacion() {
        return coeCalificacion;
    }

    public void setCoeCalificacion(String coeCalificacion) {
        this.coeCalificacion = coeCalificacion;
    }

    public Long getCoeVersion() {
        return coeVersion;
    }

    public void setCoeVersion(Long coeVersion) {
        this.coeVersion = coeVersion;
    }

    public Boolean getModficado() {
        return modficado;
    }

    public void setModficado(Boolean modficado) {
        this.modficado = modficado;
    }
    
    
    
}
