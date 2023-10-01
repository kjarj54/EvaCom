/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author kevin
 */
public class TarParametrosDto {

    public SimpleStringProperty parId;
    public SimpleStringProperty parNombre;
    public SimpleStringProperty parEmail;
    public SimpleStringProperty parClave;
    public ObjectProperty<byte[]> parHtml;
    public ObjectProperty<byte[]> parLogo;
    public SimpleStringProperty parDescripcion;
    private Long parVersion;
    private Boolean modificado;

    public TarParametrosDto() {
        this.parId = new SimpleStringProperty();
        this.parNombre = new SimpleStringProperty();
        this.parEmail = new SimpleStringProperty();
        this.parClave = new SimpleStringProperty();
        this.parHtml = new SimpleObjectProperty();
        this.parLogo = new SimpleObjectProperty();
        this.parDescripcion = new SimpleStringProperty();
        this.modificado = false;
    }

    public TarParametrosDto(cr.ac.una.evacomunaws.controller.TarParametrosDto tarParametrosDto) {
        this();
        this.parClave.set(tarParametrosDto.getParClave());
        this.parDescripcion.set(tarParametrosDto.getParDescripcion());
        this.parEmail.set(tarParametrosDto.getParEmail());
        this.parHtml.set(tarParametrosDto.getParHtml());
        this.parId.set(tarParametrosDto.getParId().toString());
        this.parLogo.set(tarParametrosDto.getParLogo());
        this.parNombre.set(tarParametrosDto.getParNombre());
        this.parVersion = tarParametrosDto.getParVersion();
    }

    public cr.ac.una.evacomunaws.controller.TarParametrosDto consultas() {
        cr.ac.una.evacomunaws.controller.TarParametrosDto tarParametrosDtoSoap = new cr.ac.una.evacomunaws.controller.TarParametrosDto();
        tarParametrosDtoSoap.setParClave(this.getParClave());
        tarParametrosDtoSoap.setParDescripcion(this.getParDescripcion());
        tarParametrosDtoSoap.setParEmail(this.getParEmail());
        tarParametrosDtoSoap.setParNombre(this.getParNombre());
        tarParametrosDtoSoap.setParHtml(this.getParHtml());
        tarParametrosDtoSoap.setParLogo(this.getParLogo());
        tarParametrosDtoSoap.setParVersion(this.getParVersion());
        return tarParametrosDtoSoap;
    }

    public Long getParId() {
        if (this.parId.get() != null && !this.parId.get().isEmpty()) {
            return Long.valueOf(this.parId.get());
        } else {
            return null;
        }
    }

    public void setParId(Long parId) {
        this.parId.set(parId.toString());
    }

    public String getParNombre() {
        return parNombre.get();
    }

    public void setParNombre(String parNombre) {
        this.parNombre.set(parNombre);
    }

    public String getParEmail() {
        return parEmail.get();
    }

    public void setParEmail(String parEmail) {
        this.parEmail.set(parEmail);
    }

    public String getParClave() {
        return parClave.get();
    }

    public void setParClave(String parClave) {
        this.parClave.set(parClave);
    }

    public byte[] getParHtml() {
        return parHtml.get();
    }

    public void setParHtml(byte[] parHtml) {
        this.parHtml.set(parHtml);
    }

    public byte[] getParLogo() {
        return parLogo.get();
    }

    public void setParLogo(byte[] parLogo) {
        this.parLogo.set(parLogo);
    }

    public String getParDescripcion() {
        return parDescripcion.get();
    }

    public void setParDescripcion(String parDescripcion) {
        this.parDescripcion.set(parDescripcion);
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
