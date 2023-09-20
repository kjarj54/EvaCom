/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.service;

import cr.ac.una.evacomuna.model.TarCaracteristicaDto;
import cr.ac.una.evacomuna.util.Respuesta;
import cr.ac.una.evacomunaws.controller.EvaComUNAWs;
import cr.ac.una.evacomunaws.controller.EvaComUNAWs_Service;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class TarCaracteristicaService {
    EvaComUNAWs evaComUNAWs;
    
    public Respuesta guardarTarCaracteristica(cr.ac.una.evacomunaws.controller.TarCaracteristicaDto tarCaracteristicaDto) {

        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            // TODO process result here
            TarCaracteristicaDto caracteristicaDto = new TarCaracteristicaDto((cr.ac.una.evacomunaws.controller.TarCaracteristicaDto) evaComUNAWs.guardarCaracteristica(tarCaracteristicaDto));
            return new Respuesta(true, "", "", "Caracteristica", caracteristicaDto);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error guardando el Caracteristica.", ex);
            return new Respuesta(false, "Error guardando el Caracteristica.", "guardarTarCaracteristica " + ex.getMessage());
        }

    }

    public Respuesta getCaracteristica(Long id) {
        
        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            // TODO process result here
            TarCaracteristicaDto caracteristicaDto = new TarCaracteristicaDto((cr.ac.una.evacomunaws.controller.TarCaracteristicaDto)evaComUNAWs.getCaracteristicaClass(id));
            
            return new Respuesta(true, "", "", "Caracteristica", caracteristicaDto);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Caracteristica [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Caracteristica.", "getCaracteristica" + ex.getMessage());
        }


    }

    public Respuesta eliminarCaracteristica(Long id) {
        
        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();

            TarCaracteristicaDto evaluadorDto  = new TarCaracteristicaDto((cr.ac.una.evacomunaws.controller.TarCaracteristicaDto)evaComUNAWs.eliminarCaracteristica(id));
            return new Respuesta(true, "", "", "Caracteristica", "");
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Caracteristica [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Caracteristica.", "eliminarCaracteristica" + ex.getMessage());
        }

    }
}
