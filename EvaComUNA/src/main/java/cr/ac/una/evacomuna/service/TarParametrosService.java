/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.service;

import cr.ac.una.evacomuna.model.TarParametrosDto;
import cr.ac.una.evacomuna.util.Respuesta;
import cr.ac.una.evacomunaws.controller.EvaComUNAWs;
import cr.ac.una.evacomunaws.controller.EvaComUNAWs_Service;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class TarParametrosService {
    EvaComUNAWs evaComUNAWs;
    
    public Respuesta guardarTarParametros(cr.ac.una.evacomunaws.controller.TarParametrosDto tarParametrosDto) {

        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            // TODO process result here
            TarParametrosDto competenciaevaluarDto = new TarParametrosDto((cr.ac.una.evacomunaws.controller.TarParametrosDto) evaComUNAWs.guardarParametros(tarParametrosDto));
            return new Respuesta(true, "", "", "Parametros", competenciaevaluarDto);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error guardando el Parametros.", ex);
            return new Respuesta(false, "Error guardando el Parametros.", "guardarTarParametros " + ex.getMessage());
        }

    }

    public Respuesta getParametros(Long id) {
        
        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            // TODO process result here
            TarParametrosDto parametrosDto = new TarParametrosDto((cr.ac.una.evacomunaws.controller.TarParametrosDto)evaComUNAWs.getParametrosClass(id));
            
            return new Respuesta(true, "", "", "Parametros", parametrosDto);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Parametros [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Parametros.", "getParametros" + ex.getMessage());
        }


    }

    public Respuesta eliminarParametros(Long id) {
        
        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();

            TarParametrosDto evaluadorDto  = new TarParametrosDto((cr.ac.una.evacomunaws.controller.TarParametrosDto)evaComUNAWs.eliminarParametros(id));
            return new Respuesta(true, "", "", "Parametros", "");
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Parametros [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Parametros.", "eliminarParametros" + ex.getMessage());
        }

    }
    
    public Respuesta getParametrosList() {
        try {
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            
            List<cr.ac.una.evacomunaws.controller.TarParametrosDto> tarParametrosDtosListSoap = evaComUNAWs.getParametros();
            
            List<TarParametrosDto> tarParametrosDtosList = new ArrayList<>();
            
            for (cr.ac.una.evacomunaws.controller.TarParametrosDto item : tarParametrosDtosListSoap) {
                TarParametrosDto tarParametrosDto = new TarParametrosDto(item);
                tarParametrosDtosList.add(tarParametrosDto);
            }
            return new Respuesta(true, "", "", "Parametros", tarParametrosDtosList);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Evaluador ", ex);
            return new Respuesta(false, "Error obteniendo el Evaluador.", "getEvaluadores" + ex.getMessage());
        }
    }
    
}
