/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.service;

import cr.ac.una.evacomunaws.model.TarProcesoevaluacion;
import cr.ac.una.evacomunaws.model.TarProcesoevaluacionDto;
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
public class TarProcesoevaluacionService {

    private static final Logger LOG = Logger.getLogger(TarProcesoevaluacionService.class.getName());
    @PersistenceContext(unitName = "EvaComUNAPU")
    private EntityManager em;

    public Respuesta guardarProcesoEvaluacion(TarProcesoevaluacionDto tarProcesoevaluacionDto) {
        try {
            TarProcesoevaluacion procesoevaluacion;
            if (tarProcesoevaluacionDto.getProId() != null && tarProcesoevaluacionDto.getProId() > 0) {
                procesoevaluacion = em.find(TarProcesoevaluacion.class, tarProcesoevaluacionDto.getProId());
                if (procesoevaluacion == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el procesoevaluar a modificar.", "guardarProcesoEvaluacion NoResultException");
                }
                procesoevaluacion.actualizar(tarProcesoevaluacionDto);
                for (TarTrabajadorevaluarDto tarTrabajadorevaluarDto : tarProcesoevaluacionDto.getTarTrabajadorevaluarListEliminados()) {
                    procesoevaluacion.getTarTrabajadorevaluarList().remove(new TarTrabajadorevaluar(tarTrabajadorevaluarDto.getTraId()));
                }
                if (!tarProcesoevaluacionDto.getTarTrabajadorevaluarList().isEmpty()) {
                    for (TarTrabajadorevaluarDto tarTrabajadorevaluarDto : tarProcesoevaluacionDto.getTarTrabajadorevaluarList()) {
                        TarTrabajadorevaluar tarTrabajadorevaluar = em.find(TarTrabajadorevaluar.class, tarTrabajadorevaluarDto.getTraId());
                        procesoevaluacion.getTarTrabajadorevaluarList().add(tarTrabajadorevaluar);
                    }
                }
                procesoevaluacion = em.merge(procesoevaluacion);
            } else {
                procesoevaluacion = new TarProcesoevaluacion(tarProcesoevaluacionDto);
                em.persist(procesoevaluacion);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TarProcesoevaluacion", new TarProcesoevaluacionDto(procesoevaluacion));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el procesoevaluar.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el procesoevaluar.", "guardarProcesoEvaluacion " + ex.getMessage());
        }
    }

    public Respuesta eliminarProcesoEvaluacion(Long id) {
        try {
            TarProcesoevaluacion procesoevaluacion;
            if (id != null && id > 0) {
                procesoevaluacion = em.find(TarProcesoevaluacion.class, id);
                if (procesoevaluacion == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el procesoevaluar a eliminar.", "eliminarProcesoEvaluacion NoResultException");
                }
                em.remove(procesoevaluacion);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el procesoevaluar a eliminar.", "eliminarProcesoEvaluacion NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el procesoevaluar porque tiene relaciones con otros registros.", "eliminarProcesoEvaluacion " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el procesoevaluar.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el procesoevaluar.", "eliminarProcesoEvaluacion " + ex.getMessage());
        }
    }

    public Respuesta getProcesosEvaluacion() {
        try {
            Query qryProcesoEvaluacion = em.createNamedQuery("TarProcesoevaluacion.findAll", TarProcesoevaluacion.class);
            List<TarProcesoevaluacion> tarProcesoevaluacionsList = qryProcesoEvaluacion.getResultList();
            List<TarProcesoevaluacionDto> tarProcesoevaluacionDtosList = new ArrayList<>();
            for (TarProcesoevaluacion tarProcesoevaluacion : tarProcesoevaluacionsList) {
                TarProcesoevaluacionDto tarProcesoevaluacionDto = new TarProcesoevaluacionDto(tarProcesoevaluacion);
                if (!tarProcesoevaluacion.getTarTrabajadorevaluarList().isEmpty()) {
                    for (TarTrabajadorevaluar tarTrabajadorevaluar : tarProcesoevaluacion.getTarTrabajadorevaluarList()) {
                        tarProcesoevaluacionDto.getTarTrabajadorevaluarList().add(new TarTrabajadorevaluarDto(tarTrabajadorevaluar));
                    }
                }
                tarProcesoevaluacionDtosList.add(tarProcesoevaluacionDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TarProcesoevaluaciones", tarProcesoevaluacionDtosList);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen el procesoevaluar con los criterios ingresados.", "getProcesosEvaluacion NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el procesoevaluar.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el procesoevaluar.", "getProcesosEvaluacion " + ex.getMessage());
        }
    }

    public Respuesta getProcesoEvaluacion(Long proId) {
        try {
            Query qryProcesoEvaluacion = em.createNamedQuery("TarProcesoevaluacion.findByProId", TarProcesoevaluacion.class);
            qryProcesoEvaluacion.setParameter("proId", proId);
            TarProcesoevaluacion tarProcesoevaluacion = (TarProcesoevaluacion) qryProcesoEvaluacion.getSingleResult();
            TarProcesoevaluacionDto tarProcesoevaluacionDto = new TarProcesoevaluacionDto(tarProcesoevaluacion);
            if (!tarProcesoevaluacion.getTarTrabajadorevaluarList().isEmpty()) {
                for (TarTrabajadorevaluar tarTrabajadorevaluar : tarProcesoevaluacion.getTarTrabajadorevaluarList()) {
                    tarProcesoevaluacionDto.getTarTrabajadorevaluarList().add(new TarTrabajadorevaluarDto(tarTrabajadorevaluar));
                }
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TarProcesoevaluacion", tarProcesoevaluacionDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe el procesoevaluar con el código ingresado.", "getProcesoEvaluacion NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el procesoevaluar.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el procesoevaluar.", "getProcesoEvaluacion NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el procesoevaluar.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el procesoevaluar.", "getProcesoEvaluacion " + ex.getMessage());
        }
    }
}
