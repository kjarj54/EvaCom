/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.service;

import cr.ac.una.evacomuna.model.TarProcesoevaluacionDto;
import cr.ac.una.evacomuna.util.Respuesta;
import cr.ac.una.evacomunaws.controller.EvaComUNAWs;
import cr.ac.una.evacomunaws.controller.EvaComUNAWs_Service;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class TarProcesoevaluacionService {
    EvaComUNAWs evaComUNAWs;
    
    public Respuesta guardarTarProcesoevaluacion(cr.ac.una.evacomunaws.controller.TarProcesoevaluacionDto tarProcesoevaluacionDto) {

        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            // TODO process result here
            TarProcesoevaluacionDto procesoevaluacionDto = new TarProcesoevaluacionDto((cr.ac.una.evacomunaws.controller.TarProcesoevaluacionDto) evaComUNAWs.guardarProcesoevaluacion(tarProcesoevaluacionDto));
            return new Respuesta(true, "", "", "Procesoevaluacion", procesoevaluacionDto);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error guardando el Procesoevaluacion.", ex);
            return new Respuesta(false, "Error guardando el Procesoevaluacion.", "guardarTarProcesoevaluacion " + ex.getMessage());
        }

    }

    public Respuesta getProcesoevaluacion(Long id) {
        
        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            // TODO process result here
            TarProcesoevaluacionDto procesoevaluacionDto = new TarProcesoevaluacionDto((cr.ac.una.evacomunaws.controller.TarProcesoevaluacionDto)evaComUNAWs.getProcesoevaluacionClass(id));
            
            return new Respuesta(true, "", "", "Procesoevaluacion", procesoevaluacionDto);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Procesoevaluacion [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Procesoevaluacion.", "getProcesoevaluacion" + ex.getMessage());
        }


    }

    public Respuesta eliminarProcesoevaluacion(Long id) {
        
        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();

            TarProcesoevaluacionDto procesoevaluacionDto  = new TarProcesoevaluacionDto((cr.ac.una.evacomunaws.controller.TarProcesoevaluacionDto)evaComUNAWs.eliminarProcesoevaluacion(id));
            return new Respuesta(true, "", "", "Procesoevaluacion", "");
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Procesoevaluacion [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Procesoevaluacion.", "eliminarProcesoevaluacion" + ex.getMessage());
        }

    }
}
