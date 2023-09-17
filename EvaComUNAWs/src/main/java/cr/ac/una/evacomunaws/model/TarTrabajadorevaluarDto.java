/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.model;

import java.util.List;

/**
 *
 * @author kevin
 */
public class TarTrabajadorevaluarDto {

    private Long traId;
    private String traResultado;
    private Long traVersion;
    private Boolean modificado;
    List<TarEvaluadorDto> tarEvaluadorList;
    List<TarEvaluadorDto> tarEvaluadorListEliminados;
    private TarProcesoevaluacionDto proId = null;
    private TarUsuarioDto usuId = null;

    public TarTrabajadorevaluarDto() {
        this.modificado = false;
    }

    public TarTrabajadorevaluarDto(TarTrabajadorevaluar tarTrabajadorevaluar) {
        this.traId = tarTrabajadorevaluar.getTraId();
        this.traResultado = tarTrabajadorevaluar.getTraResultado();
        this.traVersion = tarTrabajadorevaluar.getTraVersion();
        if (tarTrabajadorevaluar.getUsuId() != null) {
            this.usuId = new TarUsuarioDto(tarTrabajadorevaluar.getUsuId());
        }
        if (tarTrabajadorevaluar.getProId() != null) {
            this.proId = new TarProcesoevaluacionDto(tarTrabajadorevaluar.getProId());
        }
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

    public List<TarEvaluadorDto> getTarEvaluadorList() {
        return tarEvaluadorList;
    }

    public void setTarEvaluadorList(List<TarEvaluadorDto> tarEvaluadorList) {
        this.tarEvaluadorList = tarEvaluadorList;
    }

    public TarProcesoevaluacionDto getProId() {
        return proId;
    }

    public void setProId(TarProcesoevaluacionDto proId) {
        this.proId = proId;
    }

    public TarUsuarioDto getUsuId() {
        return usuId;
    }

    public void setUsuId(TarUsuarioDto usuId) {
        this.usuId = usuId;
    }

    public List<TarEvaluadorDto> getTarEvaluadorListEliminados() {
        return tarEvaluadorListEliminados;
    }

    public void setTarEvaluadorListEliminados(List<TarEvaluadorDto> tarEvaluadorListEliminados) {
        this.tarEvaluadorListEliminados = tarEvaluadorListEliminados;
    }

}
