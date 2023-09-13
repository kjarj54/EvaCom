/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.service;

import cr.ac.una.evacomunaws.model.TarEvaluador;
import cr.ac.una.evacomunaws.model.TarEvaluadorDto;
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
public class TarEvaluadorService {
   private static final Logger LOG = Logger.getLogger(TarEvaluadorService.class.getName());
    @PersistenceContext(unitName = "EvaComUNAPU")
    private EntityManager em;
    
    public Respuesta guardarEvaluador(TarEvaluadorDto tarEvaluadorDto) {
        try {
            TarEvaluador evaluador;
            if (tarEvaluadorDto.getEvaluId()!= null && tarEvaluadorDto.getEvaluId()> 0) {
                evaluador = em.find(TarEvaluador.class, tarEvaluadorDto.getEvaluId());
                if (evaluador == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el evaluador a modificar.", "guardarEvaluador NoResultException");
                }
                evaluador.actualizar(tarEvaluadorDto);
                evaluador = em.merge(evaluador);
            } else {
                evaluador = new TarEvaluador(tarEvaluadorDto);
                em.persist(evaluador);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Evaluador", new TarEvaluadorDto(evaluador));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el evaluador.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el evaluador.", "guardarEvaluador " + ex.getMessage());
        }
    }
    
    public Respuesta eliminarEvaluador(Long id) {
        try {
            TarEvaluador evaluador;
            if (id != null && id > 0) {
                evaluador = em.find(TarEvaluador.class, id);
                if (evaluador == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el evaluador a eliminar.", "eliminarEvaluador NoResultException");
                }
                em.remove(evaluador);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el evaluador a eliminar.", "eliminarEvaluador NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el evaluador porque tiene relaciones con otros registros.", "eliminarEvaluador " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la caracteristica.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el evaluador.", "eliminarEvaluador " + ex.getMessage());
        }
    }
    //TODO
    public Respuesta getEvaluadores() {
        try {
            Query qryEvaluador = em.createNamedQuery("TarEvaluador.findAll", TarEvaluador.class);
            List<TarEvaluador> evaluadores = qryEvaluador.getResultList();
            List<TarEvaluadorDto> evaluadorDto = new ArrayList<>();
            for (TarEvaluador evaluador : evaluadores) {
                evaluadorDto.add(new TarEvaluadorDto(evaluador));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Evaluadores", evaluadorDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen evaluadores con los criterios ingresados.", "getEvaluadores NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar evaluadores.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar evaluadores.", "getEvaluadores " + ex.getMessage());
        }
    }
    
    public Respuesta getEvaluador(Long evaluId) {
        try {
            Query qryCaracteristica = em.createNamedQuery("TarEvaluador.findByEvaluId", TarEvaluador.class);
            qryCaracteristica.setParameter("evaluId", evaluId);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Evaluador", new TarEvaluadorDto((TarEvaluador) qryCaracteristica.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un evaluador con el código ingresado.", "getEvaluador NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el evaluador.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el evaluador.", "getEvaluador NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el evaluador.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el evaluador.", "getEvaluador " + ex.getMessage());
        }
    }
}
