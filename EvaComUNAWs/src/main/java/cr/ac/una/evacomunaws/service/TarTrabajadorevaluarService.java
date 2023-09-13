/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.service;

import cr.ac.una.evacomunaws.model.TarTrabajadorevaluar;
import cr.ac.una.evacomunaws.model.TarTrabajadorevaluarDto;
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
public class TarTrabajadorevaluarService {
    private static final Logger LOG = Logger.getLogger(TarTrabajadorevaluarService.class.getName());
    @PersistenceContext(unitName = "EvaComUNAPU")
    private EntityManager em;
    
    public Respuesta guardarTrabajadorEvaluar(TarTrabajadorevaluarDto tarTrabajadorevaluarDto) {
        try {
            TarTrabajadorevaluar trabajadorevaluar;
            if (tarTrabajadorevaluarDto.getTraId()!= null && tarTrabajadorevaluarDto.getTraId()> 0) {
                trabajadorevaluar = em.find(TarTrabajadorevaluar.class, tarTrabajadorevaluarDto.getTraId());
                if (trabajadorevaluar == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el trabajadorevaluar a modificar.", "guardarTrabajadorEvaluar NoResultException");
                }
                trabajadorevaluar.actualizar(tarTrabajadorevaluarDto);
                trabajadorevaluar = em.merge(trabajadorevaluar);
            } else {
                trabajadorevaluar = new TarTrabajadorevaluar(tarTrabajadorevaluarDto);
                em.persist(trabajadorevaluar);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TrabajadorEvaluar", new TarTrabajadorevaluarDto(trabajadorevaluar));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el trabajadorevaluar.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el trabajadorevaluar.", "guardarTrabajadorEvaluar " + ex.getMessage());
        }
    }

    public Respuesta eliminarTrabajadorEvaluar(Long id) {
        try {
            TarTrabajadorevaluar trabajadorevaluar;
            if (id != null && id > 0) {
                trabajadorevaluar = em.find(TarTrabajadorevaluar.class, id);
                if (trabajadorevaluar == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el trabajadorevaluar a eliminar.", "eliminarTrabajadorEvaluar NoResultException");
                }
                em.remove(trabajadorevaluar);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el trabajadorevaluar a eliminar.", "eliminarTrabajadorEvaluar NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el trabajadorevaluar porque tiene relaciones con otros registros.", "eliminarTrabajadorEvaluar " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el trabajadorevaluar.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el trabajadorevaluar.", "eliminarTrabajadorEvaluar " + ex.getMessage());
        }
    }

    public Respuesta getTrabajadoresEvaluar() {
        try {
            Query qryProcesoEvaluacion = em.createNamedQuery("TarTrabajadorevaluar.findAll", TarTrabajadorevaluar.class);
            List<TarTrabajadorevaluar> trabajadorevaluars = qryProcesoEvaluacion.getResultList();
            List<TarTrabajadorevaluarDto> tarTrabajadorevaluarDto = new ArrayList<>();
            for (TarTrabajadorevaluar trabajadorevaluar : trabajadorevaluars) {
                tarTrabajadorevaluarDto.add(new TarTrabajadorevaluarDto(trabajadorevaluar));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TrabajadorEvaluares", tarTrabajadorevaluarDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen el trabajadorevaluar con los criterios ingresados.", "getTrabajadoresEvaluar NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el trabajadorevaluar.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el trabajadorevaluar.", "getTrabajadoresEvaluar " + ex.getMessage());
        }
    }

    public Respuesta getTrabajadorEvaluar(Long traId) {
        try {
            Query qryTrabajadorEvaluar = em.createNamedQuery("TarTrabajadorevaluar.findByTraId", TarTrabajadorevaluar.class);
            qryTrabajadorEvaluar.setParameter("traId", traId);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TrabajadorEvaluar", new TarTrabajadorevaluarDto((TarTrabajadorevaluar) qryTrabajadorEvaluar.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe el trabajadorevaluar con el código ingresado.", "getTrabajadorEvaluar NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el trabajadorevaluar.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el trabajadorevaluar.", "getTrabajadorEvaluar NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el trabajadorevaluar.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el trabajadorevaluar.", "getTrabajadorEvaluar " + ex.getMessage());
        }
    }
    
}
