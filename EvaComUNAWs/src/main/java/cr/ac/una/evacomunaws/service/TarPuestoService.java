/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.service;

import cr.ac.una.evacomunaws.model.TarCompetencia;
import cr.ac.una.evacomunaws.model.TarCompetenciaDto;
import cr.ac.una.evacomunaws.model.TarPuesto;
import cr.ac.una.evacomunaws.model.TarPuestoDto;
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
public class TarPuestoService {
    private static final Logger LOG = Logger.getLogger(TarPuestoService.class.getName());
    @PersistenceContext(unitName = "EvaComUNAPU")
    private EntityManager em;
    
    public Respuesta guardarPuesto(TarPuestoDto tarPuestoDto) {
        try {
            TarPuesto puesto;
            if (tarPuestoDto.getPueId()!= null && tarPuestoDto.getPueId()> 0) {
                puesto = em.find(TarPuesto.class, tarPuestoDto.getPueId());
                if (puesto == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el puesto a modificar.", "guardarPuesto NoResultException");
                }
                puesto.actualizar(tarPuestoDto);
                for(TarCompetenciaDto com : tarPuestoDto.getTarCompetenciaListEliminados()){
                    puesto.getTarCompetenciaList().remove(new TarCompetencia(com.getComId()));
                }
                if(!tarPuestoDto.getTarCompetenciaList().isEmpty()){
                    for(TarCompetenciaDto com : tarPuestoDto.getTarCompetenciaList()){
                        if(com.getModificado()){
                            TarCompetencia competencia = em.find(TarCompetencia.class, com.getComId());
                            competencia.getTarPuestoList().add(puesto);
                            puesto.getTarCompetenciaList().add(competencia);
                        }
                    }
                }
                puesto = em.merge(puesto);
            } else {
                puesto = new TarPuesto(tarPuestoDto);
                em.persist(puesto);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Puesto", new TarPuestoDto(puesto));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el puesto.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el puesto.", "guardarPuesto " + ex.getMessage());
        }
    }

    public Respuesta eliminarPuesto(Long id) {
        try {
            TarPuesto procesoevaluacion;
            if (id != null && id > 0) {
                procesoevaluacion = em.find(TarPuesto.class, id);
                if (procesoevaluacion == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el puesto a eliminar.", "eliminarPuesto NoResultException");
                }
                em.remove(procesoevaluacion);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el puesto a eliminar.", "eliminarPuesto NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el puesto porque tiene relaciones con otros registros.", "eliminarPuesto " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el puesto.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el puesto.", "eliminarPuesto " + ex.getMessage());
        }
    }

    public Respuesta getPuestos() {
        try {
            Query qryPuesto = em.createNamedQuery("TarPuesto.findAll", TarPuesto.class);
            List<TarPuesto> puestos = qryPuesto.getResultList();
            List<TarPuestoDto> puestoDto = new ArrayList<>();
            for (TarPuesto puesto : puestos) {
                puestoDto.add(new TarPuestoDto(puesto));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Puesto", puestoDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen el puestos con los criterios ingresados.", "getPuestos NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el puestos.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el puestos.", "getPuestos " + ex.getMessage());
        }
    }

    public Respuesta getPuesto(Long pueId) {
        try {
            Query qryProcesoEvaluacion = em.createNamedQuery("TarProcesoevaluacion.findByPueId", TarPuesto.class);
            qryProcesoEvaluacion.setParameter("pueId", pueId);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Puesto", new TarPuestoDto((TarPuesto) qryProcesoEvaluacion.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe el puesto con el código ingresado.", "getPuesto NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el puesto.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el puesto.", "getPuesto NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el puesto.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el puesto.", "getPuesto " + ex.getMessage());
        }
    }
    
}
