/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
@Table(name = "TAR_PARAMETROS",schema="EvaComUNA")
@NamedQueries({
    @NamedQuery(name = "TarParametros.findAll", query = "SELECT t FROM TarParametros t"),
    @NamedQuery(name = "TarParametros.findByParId", query = "SELECT t FROM TarParametros t WHERE t.parId = :parId"),
    @NamedQuery(name = "TarParametros.findByParNombre", query = "SELECT t FROM TarParametros t WHERE t.parNombre = :parNombre"),
    @NamedQuery(name = "TarParametros.findByParEmail", query = "SELECT t FROM TarParametros t WHERE t.parEmail = :parEmail"),
    @NamedQuery(name = "TarParametros.findByParClave", query = "SELECT t FROM TarParametros t WHERE t.parClave = :parClave"),
    @NamedQuery(name = "TarParametros.findByParDescripcion", query = "SELECT t FROM TarParametros t WHERE t.parDescripcion = :parDescripcion"),
    @NamedQuery(name = "TarParametros.findByParVersion", query = "SELECT t FROM TarParametros t WHERE t.parVersion = :parVersion")})
public class TarParametros implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TAR_PARAMETROS_ID_GENERATOR", sequenceName = "evacomuna.TAR_PARAMETROS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAR_PARAMETROS_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "PAR_ID")
    private Long parId;
    @Column(name = "PAR_NOMBRE")
    private String parNombre;
    @Column(name = "PAR_EMAIL")
    private String parEmail;
    @Column(name = "PAR_CLAVE")
    private String parClave;
    @Lob
    @Column(name = "PAR_HTML")
    private Serializable parHtml;
    @Lob
    @Column(name = "PAR_LOGO")
    private Serializable parLogo;
    @Column(name = "PAR_DESCRIPCION")
    private String parDescripcion;
    @Version
    @Basic(optional = false)
    @Column(name = "PAR_VERSION")
    private Long parVersion;

    public TarParametros() {
    }

    public TarParametros(Long parId) {
        this.parId = parId;
    }

    public TarParametros(TarParametrosDto tarParametrosDto) {
        this.parId = parId;
        actualizar(tarParametrosDto);
    }
    public void actualizar(TarParametrosDto tarParametrosDto ){
        this.parClave = tarParametrosDto.getParClave();
        this.parDescripcion = tarParametrosDto.getParDescripcion();
        this.parEmail = tarParametrosDto.getParEmail();
        this.parHtml = tarParametrosDto.getParHtml();
        this.parLogo = tarParametrosDto.getParLogo();
        this.parNombre = tarParametrosDto.getParNombre();
        this.parVersion = tarParametrosDto.getParVersion();
    }
    public Long getParId() {
        return parId;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public String getParNombre() {
        return parNombre;
    }

    public void setParNombre(String parNombre) {
        this.parNombre = parNombre;
    }

    public String getParEmail() {
        return parEmail;
    }

    public void setParEmail(String parEmail) {
        this.parEmail = parEmail;
    }

    public String getParClave() {
        return parClave;
    }

    public void setParClave(String parClave) {
        this.parClave = parClave;
    }

    public Serializable getParHtml() {
        return parHtml;
    }

    public void setParHtml(Serializable parHtml) {
        this.parHtml = parHtml;
    }

    public Serializable getParLogo() {
        return parLogo;
    }

    public void setParLogo(Serializable parLogo) {
        this.parLogo = parLogo;
    }

    public String getParDescripcion() {
        return parDescripcion;
    }

    public void setParDescripcion(String parDescripcion) {
        this.parDescripcion = parDescripcion;
    }

    public Long getParVersion() {
        return parVersion;
    }

    public void setParVersion(Long parVersion) {
        this.parVersion = parVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parId != null ? parId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarParametros)) {
            return false;
        }
        TarParametros other = (TarParametros) object;
        if ((this.parId == null && other.parId != null) || (this.parId != null && !this.parId.equals(other.parId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.evacomunaws.model.TarParametros[ parId=" + parId + " ]";
    }
    
}
