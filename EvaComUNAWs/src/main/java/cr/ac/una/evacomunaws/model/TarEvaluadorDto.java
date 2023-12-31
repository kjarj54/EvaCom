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
public class TarEvaluadorDto {

    private Long evaluId;
    private String evaluRetroalimentacion;
    private Long evaluVersion;
    private String evaluCalificacion;
    private Boolean modificado;
    private TarTrabajadorevaluarDto trabajadorevaluarDto;
    private TarUsuarioDto usuarioDto;
    List<TarCompetenciaevaluarDto> tarCompetenciaevaluarList = new ArrayList<>();
    List<TarCompetenciaevaluarDto> tarCompetenciaevaluarListEliminados = new ArrayList<>();

    public TarEvaluadorDto() {
        this.modificado = false;
        this.trabajadorevaluarDto = new TarTrabajadorevaluarDto();
        this.usuarioDto = new TarUsuarioDto();
        tarCompetenciaevaluarList = new ArrayList<>();
        tarCompetenciaevaluarListEliminados = new ArrayList<>();
    }

    public TarEvaluadorDto(TarEvaluador tarEvaluador) {
        this.evaluId = tarEvaluador.getEvaluId();
        this.evaluRetroalimentacion = tarEvaluador.getEvaluRetroalimentacion();
        this.evaluVersion = tarEvaluador.getEvaluVersion();
        this.evaluCalificacion = tarEvaluador.getEvaluCalificacion();
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

    public TarTrabajadorevaluarDto getTrabajadorevaluarDto() {
        return trabajadorevaluarDto;
    }

    public void setTrabajadorevaluarDto(TarTrabajadorevaluarDto trabajadorevaluarDto) {
        this.trabajadorevaluarDto = trabajadorevaluarDto;
    }

    public TarUsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(TarUsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public List<TarCompetenciaevaluarDto> getTarCompetenciaevaluarList() {
        return tarCompetenciaevaluarList;
    }

    public void setTarCompetenciaevaluarList(List<TarCompetenciaevaluarDto> tarCompetenciaevaluarList) {
        this.tarCompetenciaevaluarList = tarCompetenciaevaluarList;
    }

    public List<TarCompetenciaevaluarDto> getTarCompetenciaevaluarListEliminados() {
        return tarCompetenciaevaluarListEliminados;
    }

    public void setTarCompetenciaevaluarListEliminados(List<TarCompetenciaevaluarDto> tarCompetenciaevaluarListEliminados) {
        this.tarCompetenciaevaluarListEliminados = tarCompetenciaevaluarListEliminados;
    }

    public String getEvaluCalificacion() {
        return evaluCalificacion;
    }

    public void setEvaluCalificacion(String evaluCalificacion) {
        this.evaluCalificacion = evaluCalificacion;
    }

    
}
