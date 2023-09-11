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
import jakarta.persistence.JoinTable;
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
@Table(name = "TAR_PUESTO",schema="EvaComUNA")
@NamedQueries({
    @NamedQuery(name = "TarPuesto.findAll", query = "SELECT t FROM TarPuesto t"),
    @NamedQuery(name = "TarPuesto.findByPueId", query = "SELECT t FROM TarPuesto t WHERE t.pueId = :pueId"),
    @NamedQuery(name = "TarPuesto.findByPueNombre", query = "SELECT t FROM TarPuesto t WHERE t.pueNombre = :pueNombre"),
    @NamedQuery(name = "TarPuesto.findByPueEstado", query = "SELECT t FROM TarPuesto t WHERE t.pueEstado = :pueEstado"),
    @NamedQuery(name = "TarPuesto.findByPueVersion", query = "SELECT t FROM TarPuesto t WHERE t.pueVersion = :pueVersion")})
public class TarPuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TAR_PUESTO_ID_GENERATOR", sequenceName = "evacomuna.TAR_PUESTO_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAR_PUESTO_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "PUE_ID")
    private Long pueId;
    @Basic(optional = false)
    @Column(name = "PUE_NOMBRE")
    private String pueNombre;
    @Basic(optional = false)
    @Column(name = "PUE_ESTADO")
    private String pueEstado;
    @Version
    @Basic(optional = false)
    @Column(name = "PUE_VERSION")
    private Long pueVersion;
    @JoinTable(name = "TAR_PUESTOCOMPETENCIA", joinColumns = {
        @JoinColumn(name = "PUE_ID", referencedColumnName = "PUE_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "COM_ID", referencedColumnName = "COM_ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<TarCompetencia> tarCompetenciaList;
    @OneToMany(mappedBy = "pueId", fetch = FetchType.LAZY)
    private List<TarUsuario> tarUsuarioList;

    public TarPuesto() {
    }

    public TarPuesto(Long pueId) {
        this.pueId = pueId;
    }

    public TarPuesto(TarPuestoDto tarPuestoDto ) {
        this.pueId = tarPuestoDto.getPueId();
        actaulizar(tarPuestoDto);
        
    }
    
    public void actaulizar(TarPuestoDto tarPuestoDto){
        this.pueNombre = tarPuestoDto.getPueNombre();
        this.pueEstado = tarPuestoDto.getPueEstado();
        this.pueVersion = tarPuestoDto.getPueVersion();
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

    public List<TarCompetencia> getTarCompetenciaList() {
        return tarCompetenciaList;
    }

    public void setTarCompetenciaList(List<TarCompetencia> tarCompetenciaList) {
        this.tarCompetenciaList = tarCompetenciaList;
    }

    public List<TarUsuario> getTarUsuarioList() {
        return tarUsuarioList;
    }

    public void setTarUsuarioList(List<TarUsuario> tarUsuarioList) {
        this.tarUsuarioList = tarUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pueId != null ? pueId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarPuesto)) {
            return false;
        }
        TarPuesto other = (TarPuesto) object;
        if ((this.pueId == null && other.pueId != null) || (this.pueId != null && !this.pueId.equals(other.pueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.evacomunaws.model.TarPuesto[ pueId=" + pueId + " ]";
    }
    
}
