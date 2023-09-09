/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.LocalDate;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "TAR_PROCESOEVALUACION",schema="EvaComUNA")
@NamedQueries({
    @NamedQuery(name = "TarProcesoevaluacion.findAll", query = "SELECT t FROM TarProcesoevaluacion t"),
    @NamedQuery(name = "TarProcesoevaluacion.findByProId", query = "SELECT t FROM TarProcesoevaluacion t WHERE t.proId = :proId"),
    @NamedQuery(name = "TarProcesoevaluacion.findByProFini", query = "SELECT t FROM TarProcesoevaluacion t WHERE t.proFini = :proFini"),
    @NamedQuery(name = "TarProcesoevaluacion.findByProFfin", query = "SELECT t FROM TarProcesoevaluacion t WHERE t.proFfin = :proFfin"),
    @NamedQuery(name = "TarProcesoevaluacion.findByProTitulo", query = "SELECT t FROM TarProcesoevaluacion t WHERE t.proTitulo = :proTitulo"),
    @NamedQuery(name = "TarProcesoevaluacion.findByProEstado", query = "SELECT t FROM TarProcesoevaluacion t WHERE t.proEstado = :proEstado"),
    @NamedQuery(name = "TarProcesoevaluacion.findByProVersion", query = "SELECT t FROM TarProcesoevaluacion t WHERE t.proVersion = :proVersion")})
public class TarProcesoevaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TAR_PROCESOEVALUACION_ID_GENERATOR", sequenceName = "evacomuna.TAR_PROCESOEVALUACION_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAR_PROCESOEVALUACION_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "PRO_ID")
    private Long proId;
    @Basic(optional = false)
    @Column(name = "PRO_FINI")
    private LocalDate proFini;
    @Basic(optional = false)
    @Column(name = "PRO_FFIN")
    private LocalDate proFfin;
    @Basic(optional = false)
    @Column(name = "PRO_TITULO")
    private String proTitulo;
    @Basic(optional = false)
    @Column(name = "PRO_ESTADO")
    private String proEstado;
    @Version
    @Basic(optional = false)
    @Column(name = "PRO_VERSION")
    private Long proVersion;
    @OneToMany(mappedBy = "proId", fetch = FetchType.LAZY)
    private List<TarTrabajadorevaluar> tarTrabajadorevaluarList;

    public TarProcesoevaluacion() {
    }

    public TarProcesoevaluacion(Long proId) {
        this.proId = proId;
    }

    public TarProcesoevaluacion(Long proId, LocalDate proFini, LocalDate proFfin, String proTitulo, String proEstado, Long proVersion) {
        this.proId = proId;
        this.proFini = proFini;
        this.proFfin = proFfin;
        this.proTitulo = proTitulo;
        this.proEstado = proEstado;
        this.proVersion = proVersion;
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

    public List<TarTrabajadorevaluar> getTarTrabajadorevaluarList() {
        return tarTrabajadorevaluarList;
    }

    public void setTarTrabajadorevaluarList(List<TarTrabajadorevaluar> tarTrabajadorevaluarList) {
        this.tarTrabajadorevaluarList = tarTrabajadorevaluarList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proId != null ? proId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarProcesoevaluacion)) {
            return false;
        }
        TarProcesoevaluacion other = (TarProcesoevaluacion) object;
        if ((this.proId == null && other.proId != null) || (this.proId != null && !this.proId.equals(other.proId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.evacomunaws.model.TarProcesoevaluacion[ proId=" + proId + " ]";
    }
    
}
