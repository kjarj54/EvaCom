/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.service;

import cr.ac.una.evacomuna.model.TarCompetenciaDto;
import cr.ac.una.evacomuna.util.Respuesta;
import cr.ac.una.evacomunaws.controller.EvaComUNAWs;
import cr.ac.una.evacomunaws.controller.EvaComUNAWs_Service;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
            TarCompetenciaDto competenciaDto = new TarCompetenciaDto((cr.ac.una.evacomunaws.controller.TarCompetenciaDto) evaComUNAWs.getCompetenciaClass(id));

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

            evaComUNAWs.eliminarCompetencia(id);
            return new Respuesta(true, "", "", "Competencia", "");
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Competencia [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Competencia.", "eliminarCompetencia" + ex.getMessage());
        }

    }

    public Respuesta getCompetencias(String nombre, String activas) {
        try {
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            List<cr.ac.una.evacomunaws.controller.TarCompetenciaDto> tarPuestoDtoList = evaComUNAWs.getCompetenciaS();
            List<TarCompetenciaDto> tarCompetenciaDtoClienteList = new ArrayList<>();
            for (cr.ac.una.evacomunaws.controller.TarCompetenciaDto item : tarPuestoDtoList) {
                TarCompetenciaDto tarCompetenciaDto = new TarCompetenciaDto(item);
                tarCompetenciaDtoClienteList.add(tarCompetenciaDto);
            }

            if (nombre != null && !nombre.isBlank()) {
                tarCompetenciaDtoClienteList = tarCompetenciaDtoClienteList.stream().filter((p) -> p.getComNombre().toLowerCase().contains(nombre.toLowerCase())).collect(Collectors.toList());
            }

            if (activas != null && "S".equals(activas)) {
                tarCompetenciaDtoClienteList = tarCompetenciaDtoClienteList.stream().filter((p) -> "A".equals(p.getComEstado())).collect(Collectors.toList());
            }

            return new Respuesta(true, "", "", "TarCompetencia", tarCompetenciaDtoClienteList);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Competencia ", ex);
            return new Respuesta(false, "Error obteniendo el Competencia.", "getCompetencia" + ex.getMessage());
        }
    }

}
