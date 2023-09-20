/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.service;

import cr.ac.una.evacomuna.model.TarTrabajadorevaluarDto;
import cr.ac.una.evacomuna.util.Respuesta;
import cr.ac.una.evacomunaws.controller.EvaComUNAWs;
import cr.ac.una.evacomunaws.controller.EvaComUNAWs_Service;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class TarTrabajadorevaluarService {

    EvaComUNAWs evaComUNAWs;

    public Respuesta guardarTarTrabajadorevaluar(cr.ac.una.evacomunaws.controller.TarTrabajadorevaluarDto tarTrabajadorevaluarDto) {

        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            // TODO process result here
            TarTrabajadorevaluarDto trabajadorevaluarDto = new TarTrabajadorevaluarDto((cr.ac.una.evacomunaws.controller.TarTrabajadorevaluarDto) evaComUNAWs.guardarTrabajadorevaluar(tarTrabajadorevaluarDto));
            return new Respuesta(true, "", "", "Trabajadorevaluar", trabajadorevaluarDto);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error guardando el Trabajadorevaluar.", ex);
            return new Respuesta(false, "Error guardando el Trabajadorevaluar.", "guardarTarTrabajadorevaluar " + ex.getMessage());
        }

    }

    public Respuesta getTarTrabajadorevaluar(Long id) {
        
        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            // TODO process result here
            TarTrabajadorevaluarDto procesoevaluacionDto = new TarTrabajadorevaluarDto((cr.ac.una.evacomunaws.controller.TarTrabajadorevaluarDto)evaComUNAWs.getTrabajadorevaluarClass(id));
            
            return new Respuesta(true, "", "", "Trabajadorevaluar", procesoevaluacionDto);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Trabajadorevaluar [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Trabajadorevaluar.", "getTarTrabajadorevaluar" + ex.getMessage());
        }


    }

    public Respuesta eliminarTarTrabajadorevaluar(Long id) {
        
        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();

            TarTrabajadorevaluarDto procesoevaluacionDto  = new TarTrabajadorevaluarDto((cr.ac.una.evacomunaws.controller.TarTrabajadorevaluarDto)evaComUNAWs.eliminarTrabajadorevaluar(id));
            return new Respuesta(true, "", "", "Trabajadorevaluar", "");
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Trabajadorevaluar [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Trabajadorevaluar.", "eliminarTarTrabajadorevaluar" + ex.getMessage());
        }

    }
}
