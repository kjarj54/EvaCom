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
    private TarCompetenciaDto comId = null;
    private TarEvaluadorDto evaluId = null;

    public TarCompetenciaevaluarDto() {
        this.modficado = false;
        this.comId = new TarCompetenciaDto();
        this.evaluId = new TarEvaluadorDto();
    }

    public TarCompetenciaevaluarDto(TarCompetenciaevaluar tarCompetenciaevaluar) {
        this.coeId = tarCompetenciaevaluar.getCoeId();
        this.coeCalificacion = tarCompetenciaevaluar.getCoeCalificacion();
        this.coeVersion = tarCompetenciaevaluar.getCoeVersion();
        if (tarCompetenciaevaluar.getComId() != null) {
            this.comId = new TarCompetenciaDto(tarCompetenciaevaluar.getComId());
        }
        if (tarCompetenciaevaluar.getEvaluId() != null) {
            this.evaluId = new TarEvaluadorDto(tarCompetenciaevaluar.getEvaluId());
        }
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

    public TarCompetenciaDto getComId() {
        return comId;
    }

    public void setComId(TarCompetenciaDto comId) {
        this.comId = comId;
    }

    public TarEvaluadorDto getEvaluId() {
        return evaluId;
    }

    public void setEvaluId(TarEvaluadorDto evaluId) {
        this.evaluId = evaluId;
    }

}
