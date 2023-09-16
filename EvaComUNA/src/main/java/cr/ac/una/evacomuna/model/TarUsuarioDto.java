/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.model;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author kevin
*
public class TarUsuarioDto {
    public SimpleStringProperty usuId;
    public SimpleStringProperty usuNombre;
    public SimpleStringProperty usuApellido;
    public SimpleStringProperty usuCedula;
    public SimpleStringProperty usuCorreo;
    public SimpleStringProperty usuTelefono;
    public SimpleStringProperty usuCelular;
    public Byte[] usuFoto;
    public String usuUsu;
    public SimpleStringProperty usuClave;
    public SimpleStringProperty usuTempclave;
    public String usuActivo;
    public String usuAdmin;
    private Long usuVersion;
    private Boolean modificado;
    
    
    public TarUsuarioDto() {
        this.usuId = ();
        this.usuNombre = ();
        this.usuApellido = ();
        this.usuCedula = ();
        this.usuCorreo = ();
        this.usuTelefono = ();
        this.usuCelular = ();
        this.usuFoto = ();
        this.usuClave = ();
        this.usuActivo = ();
        this.usuAdmin = ();
        this.modificado = false;
    }

    public Long getUsuId() {
        return usuId;
    }

    public void setUsuId(Long usuId) {
        this.usuId = usuId;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuApellido() {
        return usuApellido;
    }

    public void setUsuApellido(String usuApellido) {
        this.usuApellido = usuApellido;
    }

    public String getUsuCedula() {
        return usuCedula;
    }

    public void setUsuCedula(String usuCedula) {
        this.usuCedula = usuCedula;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public Long getUsuTelefono() {
        return usuTelefono;
    }

    public void setUsuTelefono(Long usuTelefono) {
        this.usuTelefono = usuTelefono;
    }

    public Long getUsuCelular() {
        return usuCelular;
    }

    public void setUsuCelular(Long usuCelular) {
        this.usuCelular = usuCelular;
    }

    public Serializable getUsuFoto() {
        return usuFoto;
    }

    public void setUsuFoto(Serializable usuFoto) {
        this.usuFoto = usuFoto;
    }

    public String getUsuClave() {
        return usuClave;
    }

    public void setUsuClave(String usuClave) {
        this.usuClave = usuClave;
    }

    public String getUsuActivo() {
        return usuActivo;
    }

    public void setUsuActivo(String usuActivo) {
        this.usuActivo = usuActivo;
    }

    public String getUsuAdmin() {
        return usuAdmin;
    }

    public void setUsuAdmin(String usuAdmin) {
        this.usuAdmin = usuAdmin;
    }

    public Long getUsuVersion() {
        return usuVersion;
    }

    public void setUsuVersion(Long usuVersion) {
        this.usuVersion = usuVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
    
    
}
*/