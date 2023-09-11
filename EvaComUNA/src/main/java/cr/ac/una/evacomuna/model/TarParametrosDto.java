/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.model;

import java.io.Serializable;

/**
 *
 * @author kevin
 */
public class TarParametrosDto {
    private Long parId;
    private String parNombre;
    private String parEmail;
    private String parClave;
    private Serializable parHtml;
    private Serializable parLogo;
    private String parDescripcion;
    private Long parVersion;
    private Boolean modificado;
    
    public TarParametrosDto() {
        this.modificado = false;
    }
    
    public TarParametrosDto(TarParametros tarParametros) {
        this.parId = tarParametros.getParId();
        this.parNombre = tarParametros.getParNombre();
        this.parEmail = tarParametros.getParEmail();
        this.parClave = tarParametros.getParClave();
        this.parHtml = tarParametros.getParHtml();
        this.parLogo = tarParametros.getParLogo();
        this.parDescripcion = tarParametros.getParDescripcion();
        this.parVersion = tarParametros.getParVersion();
    }

    public Long getParId() {
        return parId;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public String getParNombre() {
        return parNombre;
    }

    public void setParNombre(String parNombre) {
        this.parNombre = parNombre;
    }

    public String getParEmail() {
        return parEmail;
    }

    public void setParEmail(String parEmail) {
        this.parEmail = parEmail;
    }

    public String getParClave() {
        return parClave;
    }

    public void setParClave(String parClave) {
        this.parClave = parClave;
    }

    public Serializable getParHtml() {
        return parHtml;
    }

    public void setParHtml(Serializable parHtml) {
        this.parHtml = parHtml;
    }

    public Serializable getParLogo() {
        return parLogo;
    }

    public void setParLogo(Serializable parLogo) {
        this.parLogo = parLogo;
    }

    public String getParDescripcion() {
        return parDescripcion;
    }

    public void setParDescripcion(String parDescripcion) {
        this.parDescripcion = parDescripcion;
    }

    public Long getParVersion() {
        return parVersion;
    }

    public void setParVersion(Long parVersion) {
        this.parVersion = parVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
    
    
    
    
}
