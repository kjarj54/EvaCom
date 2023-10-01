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
public class TarCompetenciaDto {

    private Long comId;
    private String comNombre;
    private String comEstado;
    private Long comVersion;
    private Boolean modificado;
    List<TarPuestoDto> tarPuestoList = new ArrayList<>();
    List<TarPuestoDto> tarPuestoEliminados = new ArrayList<>();
    List<TarCaracteristicaDto> tarCaracteristicaList = new ArrayList<>();
    List<TarCaracteristicaDto> tarCaracteristicaEliminados = new ArrayList<>();
    List<TarCompetenciaevaluarDto> tarCompetenciaevaluarList = new ArrayList<>();
    List<TarCompetenciaevaluarDto> tarCompetenciaevaluarElimimados = new ArrayList<>();

    public TarCompetenciaDto() {
        this.modificado = false;
        tarCaracteristicaList = new ArrayList<>();
        tarCaracteristicaEliminados = new ArrayList<>();
        tarPuestoList = new ArrayList<>();
        tarPuestoEliminados = new ArrayList<>();
        tarCompetenciaevaluarList = new ArrayList<>();
        tarCompetenciaevaluarElimimados = new ArrayList<>();
    }

    public TarCompetenciaDto(TarCompetencia tarCompetencia) {
        this.comId = tarCompetencia.getComId();
        this.comNombre = tarCompetencia.getComNombre();
        this.comEstado = tarCompetencia.getComEstado();
        this.comVersion = tarCompetencia.getComVersion();
    }

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public String getComNombre() {
        return comNombre;
    }

    public void setComNombre(String comNombre) {
        this.comNombre = comNombre;
    }

    public String getComEstado() {
        return comEstado;
    }

    public void setComEstado(String comEstado) {
        this.comEstado = comEstado;
    }

    public Long getComVersion() {
        return comVersion;
    }

    public void setComVersion(Long comVersion) {
        this.comVersion = comVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public List<TarPuestoDto> getTarPuestoList() {
        return tarPuestoList;
    }

    public void setTarPuestoList(List<TarPuestoDto> tarPuestoList) {
        this.tarPuestoList = tarPuestoList;
    }

    public List<TarPuestoDto> getTarPuestoEliminados() {
        return tarPuestoEliminados;
    }

    public void setTarPuestoEliminados(List<TarPuestoDto> tarPuestoEliminados) {
        this.tarPuestoEliminados = tarPuestoEliminados;
    }

    public List<TarCaracteristicaDto> getTarCaracteristicaList() {
        return tarCaracteristicaList;
    }

    public void setTarCaracteristicaList(List<TarCaracteristicaDto> tarCaracteristicaList) {
        this.tarCaracteristicaList = tarCaracteristicaList;
    }

    public List<TarCaracteristicaDto> getTarCaracteristicaEliminados() {
        return tarCaracteristicaEliminados;
    }

    public void setTarCaracteristicaEliminados(List<TarCaracteristicaDto> tarCaracteristicaEliminados) {
        this.tarCaracteristicaEliminados = tarCaracteristicaEliminados;
    }

    public List<TarCompetenciaevaluarDto> getTarCompetenciaevaluarList() {
        return tarCompetenciaevaluarList;
    }

    public void setTarCompetenciaevaluarList(List<TarCompetenciaevaluarDto> tarCompetenciaevaluarList) {
        this.tarCompetenciaevaluarList = tarCompetenciaevaluarList;
    }

    public List<TarCompetenciaevaluarDto> getTarCompetenciaevaluarElimimados() {
        return tarCompetenciaevaluarElimimados;
    }

    public void setTarCompetenciaevaluarElimimados(List<TarCompetenciaevaluarDto> tarCompetenciaevaluarElimimados) {
        this.tarCompetenciaevaluarElimimados = tarCompetenciaevaluarElimimados;
    }

}
