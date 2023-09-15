/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package cr.ac.una.evacomunaws.controller;

import cr.ac.una.evacomunaws.model.TarUsuarioDto;
import cr.ac.una.evacomunaws.service.TarCaracteristicaService;
import cr.ac.una.evacomunaws.service.TarCompetenciaService;
import cr.ac.una.evacomunaws.service.TarCompetenciaevaluarService;
import cr.ac.una.evacomunaws.service.TarEvaluadorService;
import cr.ac.una.evacomunaws.service.TarParametrosService;
import cr.ac.una.evacomunaws.service.TarProcesoevaluacionService;
import cr.ac.una.evacomunaws.service.TarPuestoService;
import cr.ac.una.evacomunaws.service.TarTrabajadorevaluarService;
import cr.ac.una.evacomunaws.service.TarUsuarioService;
import cr.ac.una.evacomunaws.util.Respuesta;
import jakarta.ejb.EJB;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author kevin
 */
@WebService(serviceName = "EvaComUNAWs")
public class EvaComUNAWs {

    /**
     * This is a sample web service operation
     */
    
    @EJB
    TarCaracteristicaService tarCaracteristicaService;
    
    @EJB
    TarCompetenciaService tarCompetenciaService;
    
    @EJB
    TarCompetenciaevaluarService tarCompetenciaevaluarService;
    
    @EJB
    TarEvaluadorService tarEvaluadorService;
    
    @EJB
    TarParametrosService tarParametrosService;
    
    @EJB
    TarProcesoevaluacionService tarProcesoevaluacionService;
    
    @EJB
    TarPuestoService tarPuestoService;
    
    @EJB
    TarTrabajadorevaluarService tarTrabajadorevaluarService;
    
    @EJB
    TarUsuarioService tarUsuarioService;
            
            
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
 
    /*******************************************************************************************
     * 
     * 
     * This is web service operation para Usuarios
     * 
     * 
     * 
     *******************************************************************************************/
 
    @WebMethod(operationName = "getUsuario")
    public Boolean getUsuario(@WebParam(name = "usuario") String usuario, @WebParam(name = "clave") String clave) {
        try {
            Respuesta res = tarUsuarioService.validarUsuario(usuario, clave);
            if (!res.getEstado()) {
                return res.getEstado();
            }
            TarUsuarioDto pruebaDto = (TarUsuarioDto) res.getResultado("Usuario");
            return res.getEstado();
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     @WebMethod(operationName = "getUsuarioClass")
    public TarUsuarioDto getUsuarioClass(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarUsuarioService.getUsuario(id);
            if (!res.getEstado()) {
                return TarUsuarioDto.class.cast(res);//TODO
            }
            TarUsuarioDto tarUsuarioDto = (TarUsuarioDto) res.getResultado("Usuario");
            return tarUsuarioDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarUsuarioDto.class.cast(ex);//TODO
        }
    }
    
    
    
    /*******************************************************************************************
     * 
     * 
     * This is web service operation para TODO
     * 
     * 
     * 
     *******************************************************************************************/
    
}
