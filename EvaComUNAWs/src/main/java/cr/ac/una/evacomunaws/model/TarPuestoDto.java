/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class TarPuestoDto {

    private Long pueId;
    private String pueNombre;
    private String pueEstado;
    private Long pueVersion;
    private Boolean modificado;
    List<TarCompetenciaDto> tarCompetenciaList = new ArrayList<>();
    List<TarCompetenciaDto> tarCompetenciaListEliminados = new ArrayList<>();
    List<TarUsuarioDto> tarUsuarioList = new ArrayList<>();
    List<TarUsuarioDto> tarUsuarioListEliminados = new ArrayList<>();

    public TarPuestoDto() {
        this.modificado = false;
        tarCompetenciaList = new ArrayList<>();
        tarCompetenciaListEliminados = new ArrayList<>();
        tarUsuarioList = new ArrayList<>();
        tarUsuarioListEliminados = new ArrayList<>();
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

    public List<TarCompetenciaDto> getTarCompetenciaList() {
        return tarCompetenciaList;
    }

    public void setTarCompetenciaList(List<TarCompetenciaDto> tarCompetenciaList) {
        this.tarCompetenciaList = tarCompetenciaList;
    }

    public List<TarCompetenciaDto> getTarCompetenciaListEliminados() {
        return tarCompetenciaListEliminados;
    }

    public void setTarCompetenciaListEliminados(List<TarCompetenciaDto> tarCompetenciaListEliminados) {
        this.tarCompetenciaListEliminados = tarCompetenciaListEliminados;
    }

    public List<TarUsuarioDto> getTarUsuarioList() {
        return tarUsuarioList;
    }

    public void setTarUsuarioList(List<TarUsuarioDto> tarUsuarioList) {
        this.tarUsuarioList = tarUsuarioList;
    }

    public List<TarUsuarioDto> getTarUsuarioListEliminados() {
        return tarUsuarioListEliminados;
    }

    public void setTarUsuarioListEliminados(List<TarUsuarioDto> tarUsuarioListEliminados) {
        this.tarUsuarioListEliminados = tarUsuarioListEliminados;
    }

}
