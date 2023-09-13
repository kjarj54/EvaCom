/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.service;

import cr.ac.una.evacomunaws.model.TarCaracteristicaDto;
import cr.ac.una.evacomunaws.model.TarCompetencia;
import cr.ac.una.evacomunaws.model.TarCompetenciaDto;
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
public class TarCompetenciaService {
    private static final Logger LOG = Logger.getLogger(TarCompetenciaService.class.getName());
    @PersistenceContext(unitName = "EvaComUNAPU")
    private EntityManager em;
    
    public Respuesta guardarCompetencia(TarCompetenciaDto tarCompetenciaDto) {
        try {
            TarCompetencia competencia;
            if (tarCompetenciaDto.getComId() != null && tarCompetenciaDto.getComId() > 0) {
                competencia = em.find(TarCompetencia.class, tarCompetenciaDto.getComId());
                if (competencia == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la competencia a modificar.", "guardarCompetencia NoResultException");
                }
                competencia.actualizar(tarCompetenciaDto);
                competencia = em.merge(competencia);
            } else {
                competencia = new TarCompetencia(tarCompetenciaDto);
                em.persist(competencia);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Competencia", new TarCompetenciaDto(competencia));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la competencia.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la competencia.", "guardarCompetencia " + ex.getMessage());
        }
    }
    
    public Respuesta eliminarCompetencia(Long id) {
        try {
            TarCompetencia competencia;
            if (id != null && id > 0) {
                competencia = em.find(TarCompetencia.class, id);
                if (competencia == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la competencia a eliminar.", "eliminarCompetencia NoResultException");
                }
                em.remove(competencia);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar la competencia a eliminar.", "eliminarCompetencia NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar la competencia porque tiene relaciones con otros registros.", "eliminarCompetencia " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la competencia.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar la competencia.", "eliminarCompetencia " + ex.getMessage());
        }
    }
    
    public Respuesta getCompetencias() {
        try {
            Query qryCompetencia = em.createNamedQuery("TarCompetencia.findAll", TarCompetencia.class);
            List<TarCompetencia> competencias = qryCompetencia.getResultList();
            List<TarCompetenciaDto> competenciaDto = new ArrayList<>();
            for (TarCompetencia competencia : competencias) {
                competenciaDto.add(new TarCompetenciaDto(competencia));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Competencia", competenciaDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen competencias con los criterios ingresados.", "getCompetencias NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la competencias.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la competencias.", "getCompetencias " + ex.getMessage());
        }
    }
    
    public Respuesta getCompetencia(Long comId) {
        try {
            Query qryCompetencia = em.createNamedQuery("TarCompetencia.findByComId", TarCompetencia.class);
            qryCompetencia.setParameter("comId", comId);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Competencia", new TarCompetenciaDto((TarCompetencia) qryCompetencia.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una competencia con el código ingresado.", "getCompetencia NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el competencia.", "getCompetencia NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el competencia.", "getCompetencia " + ex.getMessage());
        }
    }
}
