/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.service;

import cr.ac.una.evacomunaws.model.TarCaracteristica;
import cr.ac.una.evacomunaws.model.TarCaracteristicaDto;
import cr.ac.una.evacomunaws.util.CodigoRespuesta;
import cr.ac.una.evacomunaws.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la caracteristica.", "guardarEmpleado " + ex.getMessage());
        }
    }
}
