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
@Table(name = "TAR_TRABAJADOREVALUAR",schema="EvaComUNA")
@NamedQueries({
    @NamedQuery(name = "TarTrabajadorevaluar.findAll", query = "SELECT t FROM TarTrabajadorevaluar t"),
    @NamedQuery(name = "TarTrabajadorevaluar.findByTraId", query = "SELECT t FROM TarTrabajadorevaluar t WHERE t.traId = :traId"),
    @NamedQuery(name = "TarTrabajadorevaluar.findByTraResultado", query = "SELECT t FROM TarTrabajadorevaluar t WHERE t.traResultado = :traResultado"),
    @NamedQuery(name = "TarTrabajadorevaluar.findByTraVersion", query = "SELECT t FROM TarTrabajadorevaluar t WHERE t.traVersion = :traVersion")})
public class TarTrabajadorevaluar implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TAR_TRABAJADOREVALUAR_ID_GENERATOR", sequenceName = "evacomuna.TAR_TRABAJADOREVALUAR_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAR_TRABAJADOREVALUAR_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "TRA_ID")
    private Long traId;
    @Column(name = "TRA_RESULTADO")
    private String traResultado;
    @Version
    @Basic(optional = false)
    @Column(name = "TRA_VERSION")
    private Long traVersion;
    @OneToMany(mappedBy = "traId", fetch = FetchType.LAZY)
    private List<TarEvaluador> tarEvaluadorList;
    @JoinColumn(name = "PRO_ID", referencedColumnName = "PRO_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TarProcesoevaluacion proId;
    @JoinColumn(name = "USU_ID", referencedColumnName = "USU_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TarUsuario usuId;

    public TarTrabajadorevaluar() {
    }

    public TarTrabajadorevaluar(Long traId) {
        this.traId = traId;
    }

    public TarTrabajadorevaluar(TarTrabajadorevaluarDto tarTrabajadorevaluarDto) {
        this.traId = tarTrabajadorevaluarDto.getTraId();
        actualizar(tarTrabajadorevaluarDto);
    }
    
    public void actualizar(TarTrabajadorevaluarDto tarTrabajadorevaluarDto){
        this.traResultado = tarTrabajadorevaluarDto.getTraResultado();
        this.traVersion = tarTrabajadorevaluarDto.getTraVersion();
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

    public List<TarEvaluador> getTarEvaluadorList() {
        return tarEvaluadorList;
    }

    public void setTarEvaluadorList(List<TarEvaluador> tarEvaluadorList) {
        this.tarEvaluadorList = tarEvaluadorList;
    }

    public TarProcesoevaluacion getProId() {
        return proId;
    }

    public void setProId(TarProcesoevaluacion proId) {
        this.proId = proId;
    }

    public TarUsuario getUsuId() {
        return usuId;
    }

    public void setUsuId(TarUsuario usuId) {
        this.usuId = usuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (traId != null ? traId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarTrabajadorevaluar)) {
            return false;
        }
        TarTrabajadorevaluar other = (TarTrabajadorevaluar) object;
        if ((this.traId == null && other.traId != null) || (this.traId != null && !this.traId.equals(other.traId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.evacomunaws.model.TarTrabajadorevaluar[ traId=" + traId + " ]";
    }
    
}
