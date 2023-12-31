/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.service;

import cr.ac.una.evacomunaws.model.TarCaracteristica;
import cr.ac.una.evacomunaws.model.TarCaracteristicaDto;
import cr.ac.una.evacomunaws.model.TarCompetencia;
import cr.ac.una.evacomunaws.model.TarCompetenciaDto;
import cr.ac.una.evacomunaws.model.TarCompetenciaevaluar;
import cr.ac.una.evacomunaws.model.TarCompetenciaevaluarDto;
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
public class TarCompetenciaService {

    private static final Logger LOG = Logger.getLogger(TarCompetenciaService.class.getName());
    @PersistenceContext(unitName = "EvaComUNAPU")
    private EntityManager em;

    public Respuesta guardarCompetencia(TarCompetenciaDto tarCompetenciaDto) {//TODO el for each que setia las listas
        try {
            TarCompetencia competencia;
            if (tarCompetenciaDto.getComId() != null && tarCompetenciaDto.getComId() > 0) {
                competencia = em.find(TarCompetencia.class, tarCompetenciaDto.getComId());
                if (competencia == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la competencia a modificar.", "guardarCompetencia NoResultException");
                }
                competencia.actualizar(tarCompetenciaDto);
                for (TarPuestoDto pue : tarCompetenciaDto.getTarPuestoEliminados()) {
                    competencia.getTarPuestoList().remove(new TarPuesto(pue.getPueId()));
                }
                if (!tarCompetenciaDto.getTarPuestoList().isEmpty()) {
                    for (TarPuestoDto pue : tarCompetenciaDto.getTarPuestoList()) {
                        if (pue.getModificado()) {
                            TarPuesto puesto = em.find(TarPuesto.class, pue.getPueId());
                            competencia.getTarPuestoList().add(puesto);
                            puesto.getTarCompetenciaList().add(competencia);
                        }
                    }
                }

                for (TarCaracteristicaDto tarCaracteristicaDto : tarCompetenciaDto.getTarCaracteristicaEliminados()) {
                    competencia.getTarCaracteristicaList().remove(new TarCaracteristica(tarCaracteristicaDto.getCarId()));
                }
                if (!tarCompetenciaDto.getTarCaracteristicaList().isEmpty()) {
                    for (TarCaracteristicaDto tarCaracteristicaDto : tarCompetenciaDto.getTarCaracteristicaList()) {
                        if (tarCaracteristicaDto.getModificado()) {
                            TarCaracteristica tarCaracteristica = em.find(TarCaracteristica.class, tarCaracteristicaDto.getCarId());
                            tarCaracteristica.setComId(competencia);
                            competencia.getTarCaracteristicaList().add(tarCaracteristica);
                        }
                    }
                }

                for (TarCompetenciaevaluarDto tarCompetenciaevaluarDto : tarCompetenciaDto.getTarCompetenciaevaluarElimimados()) {
                    competencia.getTarCompetenciaevaluarList().remove(new TarCompetenciaevaluar(tarCompetenciaevaluarDto.getCoeId()));
                }
                if (!tarCompetenciaDto.getTarCompetenciaevaluarList().isEmpty()) {

                    for (TarCompetenciaevaluarDto tarCompetenciaevaluarDto : tarCompetenciaDto.getTarCompetenciaevaluarList()) {
                        TarCompetenciaevaluar tarCompetenciaevaluar = em.find(TarCompetenciaevaluar.class, tarCompetenciaevaluarDto.getCoeId());
                        competencia.getTarCompetenciaevaluarList().add(tarCompetenciaevaluar);
                    }
                }

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
                TarCompetenciaDto dto = new TarCompetenciaDto(competencia);

                if (!competencia.getTarPuestoList().isEmpty()) {
                    List<TarPuestoDto> tarPuestoDtosList = new ArrayList<>();
                    for (TarPuesto puesto : competencia.getTarPuestoList()) {
                        tarPuestoDtosList.add(new TarPuestoDto(puesto));
                    }
                    dto.getTarPuestoList().addAll(tarPuestoDtosList);
                }

                if (!competencia.getTarCaracteristicaList().isEmpty()) {
                    List<TarCaracteristicaDto> tarCaracteristicaDtosList = new ArrayList<>();
                    for (TarCaracteristica caracteristica : competencia.getTarCaracteristicaList()) {
                        tarCaracteristicaDtosList.add(new TarCaracteristicaDto(caracteristica));
                    }
                    dto.getTarCaracteristicaList().addAll(tarCaracteristicaDtosList);
                }

                if (!competencia.getTarCompetenciaevaluarList().isEmpty()) {
                    List<TarCompetenciaevaluarDto> tarCompetenciaevaluarDtosList = new ArrayList<>();
                    for (TarCompetenciaevaluar competenciaevaluar : competencia.getTarCompetenciaevaluarList()) {
                        tarCompetenciaevaluarDtosList.add(new TarCompetenciaevaluarDto(competenciaevaluar));
                    }
                    dto.getTarCompetenciaevaluarList().addAll(tarCompetenciaevaluarDtosList);
                }
                competenciaDto.add(dto);
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

            TarCompetencia tarCompetencia = (TarCompetencia) qryCompetencia.getSingleResult();

            TarCompetenciaDto tarCompetenciaDto = new TarCompetenciaDto(tarCompetencia);
            if (!tarCompetencia.getTarPuestoList().isEmpty()) {
                for (TarPuesto puesto : tarCompetencia.getTarPuestoList()) {
                    tarCompetenciaDto.getTarPuestoList().add(new TarPuestoDto(puesto));
                }
            }

            if (!tarCompetencia.getTarCaracteristicaList().isEmpty()) {
                for (TarCaracteristica caracteristica : tarCompetencia.getTarCaracteristicaList()) {
                    tarCompetenciaDto.getTarCaracteristicaList().add(new TarCaracteristicaDto(caracteristica));
                }
            }

            if (!tarCompetencia.getTarCompetenciaevaluarList().isEmpty()) {
                for (TarCompetenciaevaluar competenciaevaluar : tarCompetencia.getTarCompetenciaevaluarList()) {
                    tarCompetenciaDto.getTarCompetenciaevaluarList().add(new TarCompetenciaevaluarDto(competenciaevaluar));
                }
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Competencia", tarCompetenciaDto);

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
