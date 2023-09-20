/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.service;

import cr.ac.una.evacomuna.model.TarUsuarioDto;
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
            TarUsuarioDto tarUsuarioDto = new TarUsuarioDto((cr.ac.una.evacomunaws.controller.TarUsuarioDto) evaComUNAWs.guardarUsuario(usuario));
            return new Respuesta(true, "", "", "TarUsuario", tarUsuarioDto);
        } catch (Exception ex) {
            Logger.getLogger(TarUsuarioService.class.getName()).log(Level.SEVERE, "Error guardando el usuario.", ex);
            return new Respuesta(false, "Error guardando el usuario.", "guardarUsuario " + ex.getMessage());
        }
    }

    public Respuesta getUsuario(String usuario, String clave) {

        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            evaComUNAWs.logIn(usuario, clave);
            if (evaComUNAWs.logIn(usuario, clave).equals(false)) {
                return new Respuesta(false, "", "");
            }
            TarUsuarioDto tarUsuarioDto = new TarUsuarioDto((cr.ac.una.evacomunaws.controller.TarUsuarioDto) evaComUNAWs.logIn(usuario, clave));

            return new Respuesta(true, "", "", "TarUsuario", tarUsuarioDto);
        } catch (Exception ex) {
            Logger.getLogger(TarUsuarioService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + usuario + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "getUsuario" + ex.getMessage());
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
            TarUsuarioDto tarUsuarioDto = new TarUsuarioDto((cr.ac.una.evacomunaws.controller.TarUsuarioDto) evaComUNAWs.getUsuarioClass(id));
            return new Respuesta(true, "", "", "TarUsuario", tarUsuarioDto);
        } catch (Exception ex) {
            Logger.getLogger(TarUsuarioService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "getUsuario" + ex.getMessage());
        }
    }

    public Respuesta eliminarUsuario(Long id) {

        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            // TODO process result here
            TarUsuarioDto tarUsuarioDto = new TarUsuarioDto((cr.ac.una.evacomunaws.controller.TarUsuarioDto) evaComUNAWs.eliminarUsuario(id));
            return new Respuesta(true, "", "", "TarUsuario", tarUsuarioDto);
        } catch (Exception ex) {
            Logger.getLogger(TarUsuarioService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "getUsuario" + ex.getMessage());
        }

    }

    public Respuesta recuperarClave(String correo) {//Devuelve un boolean

        try { // Call Web Service Operation
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            // TODO process result here
            return new Respuesta(true, "", "", "TarUsuario", evaComUNAWs.recuperarClave(correo));
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            Logger.getLogger(TarUsuarioService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + correo + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "getUsuario" + ex.getMessage());
        }

    }

}
