/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.service;

import cr.ac.una.evacomuna.util.Respuesta;
import cr.ac.una.evacomunaws.controller.EvaComUNAWs;
import cr.ac.una.evacomunaws.controller.EvaComUNAWs_Service;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class TarUsuarioService {
    EvaComUNAWs evaComUNAWs;

    public Respuesta guardarUsuario(cr.ac.una.evacomunaws.controller.TarUsuarioDto usuario) {
        try {
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            evaComUNAWs.guardarUsuario(usuario);
           if (false) {
                return new Respuesta(false, "", "");
            }
            cr.ac.una.evacomunaws.controller.TarUsuarioDto tarUsuarioDto = (cr.ac.una.evacomunaws.controller.TarUsuarioDto) evaComUNAWs.guardarUsuario(usuario);
            return new Respuesta(true, "", "", "TarUsuario", tarUsuarioDto);
        } catch (Exception ex) {
            Logger.getLogger(TarUsuarioService.class.getName()).log(Level.SEVERE, "Error guardando el usuario.", ex);
            return new Respuesta(false, "Error guardando el usuario.", "guardarUsuario " + ex.getMessage());
        }
    }
    
     public Respuesta getUsuario(Long id) {
        try {
            //TODO
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            evaComUNAWs.getUsuarioClass(id);
            if (false) {
                return new Respuesta(false, "", "");
            }
            cr.ac.una.evacomunaws.controller.TarUsuarioDto tarUsuarioDto = (cr.ac.una.evacomunaws.controller.TarUsuarioDto) evaComUNAWs.getUsuarioClass(id);
            return new Respuesta(true, "", "", "TarUsuario", tarUsuarioDto);
        } catch (Exception ex) {
            Logger.getLogger(TarUsuarioService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "getUsuario" + ex.getMessage());
        }
    }
}
