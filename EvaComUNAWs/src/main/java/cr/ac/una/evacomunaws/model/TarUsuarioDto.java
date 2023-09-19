/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class TarUsuarioDto {

    private Long usuId;
    private String usuNombre;
    private String usuApellido;
    private String usuCedula;
    private String usuCorreo;
    private Long usuTelefono;
    private Long usuCelular;
    private Byte[] usuFoto;
    private String usuUsu;
    private String usuClave;
    private String usuTempclave;
    private String usuActivo;
    private String usuAdmin;
    private Long usuVersion;
    private Boolean modificado;
    List<TarEvaluadorDto> tarEvaluadorList;
    List<TarEvaluadorDto> tarEvaluadorListEliminados;
    List<TarTrabajadorevaluarDto> tarTrabajadorevaluarList;
    List<TarTrabajadorevaluarDto> tarTrabajadorevaluarListEliminados;
    private TarPuestoDto puestoDto = null;
    private LocalDateTime fecha;

    public TarUsuarioDto() {
        this.modificado = false;
        tarEvaluadorList = new ArrayList<>();
        tarEvaluadorListEliminados = new ArrayList<>();
        tarTrabajadorevaluarList = new ArrayList<>();
        tarTrabajadorevaluarListEliminados = new ArrayList<>();
        this.fecha = LocalDateTime.now();   
    }

    public TarUsuarioDto(TarUsuario tarUsuario) {
        this.usuId = tarUsuario.getUsuId();
        this.usuNombre = tarUsuario.getUsuNombre();
        this.usuApellido = tarUsuario.getUsuApellido();
        this.usuCedula = tarUsuario.getUsuCedula();
        this.usuCorreo = tarUsuario.getUsuCorreo();
        this.usuTelefono = tarUsuario.getUsuTelefono();
        this.usuCelular = tarUsuario.getUsuCelular();
        this.usuFoto = (Byte[]) tarUsuario.getUsuFoto();
        this.usuClave = tarUsuario.getUsuClave();
        this.usuActivo = tarUsuario.getUsuActivo();
        this.usuAdmin = tarUsuario.getUsuAdmin();
        this.usuVersion = tarUsuario.getUsuVersion();
        this.usuUsu = tarUsuario.getUsuUsu();
        this.usuTempclave = tarUsuario.getUsuTempclave();        
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

    public Byte[] getUsuFoto() {
        return usuFoto;
    }

    public void setUsuFoto(Byte[] usuFoto) {
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

    public String getUsuUsu() {
        return usuUsu;
    }

    public void setUsuUsu(String usuUsu) {
        this.usuUsu = usuUsu;
    }

    public String getUsuTempclave() {
        return usuTempclave;
    }

    public void setUsuTempclave(String usuTempclave) {
        this.usuTempclave = usuTempclave;
    }

    public List<TarEvaluadorDto> getTarEvaluadorList() {
        return tarEvaluadorList;
    }

    public void setTarEvaluadorList(List<TarEvaluadorDto> tarEvaluadorList) {
        this.tarEvaluadorList = tarEvaluadorList;
    }

    public List<TarEvaluadorDto> getTarEvaluadorListEliminados() {
        return tarEvaluadorListEliminados;
    }

    public void setTarEvaluadorListEliminados(List<TarEvaluadorDto> tarEvaluadorListEliminados) {
        this.tarEvaluadorListEliminados = tarEvaluadorListEliminados;
    }

    public List<TarTrabajadorevaluarDto> getTarTrabajadorevaluarList() {
        return tarTrabajadorevaluarList;
    }

    public void setTarTrabajadorevaluarList(List<TarTrabajadorevaluarDto> tarTrabajadorevaluarList) {
        this.tarTrabajadorevaluarList = tarTrabajadorevaluarList;
    }

    public List<TarTrabajadorevaluarDto> getTarTrabajadorevaluarListEliminados() {
        return tarTrabajadorevaluarListEliminados;
    }

    public void setTarTrabajadorevaluarListEliminados(List<TarTrabajadorevaluarDto> tarTrabajadorevaluarListEliminados) {
        this.tarTrabajadorevaluarListEliminados = tarTrabajadorevaluarListEliminados;
    }

    public TarPuestoDto getPuestoDto() {
        return puestoDto;
    }

    public void setPuestoDto(TarPuestoDto puestoDto) {
        this.puestoDto = puestoDto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    

}
