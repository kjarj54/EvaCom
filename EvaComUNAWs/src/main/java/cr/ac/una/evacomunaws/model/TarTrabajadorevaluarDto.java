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
public class TarTrabajadorevaluarDto {

    private Long traId;
    private String traResultado;
    private Long traVersion;
    private Boolean modificado;
    List<TarEvaluadorDto> tarEvaluadorList = new ArrayList<>();
    List<TarEvaluadorDto> tarEvaluadorListEliminados = new ArrayList<>();
    private TarProcesoevaluacionDto procesoDto;
    private TarUsuarioDto usuarioDto;

    public TarTrabajadorevaluarDto() {
        this.modificado = false;
        this.tarEvaluadorList = new ArrayList<>();
        this.tarEvaluadorListEliminados = new ArrayList<>();
    }

    public TarTrabajadorevaluarDto(TarTrabajadorevaluar tarTrabajadorevaluar) {
        this.traId = tarTrabajadorevaluar.getTraId();
        this.traResultado = tarTrabajadorevaluar.getTraResultado();
        this.traVersion = tarTrabajadorevaluar.getTraVersion();
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

    public TarProcesoevaluacionDto getProcesoDto() {
        return procesoDto;
    }

    public void setProcesoDto(TarProcesoevaluacionDto procesoDto) {
        this.procesoDto = procesoDto;
    }

    public TarUsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(TarUsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public List<TarEvaluadorDto> getTarEvaluadorListEliminados() {
        return tarEvaluadorListEliminados;
    }

    public void setTarEvaluadorListEliminados(List<TarEvaluadorDto> tarEvaluadorListEliminados) {
        this.tarEvaluadorListEliminados = tarEvaluadorListEliminados;
    }

}
