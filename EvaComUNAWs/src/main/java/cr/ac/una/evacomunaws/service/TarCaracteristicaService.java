/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.service;

import cr.ac.una.evacomunaws.model.TarCaracteristica;
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
public class TarCaracteristicaService {

    private static final Logger LOG = Logger.getLogger(TarCaracteristicaService.class.getName());
    @PersistenceContext(unitName = "EvaComUNAPU")
    private EntityManager em;

    public Respuesta guardarCaracteristica(TarCaracteristicaDto tarCaracteristicaDto) {
        try {
            TarCaracteristica caracteristica;
            if (tarCaracteristicaDto.getCarId() != null && tarCaracteristicaDto.getCarId() > 0) {
                caracteristica = em.find(TarCaracteristica.class, tarCaracteristicaDto.getCarId());
                if (caracteristica == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la caracteristica a modificar.", "guardarCaracteristica NoResultException");
                }
                if (tarCaracteristicaDto.getCompetenciaDto() != null) {
                    TarCompetencia competencia = em.find(TarCompetencia.class, tarCaracteristicaDto.getCompetenciaDto().getComId());
                    caracteristica.setComId(competencia);
                }
                caracteristica.actualizar(tarCaracteristicaDto);
                caracteristica = em.merge(caracteristica);
            } else {
                caracteristica = new TarCaracteristica(tarCaracteristicaDto);
                em.persist(caracteristica);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Caracteristica", new TarCaracteristicaDto(caracteristica));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la caracteristica.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la caracteristica.", "guardarCaracteristica " + ex.getMessage());
        }
    }

    public Respuesta eliminarCaracteristica(Long id) {
        try {
            TarCaracteristica caracteristica;
            if (id != null && id > 0) {
                caracteristica = em.find(TarCaracteristica.class, id);
                if (caracteristica == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la caracteristica a eliminar.", "eliminarCaracteristica NoResultException");
                }
                em.remove(caracteristica);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar la caracteristica a eliminar.", "eliminarCaracteristica NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar la caracteristica porque tiene relaciones con otros registros.", "eliminarCaracteristica " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la caracteristica.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar la caracteristica.", "eliminarCaracteristica " + ex.getMessage());
        }
    }

    //TODO
    public Respuesta getCaracteristicas() {
        try {
            Query qryCaracteristica = em.createNamedQuery("TarCaracteristica.findAll", TarCaracteristica.class);
            List<TarCaracteristica> caracteristicas = qryCaracteristica.getResultList();
            List<TarCaracteristicaDto> caracteristicaDto = new ArrayList<>();
            for (TarCaracteristica caracteristica : caracteristicas) {
                TarCaracteristicaDto tarCaracteristicaDto = new TarCaracteristicaDto(caracteristica);
                if (caracteristica.getComId() != null) {
                    tarCaracteristicaDto.setCompetenciaDto(new TarCompetenciaDto(caracteristica.getComId()));
                }
                caracteristicaDto.add(tarCaracteristicaDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Caracteristica", caracteristicaDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen caracteristicas con los criterios ingresados.", "getCaracteristicas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la caracteristica.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la caracteristica.", "getCaracteristicas " + ex.getMessage());
        }
    }

    public Respuesta getCaracteristica(Long carId) {
        try {
            Query qryCaracteristica = em.createNamedQuery("TarCaracteristica.findByCarId", TarCaracteristica.class);
            qryCaracteristica.setParameter("carId", carId);
            TarCaracteristica tarCaracteristica = (TarCaracteristica) qryCaracteristica.getSingleResult();
            TarCaracteristicaDto tarCaracteristicaDto = new TarCaracteristicaDto(tarCaracteristica);
            if (tarCaracteristica.getComId() != null) {
                tarCaracteristicaDto.setCompetenciaDto(new TarCompetenciaDto(tarCaracteristica.getComId()));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Caracteristica", tarCaracteristicaDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una caracteristica con el código ingresado.", "getCaracteristica NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la caracteristica.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la caracteristica.", "getCaracteristica NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la caracteristica.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la caracteristica.", "getCaracteristica " + ex.getMessage());
        }
    }

}
