/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.service;

import cr.ac.una.evacomunaws.model.TarCompetencia;
import cr.ac.una.evacomunaws.model.TarCompetenciaDto;
import cr.ac.una.evacomunaws.util.Respuesta;
import cr.ac.una.evacomunaws.model.TarCompetenciaevaluar;
import cr.ac.una.evacomunaws.model.TarCompetenciaevaluarDto;
import cr.ac.una.evacomunaws.model.TarEvaluador;
import cr.ac.una.evacomunaws.model.TarEvaluadorDto;
import cr.ac.una.evacomunaws.util.CodigoRespuesta;
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
public class TarCompetenciaevaluarService {

    private static final Logger LOG = Logger.getLogger(TarCompetenciaevaluarService.class.getName());
    @PersistenceContext(unitName = "EvaComUNAPU")
    private EntityManager em;

    public Respuesta guardarCompetenciaEvaluar(TarCompetenciaevaluarDto tarCompetenicaevaluarDto) {
        try {
            TarCompetenciaevaluar competenciaEvaluar;
            if (tarCompetenicaevaluarDto.getCoeId() != null && tarCompetenicaevaluarDto.getCoeId() > 0) {
                competenciaEvaluar = em.find(TarCompetenciaevaluar.class, tarCompetenicaevaluarDto.getCoeId());
                if (competenciaEvaluar == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la competenciaevaluar a modificar.", "guardarCompetenciaEvaluar NoResultException");
                }
                if (tarCompetenicaevaluarDto.getCompetenciaDto() != null) {
                    TarCompetencia tarCompetencia = em.find(TarCompetencia.class, tarCompetenicaevaluarDto.getCompetenciaDto().getComId());
                    competenciaEvaluar.setComId(tarCompetencia);
                }
                if (tarCompetenicaevaluarDto.getEvaluadorDto() != null) {
                    TarEvaluador tarEvaluador = em.find(TarEvaluador.class, tarCompetenicaevaluarDto.getEvaluadorDto().getEvaluId());
                    competenciaEvaluar.setEvaluId(tarEvaluador);
                }
                competenciaEvaluar.actualizar(tarCompetenicaevaluarDto);
                competenciaEvaluar = em.merge(competenciaEvaluar);
            } else {
                competenciaEvaluar = new TarCompetenciaevaluar(tarCompetenicaevaluarDto);
                em.persist(competenciaEvaluar);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "CompetenciaEvaluar", new TarCompetenciaevaluarDto(competenciaEvaluar));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la competenciaevaluar.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la competenciaevaluar.", "guardarCompetenciaEvaluar " + ex.getMessage());
        }
    }

    public Respuesta eliminarCompetenciaEvaluar(Long id) {
        try {
            TarCompetenciaevaluar competenciaEvaluar;
            if (id != null && id > 0) {
                competenciaEvaluar = em.find(TarCompetenciaevaluar.class, id);
                if (competenciaEvaluar == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la competenciaevaluar a eliminar.", "eliminarCompetenciaEvaluar NoResultException");
                }
                em.remove(competenciaEvaluar);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar la competenciaevaluar a eliminar.", "eliminarCompetenciaEvaluar NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar la competenciaevaluar porque tiene relaciones con otros registros.", "eliminarCompetenciaEvaluar " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la competenciaevaluar.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar la competenciaevaluar.", "eliminarCompetenciaEvaluar " + ex.getMessage());
        }
    }

    public Respuesta getCompetenciasEvaluar() {
        try {
            Query qryCompetencia = em.createNamedQuery("TarCompetenciaevaluar.findAll", TarCompetenciaevaluar.class);
            List<TarCompetenciaevaluar> competenciasEvaluar = qryCompetencia.getResultList();
            List<TarCompetenciaevaluarDto> competenciaEvaluarDto = new ArrayList<>();
            for (TarCompetenciaevaluar tarCompetenciaevaluar : competenciasEvaluar) {
                TarCompetenciaevaluarDto competenciaevaluarDto = new TarCompetenciaevaluarDto(tarCompetenciaevaluar);
                if (tarCompetenciaevaluar.getComId() != null) {
                    competenciaevaluarDto.setCompetenciaDto(new TarCompetenciaDto(tarCompetenciaevaluar.getComId()));
                }
                if (tarCompetenciaevaluar.getEvaluId() != null) {
                    competenciaevaluarDto.setEvaluadorDto(new TarEvaluadorDto(tarCompetenciaevaluar.getEvaluId()));
                }
                competenciaEvaluarDto.add(competenciaevaluarDto);

            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "CompetenciaEvaluar", competenciaEvaluarDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen competencias con los criterios ingresados.", "getCompetenciasEvaluar NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la competenciasevaluar.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la competenciasevaluar.", "getCompetenciasEvaluar " + ex.getMessage());
        }
    }

    public Respuesta getCompetenciaEvaluar(Long coeId) {
        try {
            Query qryCompetencia = em.createNamedQuery("TarCompetencia.findByCoeId", TarCompetenciaevaluar.class);
            qryCompetencia.setParameter("coeId", coeId);
            TarCompetenciaevaluar tarCompetenciaevaluar = (TarCompetenciaevaluar) qryCompetencia.getSingleResult();
            TarCompetenciaevaluarDto competenciaevaluarDto = new TarCompetenciaevaluarDto();

            if (tarCompetenciaevaluar.getComId() != null) {
                competenciaevaluarDto.setCompetenciaDto(new TarCompetenciaDto(tarCompetenciaevaluar.getComId()));
            }
            if (tarCompetenciaevaluar.getEvaluId() != null) {
                competenciaevaluarDto.setEvaluadorDto(new TarEvaluadorDto(tarCompetenciaevaluar.getEvaluId()));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "CompetenciaEvaluar", competenciaevaluarDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una competenciaevaluar con el código ingresado.", "getCompetenciaEvaluar NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la competenciaevaluar.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la competenciaevaluar.", "getCompetenciaEvaluar NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la competenciaevaluar.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la competenciaevaluar.", "getCompetenciaEvaluar " + ex.getMessage());
        }
    }
}
