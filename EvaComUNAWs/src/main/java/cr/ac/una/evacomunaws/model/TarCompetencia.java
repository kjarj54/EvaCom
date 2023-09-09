/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "TAR_COMPETENCIA",schema="EvaComUNA")
@NamedQueries({
    @NamedQuery(name = "TarCompetencia.findAll", query = "SELECT t FROM TarCompetencia t"),
    @NamedQuery(name = "TarCompetencia.findByComId", query = "SELECT t FROM TarCompetencia t WHERE t.comId = :comId"),
    @NamedQuery(name = "TarCompetencia.findByComNombre", query = "SELECT t FROM TarCompetencia t WHERE t.comNombre = :comNombre"),
    @NamedQuery(name = "TarCompetencia.findByComEstado", query = "SELECT t FROM TarCompetencia t WHERE t.comEstado = :comEstado"),
    @NamedQuery(name = "TarCompetencia.findByComVersion", query = "SELECT t FROM TarCompetencia t WHERE t.comVersion = :comVersion")})
public class TarCompetencia implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TAR_COMPETENCIA_ID_GENERATOR", sequenceName = "evacomuna.TAR_COMPETENCIA_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAR_COMPETENCIA_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "COM_ID")
    private Long comId;
    @Basic(optional = false)
    @Column(name = "COM_NOMBRE")
    private String comNombre;
    @Basic(optional = false)
    @Column(name = "COM_ESTADO")
    private String comEstado;
    @Version
    @Basic(optional = false)
    @Column(name = "COM_VERSION")
    private Long comVersion;
    @ManyToMany(mappedBy = "tarCompetenciaList", fetch = FetchType.LAZY)
    private List<TarPuesto> tarPuestoList;
    @OneToMany(mappedBy = "comId", fetch = FetchType.LAZY)
    private List<TarCaracteristica> tarCaracteristicaList;
    @OneToMany(mappedBy = "comId", fetch = FetchType.LAZY)
    private List<TarCompetenciaevaluar> tarCompetenciaevaluarList;

    public TarCompetencia() {
    }

    public TarCompetencia(Long comId) {
        this.comId = comId;
    }

    public TarCompetencia(Long comId, String comNombre, String comEstado, Long comVersion) {
        this.comId = comId;
        this.comNombre = comNombre;
        this.comEstado = comEstado;
        this.comVersion = comVersion;
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

    public List<TarPuesto> getTarPuestoList() {
        return tarPuestoList;
    }

    public void setTarPuestoList(List<TarPuesto> tarPuestoList) {
        this.tarPuestoList = tarPuestoList;
    }

    public List<TarCaracteristica> getTarCaracteristicaList() {
        return tarCaracteristicaList;
    }

    public void setTarCaracteristicaList(List<TarCaracteristica> tarCaracteristicaList) {
        this.tarCaracteristicaList = tarCaracteristicaList;
    }

    public List<TarCompetenciaevaluar> getTarCompetenciaevaluarList() {
        return tarCompetenciaevaluarList;
    }

    public void setTarCompetenciaevaluarList(List<TarCompetenciaevaluar> tarCompetenciaevaluarList) {
        this.tarCompetenciaevaluarList = tarCompetenciaevaluarList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comId != null ? comId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarCompetencia)) {
            return false;
        }
        TarCompetencia other = (TarCompetencia) object;
        if ((this.comId == null && other.comId != null) || (this.comId != null && !this.comId.equals(other.comId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.evacomunaws.model.TarCompetencia[ comId=" + comId + " ]";
    }
    
}
