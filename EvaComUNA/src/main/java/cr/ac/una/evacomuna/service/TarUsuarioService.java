/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomuna.service;

import cr.ac.una.evacomuna.model.TarUsuarioDto;
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
public class TarUsuarioService {

    EvaComUNAWs evaComUNAWs;

    public Respuesta guardarUsuario(cr.ac.una.evacomunaws.controller.TarUsuarioDto usuario) {
        try {
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
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
            return new Respuesta(true, "", "", "TarUsuario",  evaComUNAWs.eliminarUsuario(id));
        } catch (Exception ex) {
            Logger.getLogger(TarUsuarioService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "getUsuario" + ex.getMessage());
        }

    }

    public Respuesta getUsuarios(String cedula, String nombre, String usuario, String puesto) {
        try {
            EvaComUNAWs_Service canchaUNAWs_service = new EvaComUNAWs_Service();
            evaComUNAWs = canchaUNAWs_service.getEvaComUNAWsPort();
            
            List<cr.ac.una.evacomunaws.controller.TarUsuarioDto> tarUsuarioDtosSoap = evaComUNAWs.getUsuarios();
            
            List<TarUsuarioDto> tarUsuarioDtosList = new ArrayList<>();
            
            for(cr.ac.una.evacomunaws.controller.TarUsuarioDto item : tarUsuarioDtosSoap){
                TarUsuarioDto tarUsuarioDto = new TarUsuarioDto(item);
                tarUsuarioDtosList.add(tarUsuarioDto);
            }
            
            if (!cedula.isEmpty()) {
                System.out.println("-" + cedula + "-");
                tarUsuarioDtosList = tarUsuarioDtosList.stream().filter((p) -> p.getUsuCedula().contains(cedula)).collect(Collectors.toList());
            }
            if (!nombre.isEmpty()) {
                System.out.println("-" + nombre + "-");
                tarUsuarioDtosList = tarUsuarioDtosList.stream().filter((p) -> p.getUsuNombre().toLowerCase().contains(nombre.toLowerCase())).collect(Collectors.toList());
            }
            if (!usuario.isEmpty()) {
                System.out.println("-" + usuario + "-");
                tarUsuarioDtosList = tarUsuarioDtosList.stream().filter((p) -> p.getUsuUsu().toLowerCase().contains(usuario.toLowerCase())).collect(Collectors.toList());
            }
            if (!puesto.isEmpty()) {
                System.out.println("-" + puesto + "-");
                tarUsuarioDtosList = tarUsuarioDtosList.stream().filter((p) -> p.getPuestoDto()!= null && p.getPuestoDto().getPueNombre().toLowerCase().contains(puesto.toLowerCase())).collect(Collectors.toList());
            }
            
            return new Respuesta(true, "", "", "TarUsuario",tarUsuarioDtosList);

        } catch (Exception ex) {
            Logger.getLogger(TarUsuarioService.class.getName()).log(Level.SEVERE, "Error obteniendo los usuarios", ex);
            return new Respuesta(false, "Error obteniendo los usuarios.", "getUsuarios" + ex.getMessage());
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
