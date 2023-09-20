/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.service;

import cr.ac.una.evacomuna.model.TarEvaluadorDto;
import cr.ac.una.evacomuna.util.Respuesta;
import cr.ac.una.evacomunaws.controller.EvaComUNAWs;
import cr.ac.una.evacomunaws.controller.EvaComUNAWs_Service;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class TarEvaluadorService {
    EvaComUNAWs evaComUNAWs;
    public Respuesta guardarTarEvaluador(cr.ac.una.evacomunaws.controller.TarEvaluadorDto tarEvaluadorDto) {

        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            // TODO process result here
            TarEvaluadorDto evaluadorDto = new TarEvaluadorDto((cr.ac.una.evacomunaws.controller.TarEvaluadorDto) evaComUNAWs.guardarEvaluador(tarEvaluadorDto));
            return new Respuesta(true, "", "", "Evaluador", evaluadorDto);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error guardando el Evaluador.", ex);
            return new Respuesta(false, "Error guardando el Evaluador.", "guardarTarEvaluador " + ex.getMessage());
        }

    }

    public Respuesta getEvaluador(Long id) {
        
        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            // TODO process result here
            TarEvaluadorDto evaluadorDto = new TarEvaluadorDto((cr.ac.una.evacomunaws.controller.TarEvaluadorDto)evaComUNAWs.getEvaluadorClass(id));
            
            return new Respuesta(true, "", "", "Evaluador", evaluadorDto);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Evaluador [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Evaluador.", "getEvaluador" + ex.getMessage());
        }


    }

    public Respuesta eliminarEvaluador(Long id) {
        
        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();

            TarEvaluadorDto evaluadorDto  = new TarEvaluadorDto((cr.ac.una.evacomunaws.controller.TarEvaluadorDto)evaComUNAWs.eliminarEvaluador(id));
            return new Respuesta(true, "", "", "Evaluador", "");
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Evaluador [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Evaluador.", "eliminarEvaluador" + ex.getMessage());
        }

    }
    
    
}
