/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.model;

/**
 *
 * @author kevin
 */
public class TarEvaluadorDto {
    private Long evaluId;
    private String evaluRetroalimentacion;
    private Long evaluVersion;
    private Boolean modificado;
    
    public TarEvaluadorDto() {
        this.modificado = false;
    }
    
    public TarEvaluadorDto(TarEvaluador tarEvaluador) {
        this.evaluId = tarEvaluador.getEvaluId();
        this.evaluRetroalimentacion = tarEvaluador.getEvaluRetroalimentacion();
        this.evaluVersion = tarEvaluador.getEvaluVersion();
    }

    public Long getEvaluId() {
        return evaluId;
    }

    public void setEvaluId(Long evaluId) {
        this.evaluId = evaluId;
    }

    public String getEvaluRetroalimentacion() {
        return evaluRetroalimentacion;
    }

    public void setEvaluRetroalimentacion(String evaluRetroalimentacion) {
        this.evaluRetroalimentacion = evaluRetroalimentacion;
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
