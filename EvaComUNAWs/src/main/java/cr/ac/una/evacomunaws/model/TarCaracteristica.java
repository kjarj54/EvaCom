/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.model;

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
import java.io.Serializable;


/**
 *
 * @author kevin
 */
@Entity
@Table(name = "TAR_CARACTERISTICA", schema="EvaComUNA")
@NamedQueries({
    @NamedQuery(name = "TarCaracteristica.findAll", query = "SELECT t FROM TarCaracteristica t"),
    @NamedQuery(name = "TarCaracteristica.findByCarId", query = "SELECT t FROM TarCaracteristica t WHERE t.carId = :carId"),
    @NamedQuery(name = "TarCaracteristica.findByCarDescripcion", query = "SELECT t FROM TarCaracteristica t WHERE t.carDescripcion = :carDescripcion"),
    @NamedQuery(name = "TarCaracteristica.findByCarVersion", query = "SELECT t FROM TarCaracteristica t WHERE t.carVersion = :carVersion")})
public class TarCaracteristica implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TAR_CARACTERISTICA_ID_GENERATOR", sequenceName = "evacomuna.TAR_CARACTERISTICA_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAR_CARACTERISTICA_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "CAR_ID")
    private Long carId;
    @Basic(optional = false)
    @Column(name = "CAR_DESCRIPCION")
    private String carDescripcion;
    @Version
    @Basic(optional = false)
    @Column(name = "CAR_VERSION")
    private Long carVersion;
    @JoinColumn(name = "COM_ID", referencedColumnName = "COM_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TarCompetencia comId;

    public TarCaracteristica() {
    }

    public TarCaracteristica(Long carId) {
        this.carId = carId;
    }

    public TarCaracteristica(Long carId, String carDescripcion, Long carVersion) {
        this.carId = carId;
        this.carDescripcion = carDescripcion;
        this.carVersion = carVersion;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarDescripcion() {
        return carDescripcion;
    }

    public void setCarDescripcion(String carDescripcion) {
        this.carDescripcion = carDescripcion;
    }

    public Long getCarVersion() {
        return carVersion;
    }

    public void setCarVersion(Long carVersion) {
        this.carVersion = carVersion;
    }

    public TarCompetencia getComId() {
        return comId;
    }

    public void setComId(TarCompetencia comId) {
        this.comId = comId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carId != null ? carId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarCaracteristica)) {
            return false;
        }
        TarCaracteristica other = (TarCaracteristica) object;
        if ((this.carId == null && other.carId != null) || (this.carId != null && !this.carId.equals(other.carId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.evacomunaws.model.TarCaracteristica[ carId=" + carId + " ]";
    }
    
}
