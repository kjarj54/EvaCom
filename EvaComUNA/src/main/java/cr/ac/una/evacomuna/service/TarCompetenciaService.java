/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.service;

import cr.ac.una.evacomuna.model.TarCompetenciaDto;
import cr.ac.una.evacomuna.util.Respuesta;
import cr.ac.una.evacomunaws.controller.EvaComUNAWs;
import cr.ac.una.evacomunaws.controller.EvaComUNAWs_Service;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class TarCompetenciaService {
    EvaComUNAWs evaComUNAWs;
    
    public Respuesta guardarTarCompetencia(cr.ac.una.evacomunaws.controller.TarCompetenciaDto tarCompetenciaDto) {

        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            // TODO process result here
            TarCompetenciaDto competenciaDto = new TarCompetenciaDto((cr.ac.una.evacomunaws.controller.TarCompetenciaDto) evaComUNAWs.guardarCompetencia(tarCompetenciaDto));
            return new Respuesta(true, "", "", "Competencia", competenciaDto);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error guardando el Competencia.", ex);
            return new Respuesta(false, "Error guardando el Competencia.", "guardarTarCompetencia " + ex.getMessage());
        }

    }

    public Respuesta getCompetencia(Long id) {
        
        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            // TODO process result here
            TarCompetenciaDto competenciaDto = new TarCompetenciaDto((cr.ac.una.evacomunaws.controller.TarCompetenciaDto)evaComUNAWs.getCompetenciaClass(id));
            
            return new Respuesta(true, "", "", "Competencia", competenciaDto);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Competencia [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Competencia.", "getCompetencia" + ex.getMessage());
        }


    }

    public Respuesta eliminarCompetencia(Long id) {
        
        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();

            TarCompetenciaDto competenciaDto  = new TarCompetenciaDto((cr.ac.una.evacomunaws.controller.TarCompetenciaDto)evaComUNAWs.eliminarCompetencia(id));
            return new Respuesta(true, "", "", "Competencia", "");
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Competencia [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Competencia.", "eliminarCompetencia" + ex.getMessage());
        }

    }
}
