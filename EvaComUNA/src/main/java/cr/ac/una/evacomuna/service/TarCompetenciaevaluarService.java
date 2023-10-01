/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.service;

import cr.ac.una.evacomuna.model.TarCompetenciaevaluarDto;
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
public class TarCompetenciaevaluarService {

    EvaComUNAWs evaComUNAWs;

    public Respuesta guardarTarCompetenciaevaluar(cr.ac.una.evacomunaws.controller.TarCompetenciaevaluarDto tarCompetenciaevaluarDto) {

        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            // TODO process result here
            TarCompetenciaevaluarDto competenciaevaluarDto = new TarCompetenciaevaluarDto((cr.ac.una.evacomunaws.controller.TarCompetenciaevaluarDto) evaComUNAWs.guardarCompetenciaevaluar(tarCompetenciaevaluarDto));
            return new Respuesta(true, "", "", "Competenciaevaluar", competenciaevaluarDto);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error guardando el Competenciaevaluar.", ex);
            return new Respuesta(false, "Error guardando el Competenciaevaluar.", "guardarTarCompetenciaevaluar " + ex.getMessage());
        }

    }

    public Respuesta getCompetenciaevaluar(Long id) {

        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            // TODO process result here
            TarCompetenciaevaluarDto competenciaevaluarDto = new TarCompetenciaevaluarDto((cr.ac.una.evacomunaws.controller.TarCompetenciaevaluarDto) evaComUNAWs.getCompetenciaevaluarClass(id));

            return new Respuesta(true, "", "", "Competenciaevaluar", competenciaevaluarDto);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Competenciaevaluar [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Competenciaevaluar.", "getCompetenciaevaluar" + ex.getMessage());
        }

    }

    public Respuesta eliminarCompetenciaevaluar(Long id) {

        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();

            TarCompetenciaevaluarDto evaluadorDto = new TarCompetenciaevaluarDto((cr.ac.una.evacomunaws.controller.TarCompetenciaevaluarDto) evaComUNAWs.eliminarCompetenciaevaluar(id));
            return new Respuesta(true, "", "", "Competenciaevaluar", "");
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Competenciaevaluar [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Competenciaevaluar.", "eliminarCompetenciaevaluar" + ex.getMessage());
        }

    }

    public Respuesta getCompetenciasevaluar() {
        try {
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            List<cr.ac.una.evacomunaws.controller.TarCompetenciaevaluarDto> competenciaevaluarDtosListSoap = evaComUNAWs.getCompetenciasevaluar();
            List<TarCompetenciaevaluarDto> tarCompetenciaevaluarDtosList = new ArrayList<>();
            for (cr.ac.una.evacomunaws.controller.TarCompetenciaevaluarDto item : competenciaevaluarDtosListSoap) {
                TarCompetenciaevaluarDto tarCompetenciaevaluarDto = new TarCompetenciaevaluarDto(item);
                tarCompetenciaevaluarDtosList.add(tarCompetenciaevaluarDto);
            }
            return new Respuesta(true, "", "", "Competenciaevaluar", tarCompetenciaevaluarDtosList);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Competencia ", ex);
            return new Respuesta(false, "Error obteniendo el Competencia.", "getCompetencia" + ex.getMessage());
        }
    }
}
