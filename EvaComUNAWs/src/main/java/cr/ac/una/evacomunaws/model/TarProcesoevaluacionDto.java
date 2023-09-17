/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class TarProcesoevaluacionDto {
    private Long proId;
    private LocalDate proFini;
    private LocalDate proFfin;
    private String proTitulo;
    private String proEstado;
    private Long proVersion;
    private Boolean modificado;
    List<TarTrabajadorevaluarDto> tarTrabajadorevaluarList;
    List<TarTrabajadorevaluarDto> tarTrabajadorevaluarListEliminados;

    public TarProcesoevaluacionDto() {
        this.modificado = false;
        tarTrabajadorevaluarList = new ArrayList<>();
        tarTrabajadorevaluarListEliminados = new ArrayList<>();
        
    }
    
    public TarProcesoevaluacionDto(TarProcesoevaluacion tarProcesoevaluacion) {
        this.proId = tarProcesoevaluacion.getProId();
        this.proFini = tarProcesoevaluacion.getProFini();
        this.proFfin = tarProcesoevaluacion.getProFfin();
        this.proTitulo = tarProcesoevaluacion.getProTitulo();
        this.proEstado = tarProcesoevaluacion.getProEstado();
        this.proVersion = tarProcesoevaluacion.getProVersion();
    }

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public LocalDate getProFini() {
        return proFini;
    }

    public void setProFini(LocalDate proFini) {
        this.proFini = proFini;
    }

    public LocalDate getProFfin() {
        return proFfin;
    }

    public void setProFfin(LocalDate proFfin) {
        this.proFfin = proFfin;
    }

    public String getProTitulo() {
        return proTitulo;
    }

    public void setProTitulo(String proTitulo) {
        this.proTitulo = proTitulo;
    }

    public String getProEstado() {
        return proEstado;
    }

    public void setProEstado(String proEstado) {
        this.proEstado = proEstado;
    }

    public Long getProVersion() {
        return proVersion;
    }

    public void setProVersion(Long proVersion) {
        this.proVersion = proVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
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
    
    
    
}
