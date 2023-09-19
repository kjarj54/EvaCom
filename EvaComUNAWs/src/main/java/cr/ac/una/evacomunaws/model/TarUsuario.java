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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.QueryHint;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "TAR_USUARIO", schema = "EvaComUNA")
@NamedQueries({
    @NamedQuery(name = "TarUsuario.findAll", query = "SELECT t FROM TarUsuario t"),
    @NamedQuery(name = "TarUsuario.findByUsuId", query = "SELECT t FROM TarUsuario t WHERE t.usuId = :usuId"),
    @NamedQuery(name = "TarUsuario.findByUsuNombre", query = "SELECT t FROM TarUsuario t WHERE t.usuNombre = :usuNombre"),
    @NamedQuery(name = "TarUsuario.findByUsuApellido", query = "SELECT t FROM TarUsuario t WHERE t.usuApellido = :usuApellido"),
    @NamedQuery(name = "TarUsuario.findByUsuCedula", query = "SELECT t FROM TarUsuario t WHERE t.usuCedula = :usuCedula"),
    @NamedQuery(name = "TarUsuario.findByUsuCorreo", query = "SELECT t FROM TarUsuario t WHERE t.usuCorreo = :usuCorreo"),
    @NamedQuery(name = "TarUsuario.findByUsuTelefono", query = "SELECT t FROM TarUsuario t WHERE t.usuTelefono = :usuTelefono"),
    @NamedQuery(name = "TarUsuario.findByUsuCelular", query = "SELECT t FROM TarUsuario t WHERE t.usuCelular = :usuCelular"),
    @NamedQuery(name = "TarUsuario.findByUsuUsu", query = "SELECT t FROM TarUsuario t WHERE t.usuUsu = :usuUsu"),
    @NamedQuery(name = "TarUsuario.findByUsuClave", query = "SELECT t FROM TarUsuario t WHERE t.usuClave = :usuClave"),
    @NamedQuery(name = "TarUsuario.findByUsuClaveUsuario", query = "SELECT t FROM TarUsuario t WHERE t.usuUsu = :usuUsu and t.usuClave = :usuClave", hints = @QueryHint(name = "eclipselink.refresh", value = "true")),
    @NamedQuery(name = "TarUsuario.findByUsuTempclave", query = "SELECT t FROM TarUsuario t WHERE t.usuTempclave = :usuTempclave"),
    @NamedQuery(name = "TarUsuario.findByUsuActivo", query = "SELECT t FROM TarUsuario t WHERE t.usuActivo = :usuActivo"),
    @NamedQuery(name = "TarUsuario.findByUsuAdmin", query = "SELECT t FROM TarUsuario t WHERE t.usuAdmin = :usuAdmin"),
    @NamedQuery(name = "TarUsuario.findByUsuVersion", query = "SELECT t FROM TarUsuario t WHERE t.usuVersion = :usuVersion")})
public class TarUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "TAR_USUARIO_ID_GENERATOR", sequenceName = "evacomuna.TAR_USUARIO_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAR_USUARIO_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "USU_ID")
    private Long usuId;
    @Basic(optional = false)
    @Column(name = "USU_NOMBRE")
    private String usuNombre;
    @Basic(optional = false)
    @Column(name = "USU_APELLIDO")
    private String usuApellido;
    @Basic(optional = false)
    @Column(name = "USU_CEDULA")
    private String usuCedula;
    @Basic(optional = false)
    @Column(name = "USU_CORREO")
    private String usuCorreo;
    @Column(name = "USU_TELEFONO")
    private Long usuTelefono;
    @Column(name = "USU_CELULAR")
    private Long usuCelular;
    @Lob
    @Column(name = "USU_FOTO")
    private Serializable usuFoto;
    @Basic(optional = false)
    @Column(name = "USU_USU")
    private String usuUsu;
    @Basic(optional = false)
    @Column(name = "USU_CLAVE")
    private String usuClave;
    @Column(name = "USU_TEMPCLAVE")
    private String usuTempclave;
    @Basic(optional = false)
    @Column(name = "USU_ACTIVO")
    private String usuActivo;
    @Basic(optional = false)
    @Column(name = "USU_ADMIN")
    private String usuAdmin;
    @Version
    @Basic(optional = false)
    @Column(name = "USU_VERSION")
    private Long usuVersion;
    @OneToMany(mappedBy = "usuId", fetch = FetchType.LAZY)
    private List<TarEvaluador> tarEvaluadorList;
    @JoinColumn(name = "PUE_ID", referencedColumnName = "PUE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TarPuesto pueId;
    @OneToMany(mappedBy = "usuId", fetch = FetchType.LAZY)
    private List<TarTrabajadorevaluar> tarTrabajadorevaluarList;

    public TarUsuario() {
    }

    public TarUsuario(Long usuId) {
        this.usuId = usuId;
    }

    public TarUsuario(TarUsuarioDto tarUsuarioDto) {
        this.usuId = tarUsuarioDto.getUsuId();
        actualizar(tarUsuarioDto);
    }

    public void actualizar(TarUsuarioDto tarUsuarioDto) {
        this.usuNombre = tarUsuarioDto.getUsuNombre();
        this.usuApellido = tarUsuarioDto.getUsuApellido();
        this.usuCedula = tarUsuarioDto.getUsuCedula();
        this.usuCorreo = tarUsuarioDto.getUsuCorreo();
        this.usuClave = tarUsuarioDto.getUsuClave();
        this.usuActivo = tarUsuarioDto.getUsuActivo();
        this.usuAdmin = tarUsuarioDto.getUsuAdmin();
        this.usuCelular = tarUsuarioDto.getUsuCelular();
        this.usuTelefono = tarUsuarioDto.getUsuTelefono();
        this.usuVersion = tarUsuarioDto.getUsuVersion();
        this.usuFoto = tarUsuarioDto.getUsuFoto();
        this.usuTempclave = tarUsuarioDto.getUsuTempclave();
        this.usuUsu = tarUsuarioDto.getUsuUsu();

    }

    public Long getUsuId() {
        return usuId;
    }

    public void setUsuId(Long usuId) {
        this.usuId = usuId;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuApellido() {
        return usuApellido;
    }

    public void setUsuApellido(String usuApellido) {
        this.usuApellido = usuApellido;
    }

    public String getUsuCedula() {
        return usuCedula;
    }

    public void setUsuCedula(String usuCedula) {
        this.usuCedula = usuCedula;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public Long getUsuTelefono() {
        return usuTelefono;
    }

    public void setUsuTelefono(Long usuTelefono) {
        this.usuTelefono = usuTelefono;
    }

    public Long getUsuCelular() {
        return usuCelular;
    }

    public void setUsuCelular(Long usuCelular) {
        this.usuCelular = usuCelular;
    }

    public Serializable getUsuFoto() {
        return usuFoto;
    }

    public void setUsuFoto(Serializable usuFoto) {
        this.usuFoto = usuFoto;
    }

    public String getUsuClave() {
        return usuClave;
    }

    public void setUsuClave(String usuClave) {
        this.usuClave = usuClave;
    }

    public String getUsuUsu() {
        return usuUsu;
    }

    public void setUsuUsu(String usuUsu) {
        this.usuUsu = usuUsu;
    }

    public String getUsuTempclave() {
        return usuTempclave;
    }

    public void setUsuTempclave(String usuTempclave) {
        this.usuTempclave = usuTempclave;
    }

    public String getUsuActivo() {
        return usuActivo;
    }

    public void setUsuActivo(String usuActivo) {
        this.usuActivo = usuActivo;
    }

    public String getUsuAdmin() {
        return usuAdmin;
    }

    public void setUsuAdmin(String usuAdmin) {
        this.usuAdmin = usuAdmin;
    }

    public Long getUsuVersion() {
        return usuVersion;
    }

    public void setUsuVersion(Long usuVersion) {
        this.usuVersion = usuVersion;
    }

    public List<TarEvaluador> getTarEvaluadorList() {
        return tarEvaluadorList;
    }

    public void setTarEvaluadorList(List<TarEvaluador> tarEvaluadorList) {
        this.tarEvaluadorList = tarEvaluadorList;
    }

    public TarPuesto getPueId() {
        return pueId;
    }

    public void setPueId(TarPuesto pueId) {
        this.pueId = pueId;
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
        hash += (usuId != null ? usuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarUsuario)) {
            return false;
        }
        TarUsuario other = (TarUsuario) object;
        if ((this.usuId == null && other.usuId != null) || (this.usuId != null && !this.usuId.equals(other.usuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.evacomunaws.model.TarUsuario[ usuId=" + usuId + " ]";
    }

}
