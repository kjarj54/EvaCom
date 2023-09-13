/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.service;

import cr.ac.una.evacomunaws.model.TarParametros;
import cr.ac.una.evacomunaws.model.TarParametrosDto;
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
public class TarParametrosService {

    private static final Logger LOG = Logger.getLogger(TarParametrosService.class.getName());
    @PersistenceContext(unitName = "EvaComUNAPU")
    private EntityManager em;
    
    
    public Respuesta guardarParametros(TarParametrosDto tarParametrosDto) {
        try {
            TarParametros parametros;
            if (tarParametrosDto.getParId()!= null && tarParametrosDto.getParId()> 0) {
                parametros = em.find(TarParametros.class, tarParametrosDto.getParId());
                if (parametros == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró los parametros a modificar.", "guardarParametros NoResultException");
                }
                parametros.actualizar(tarParametrosDto);
                parametros = em.merge(parametros);
            } else {
                parametros = new TarParametros(tarParametrosDto);
                em.persist(parametros);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TarParametros", new TarParametrosDto(parametros));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar los parametros.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar los parametros.", "guardarParametros " + ex.getMessage());
        }
    }
    
    public Respuesta eliminarParametros(Long id) {
        try {
            TarParametros parametros;
            if (id != null && id > 0) {
                parametros = em.find(TarParametros.class, id);
                if (parametros == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró los parametros a eliminar.", "eliminarParametros NoResultException");
                }
                em.remove(parametros);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar los parametros a eliminar.", "eliminarParametros NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar los parametros porque tiene relaciones con otros registros.", "eliminarParametros " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar los parametros.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar los parametros.", "eliminarParametros " + ex.getMessage());
        }
    }
    
    public Respuesta getParametros() {
        try {
            Query qryParametros = em.createNamedQuery("TarParametros.findAll", TarParametros.class);
            List<TarParametros> parametros = qryParametros.getResultList();
            List<TarParametrosDto> parametrosDto = new ArrayList<>();
            for (TarParametros parametro:  parametros) {
                parametrosDto.add(new TarParametrosDto(parametro));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TarParametros", parametrosDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen los parametros con los criterios ingresados.", "getParametros NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar los parametros.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los parametros.", "getParametros " + ex.getMessage());
        }
    }
    
    public Respuesta getParametro(Long parId) {
        try {
            Query qryCaracteristica = em.createNamedQuery("TarParametros.findByParId", TarParametros.class);
            qryCaracteristica.setParameter("parId", parId);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TarParametros", new TarParametrosDto((TarParametros) qryCaracteristica.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe los parametros con el código ingresado.", "getParametro NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar los parametros.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los parametros.", "getParametro NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar los parametros.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los parametros.", "getParametro " + ex.getMessage());
        }
    }
}
