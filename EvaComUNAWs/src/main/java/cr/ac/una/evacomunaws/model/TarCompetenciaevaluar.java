/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.model;

import java.io.Serializable;
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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "TAR_COMPETENCIAEVALUAR")
@NamedQueries({
    @NamedQuery(name = "TarCompetenciaevaluar.findAll", query = "SELECT t FROM TarCompetenciaevaluar t"),
    @NamedQuery(name = "TarCompetenciaevaluar.findByCoeId", query = "SELECT t FROM TarCompetenciaevaluar t WHERE t.coeId = :coeId"),
    @NamedQuery(name = "TarCompetenciaevaluar.findByCoeCalificacion", query = "SELECT t FROM TarCompetenciaevaluar t WHERE t.coeCalificacion = :coeCalificacion"),
    @NamedQuery(name = "TarCompetenciaevaluar.findByCoeVersion", query = "SELECT t FROM TarCompetenciaevaluar t WHERE t.coeVersion = :coeVersion")})
public class TarCompetenciaevaluar implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TAR_COMPETENCIAEVALUAR_ID_GENERATOR", sequenceName = "evacomuna.TAR_COMPETENCIAEVALUAR_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAR_COMPETENCIAEVALUAR_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "COE_ID")
    private Long coeId;
    @Column(name = "COE_CALIFICACION")
    private String coeCalificacion;
    @Version
    @Basic(optional = false)
    @Column(name = "COE_VERSION")
    private Long coeVersion;
    @JoinColumn(name = "COM_ID", referencedColumnName = "COM_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TarCompetencia comId;
    @JoinColumn(name = "EVALU_ID", referencedColumnName = "EVALU_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TarEvaluador evaluId;

    public TarCompetenciaevaluar() {
    }

    public TarCompetenciaevaluar(Long coeId) {
        this.coeId = coeId;
    }

    public TarCompetenciaevaluar(TarCompetenciaevaluarDto tarCompetenciaevaluarDto) {
        this.coeId = tarCompetenciaevaluarDto.getCoeId();
        actualizar(tarCompetenciaevaluarDto);
    }
    
    public void actualizar(TarCompetenciaevaluarDto tarCompetenciaevaluarDto){
        this.coeCalificacion = tarCompetenciaevaluarDto.getCoeCalificacion();
        this.coeVersion = tarCompetenciaevaluarDto.getCoeVersion();
    }

    public Long getCoeId() {
        return coeId;
    }

    public void setCoeId(Long coeId) {
        this.coeId = coeId;
    }

    public String getCoeCalificacion() {
        return coeCalificacion;
    }

    public void setCoeCalificacion(String coeCalificacion) {
        this.coeCalificacion = coeCalificacion;
    }

    public Long getCoeVersion() {
        return coeVersion;
    }

    public void setCoeVersion(Long coeVersion) {
        this.coeVersion = coeVersion;
    }

    public TarCompetencia getComId() {
        return comId;
    }

    public void setComId(TarCompetencia comId) {
        this.comId = comId;
    }

    public TarEvaluador getEvaluId() {
        return evaluId;
    }

    public void setEvaluId(TarEvaluador evaluId) {
        this.evaluId = evaluId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coeId != null ? coeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarCompetenciaevaluar)) {
            return false;
        }
        TarCompetenciaevaluar other = (TarCompetenciaevaluar) object;
        if ((this.coeId == null && other.coeId != null) || (this.coeId != null && !this.coeId.equals(other.coeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.evacomunaws.model.TarCompetenciaevaluar[ coeId=" + coeId + " ]";
    }
    
}
