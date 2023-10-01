/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.service;

import cr.ac.una.evacomuna.model.TarPuestoDto;
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
public class TarPuestoService {

    EvaComUNAWs evaComUNAWs;

    public Respuesta guardarTarPuesto(cr.ac.una.evacomunaws.controller.TarPuestoDto tarPuestoDto) {

        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            // TODO process result here
            TarPuestoDto puestoDto = new TarPuestoDto((cr.ac.una.evacomunaws.controller.TarPuestoDto) evaComUNAWs.guardarPuesto(tarPuestoDto));
            return new Respuesta(true, "", "", "Puesto", puestoDto);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error guardando el Puesto.", ex);
            return new Respuesta(false, "Error guardando el Puesto.", "guardarTarPuesto " + ex.getMessage());
        }

    }

    public Respuesta getPuesto(Long id) {

        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            // TODO process result here
            TarPuestoDto puestoDto = new TarPuestoDto((cr.ac.una.evacomunaws.controller.TarPuestoDto) evaComUNAWs.getPuestoClass(id));

            return new Respuesta(true, "", "", "Puesto", puestoDto);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Puesto [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Puesto.", "getPuesto" + ex.getMessage());
        }

    }

    public Respuesta eliminarPuesto(Long id) {

        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();

            TarPuestoDto puestoDto = new TarPuestoDto((cr.ac.una.evacomunaws.controller.TarPuestoDto) evaComUNAWs.eliminarPuesto(id));
            return new Respuesta(true, "", "", "Puesto", "");
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo el Puesto [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Puesto.", "eliminarPuesto" + ex.getMessage());
        }

    }

    public Respuesta getPuestos(String nombre, String activas) {
        try {
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            List<cr.ac.una.evacomunaws.controller.TarPuestoDto> tarPuestoDtoList = evaComUNAWs.getPuestos();
            List<TarPuestoDto> tarPuestoDtoClienteList = new ArrayList<>();
            
            for(cr.ac.una.evacomunaws.controller.TarPuestoDto item : tarPuestoDtoList){
                TarPuestoDto tarPuestoDto = new TarPuestoDto(item);
                tarPuestoDtoClienteList.add(tarPuestoDto);
            }

            if (!nombre.isEmpty()) {
                tarPuestoDtoClienteList = tarPuestoDtoClienteList.stream().filter((p) -> p.getPueNombre().toLowerCase().contains(nombre.toLowerCase())).collect(Collectors.toList());
            }
            
            if ("S".equals(activas)) {
                tarPuestoDtoClienteList = tarPuestoDtoClienteList.stream().filter((p) -> "A".equals(p.getPueEstado())).collect(Collectors.toList());
            }
            
            return new Respuesta(true, "", "", "TarPuestos",tarPuestoDtoClienteList);
        } catch (Exception ex) {
            Logger.getLogger(TarTrabajadorevaluarService.class.getName()).log(Level.SEVERE, "Error obteniendo los Puesto", ex);
            return new Respuesta(false, "Error obteniendo los Puesto.", "getPuestos" + ex.getMessage());
        }
    }
}
