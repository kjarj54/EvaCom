/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.service;

import cr.ac.una.evacomunaws.model.TarUsuario;
import cr.ac.una.evacomunaws.model.TarUsuarioDto;
import cr.ac.una.evacomunaws.util.CodigoRespuesta;
import cr.ac.una.evacomunaws.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
@Stateless
@LocalBean
public class TarUsuarioService {

    private static final Logger LOG = Logger.getLogger(TarUsuarioService.class.getName());
    @PersistenceContext(unitName = "EvaComUNAPU")
    private EntityManager em;
    
    public Respuesta validarUsuario(String usuario, String clave) {
        try {
            Query qryActividad = em.createNamedQuery("TarUsuario.", TarUsuario.class);
            qryActividad.setParameter("usuario", usuario);
            qryActividad.setParameter("clave", clave);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TarUsuario", new TarUsuarioDto((TarUsuario) qryActividad.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un usuario con las credenciales ingresadas.", "validarUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario " + ex.getMessage());
        }
    }
    
    
    
    public Respuesta guardarUsuario(TarUsuarioDto tarUsuarioDto) {
        try {
            TarUsuario usuario;
            if (tarUsuarioDto.getUsuId()!= null && tarUsuarioDto.getUsuId() > 0) {
                usuario = em.find(TarUsuario.class, tarUsuarioDto.getUsuId());
                if (usuario == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el usuario a modificar.", "guardarCaracteristica NoResultException");
                }
                usuario.actualizar(tarUsuarioDto);
                usuario = em.merge(usuario);
            } else {
                usuario = new TarUsuario(tarUsuarioDto);
                em.persist(usuario);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TarUsuario", new TarUsuarioDto(usuario));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el usuario.", "guardarUsuario " + ex.getMessage());
        }
    }
    
    public Respuesta eliminarUsuario(Long id) {
        try {
            TarUsuario caracteristica;
            if (id != null && id > 0) {
                caracteristica = em.find(TarUsuario.class, id);
                if (caracteristica == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el usuario a eliminar.", "eliminarUsuario NoResultException");
                }
                em.remove(caracteristica);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el usuario a eliminar.", "eliminarUsuario NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el usuario porque tiene relaciones con otros registros.", "eliminarUsuario " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la caracteristica.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el usuario.", "eliminarUsuario " + ex.getMessage());
        }
    }
    
    public Respuesta getUsuarios() {
        try {
            Query qryUsuario = em.createNamedQuery("", TarUsuario.class);
            List<TarUsuario> usuarios = qryUsuario.getResultList();
            List<TarUsuarioDto> usuarioDto = new ArrayList<>();
            for (TarUsuario usuario : usuarios) {
                usuarioDto.add(new TarUsuarioDto(usuario));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", usuarioDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen usuarios con los criterios ingresados.", "getUsuarios NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar usuarios.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar usuarios.", "getUsuarios " + ex.getMessage());
        }
    }
    
    public Respuesta getUsuario(Long id) {
        try {
            Query qryCaracteristica = em.createNamedQuery("TarUsuario.findBy", TarUsuario.class);
            qryCaracteristica.setParameter("id", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Empleado", new TarUsuarioDto((TarUsuario) qryCaracteristica.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un usuario con el código ingresado.", "getUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "getUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "getUsuario " + ex.getMessage());
        }
    }
    
    
}
