/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.model;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "TAR_EVALUADOR",schema="EvaComUNA")
@NamedQueries({
    @NamedQuery(name = "TarEvaluador.findAll", query = "SELECT t FROM TarEvaluador t"),
    @NamedQuery(name = "TarEvaluador.findByEvaluId", query = "SELECT t FROM TarEvaluador t WHERE t.evaluId = :evaluId"),
    @NamedQuery(name = "TarEvaluador.findByEvaluRetroalimentacion", query = "SELECT t FROM TarEvaluador t WHERE t.evaluRetroalimentacion = :evaluRetroalimentacion"),
    @NamedQuery(name = "TarEvaluador.findByEvaluVersion", query = "SELECT t FROM TarEvaluador t WHERE t.evaluVersion = :evaluVersion")})
public class TarEvaluador implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TAR_EVALUADOR_ID_GENERATOR", sequenceName = "evacomuna.TAR_EVALUADOR_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAR_EVALUADOR_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "EVALU_ID")
    private Long evaluId;
    @Column(name = "EVALU_RETROALIMENTACION")
    private String evaluRetroalimentacion;
    @Version
    @Basic(optional = false)
    @Column(name = "EVALU_VERSION")
    private Long evaluVersion;
    @JoinColumn(name = "TRA_ID", referencedColumnName = "TRA_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TarTrabajadorevaluar traId;
    @JoinColumn(name = "USU_ID", referencedColumnName = "USU_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TarUsuario usuId;
    @OneToMany(mappedBy = "evaluId", fetch = FetchType.LAZY)
    private List<TarCompetenciaevaluar> tarCompetenciaevaluarList;

    public TarEvaluador() {
    }

    public TarEvaluador(Long evaluId) {
        this.evaluId = evaluId;
    }

    public TarEvaluador(TarEvaluadorDto tarEvaluadorDto) {
        this.evaluId = tarEvaluadorDto.getEvaluId();
        actualizar(tarEvaluadorDto);
    }
    public void actualizar(TarEvaluadorDto tarEvaluadorDto){
        this.evaluRetroalimentacion = tarEvaluadorDto.getEvaluRetroalimentacion();
        this.evaluVersion = tarEvaluadorDto.getEvaluVersion();
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

    public TarTrabajadorevaluar getTraId() {
        return traId;
    }

    public void setTraId(TarTrabajadorevaluar traId) {
        this.traId = traId;
    }

    public TarUsuario getUsuId() {
        return usuId;
    }

    public void setUsuId(TarUsuario usuId) {
        this.usuId = usuId;
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
        hash += (evaluId != null ? evaluId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarEvaluador)) {
            return false;
        }
        TarEvaluador other = (TarEvaluador) object;
        if ((this.evaluId == null && other.evaluId != null) || (this.evaluId != null && !this.evaluId.equals(other.evaluId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.evacomunaws.model.TarEvaluador[ evaluId=" + evaluId + " ]";
    }
    
}
