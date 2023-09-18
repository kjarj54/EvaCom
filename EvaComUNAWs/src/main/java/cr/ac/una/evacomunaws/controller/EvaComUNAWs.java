/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package cr.ac.una.evacomunaws.controller;

import cr.ac.una.evacomunaws.model.TarCaracteristicaDto;
import cr.ac.una.evacomunaws.model.TarCompetenciaDto;
import cr.ac.una.evacomunaws.model.TarCompetenciaevaluarDto;
import cr.ac.una.evacomunaws.model.TarEvaluadorDto;
import cr.ac.una.evacomunaws.model.TarParametrosDto;
import cr.ac.una.evacomunaws.model.TarProcesoevaluacionDto;
import cr.ac.una.evacomunaws.model.TarPuestoDto;
import cr.ac.una.evacomunaws.model.TarTrabajadorevaluarDto;
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
import cr.ac.una.evacomunaws.util.CodigoRespuesta;
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

    /**
     * *****************************************************************************************
     *
     *
     * This is web service operation para Usuarios
     *
     *
     *
     ******************************************************************************************
     *
     */
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

    @WebMethod(operationName = "getUsuarioId")
    public Long getUsuarioId(@WebParam(name = "usuario") String usuario, @WebParam(name = "clave") String clave) {
        try {
            Respuesta res = tarUsuarioService.validarUsuario(usuario, clave);
            if (!res.getEstado()) {
                return res.getCodigoRespuesta().getValue().longValue();//TODO
            }
            TarUsuarioDto tarUsuarioDto = (TarUsuarioDto) res.getResultado("Usuario");
            return tarUsuarioDto.getUsuId();//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return CodigoRespuesta.ERROR_INTERNO.getValue().longValue();//TODO
        }
    }

    @WebMethod(operationName = "guardarUsuario")
    public TarUsuarioDto guardarUsuario(@WebParam(name = "usuarioDto") TarUsuarioDto usuarioDto) {
        try {
            Respuesta res = tarUsuarioService.guardarUsuario(usuarioDto);
            if (!res.getEstado()) {
                return TarUsuarioDto.class.cast(res);//TODO
            }
            return usuarioDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarUsuarioDto.class.cast(ex);//TODO
        }
    }
    
    
     @WebMethod(operationName = "EliminarUsuario")
    public TarUsuarioDto EliminarUsuario(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarUsuarioService.eliminarUsuario(id);
            if (!res.getEstado()) {
                return TarUsuarioDto.class.cast(res);//TODO
            }
            TarUsuarioDto tarAgendasDto = (TarUsuarioDto) res.getResultado("Agenda");
            return tarAgendasDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarUsuarioDto.class.cast(ex);//TODO
        }
    }

    /**
     * *****************************************************************************************
     *
     *
     * This is web service operation para Parametros
     *
     *
     *
     ******************************************************************************************
     */
    @WebMethod(operationName = "guardarParametros")
    public TarParametrosDto guardarParametros(@WebParam(name = "tarParametrosDto") TarParametrosDto tarParametrosDto) {
        try {
            Respuesta res = tarParametrosService.guardarParametros(tarParametrosDto);
            if (!res.getEstado()) {
                return TarParametrosDto.class.cast(res);//TODO
            }
            return tarParametrosDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarParametrosDto.class.cast(ex);//TODO
        }
    }
    
    @WebMethod(operationName = "getParametrosClass")
    public TarParametrosDto getParametrosClass(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarParametrosService.getParametro(id);
            if (!res.getEstado()) {
                return TarParametrosDto.class.cast(res);//TODO
            }
            TarParametrosDto tarParametrosDto = (TarParametrosDto) res.getResultado("");
            return tarParametrosDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarParametrosDto.class.cast(ex);//TODO
        }
    }

    /**
     * *****************************************************************************************
     *
     *
     * This is web service operation para Caracteristica
     *
     *
     *
     ******************************************************************************************
     */
    @WebMethod(operationName = "guardarCaracteristica")
    public TarCaracteristicaDto guardarCaracteristica(@WebParam(name = "tarCaracteristicaDto") TarCaracteristicaDto tarCaracteristicaDto) {
        try {
            Respuesta res = tarCaracteristicaService.guardarCaracteristica(tarCaracteristicaDto);
            if (!res.getEstado()) {
                return TarCaracteristicaDto.class.cast(res);//TODO
            }
            return tarCaracteristicaDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarCaracteristicaDto.class.cast(ex);//TODO
        }
    }

    /**
     * *****************************************************************************************
     *
     *
     * This is web service operation para Competencia
     *
     *
     *
     ******************************************************************************************
     * 
     */
    
    @WebMethod(operationName = "guardarCompetencia")
    public TarCompetenciaDto guardarCompetencia(@WebParam(name = "tarCompetenciaDto") TarCompetenciaDto tarCompetenciaDto) {
        try {
            Respuesta res = tarCompetenciaService.guardarCompetencia(tarCompetenciaDto);
            if (!res.getEstado()) {
                return TarCompetenciaDto.class.cast(res);//TODO
            }
            return tarCompetenciaDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarCompetenciaDto.class.cast(ex);//TODO
        }
    }
    
    
    
    
    /**
     * *****************************************************************************************
     *
     *
     * This is web service operation para Competenciaevaluar
     *
     *
     *
     ******************************************************************************************
     */
    
    @WebMethod(operationName = "guardarCompetenciaevaluar")
    public TarCompetenciaevaluarDto guardarCompetenciaevaluar(@WebParam(name = "tarCompetenciaevaluarDto") TarCompetenciaevaluarDto tarCompetenciaevaluarDto) {
        try {
            Respuesta res = tarCompetenciaevaluarService.guardarCompetenciaEvaluar(tarCompetenciaevaluarDto);
            if (!res.getEstado()) {
                return TarCompetenciaevaluarDto.class.cast(res);//TODO
            }
            return tarCompetenciaevaluarDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarCompetenciaevaluarDto.class.cast(ex);//TODO
        }
    }
    
    /**
     * *****************************************************************************************
     *
     *
     * This is web service operation para Evaluador
     *
     *
     *
     ******************************************************************************************
     */
    
    
    @WebMethod(operationName = "guardarEvaluador")
    public TarEvaluadorDto guardarEvaluador(@WebParam(name = "tarEvaluadorDto") TarEvaluadorDto tarEvaluadorDto) {
        try {
            Respuesta res = tarEvaluadorService.guardarEvaluador(tarEvaluadorDto);
            if (!res.getEstado()) {
                return TarEvaluadorDto.class.cast(res);//TODO
            }
            return tarEvaluadorDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarEvaluadorDto.class.cast(ex);//TODO
        }
    }
    
    /**
     * *****************************************************************************************
     *
     *
     * This is web service operation para Procesoevaluacion
     *
     *
     *
     ******************************************************************************************
     */
    
    @WebMethod(operationName = "guardarProcesoevaluacion")
    public TarProcesoevaluacionDto guardarProcesoevaluacion(@WebParam(name = "tarProcesoevaluacionDto") TarProcesoevaluacionDto tarProcesoevaluacionDto) {
        try {
            Respuesta res = tarProcesoevaluacionService.guardarProcesoEvaluacion(tarProcesoevaluacionDto);
            if (!res.getEstado()) {
                return TarProcesoevaluacionDto.class.cast(res);//TODO
            }
            return tarProcesoevaluacionDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarProcesoevaluacionDto.class.cast(ex);//TODO
        }
    }
    
    
    /**
     * *****************************************************************************************
     *
     *
     * This is web service operation para Puesto
     *
     *
     *
     ******************************************************************************************
     */
    
    @WebMethod(operationName = "guardarPuesto")
    public TarPuestoDto guardarPuesto(@WebParam(name = "tarPuestoDto") TarPuestoDto tarPuestoDto) {
        try {
            Respuesta res = tarPuestoService.guardarPuesto(tarPuestoDto);
            if (!res.getEstado()) {
                return TarPuestoDto.class.cast(res);//TODO
            }
            return tarPuestoDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarPuestoDto.class.cast(ex);//TODO
        }
    }
    
    /**
     * *****************************************************************************************
     *
     *
     * This is web service operation para Trabajadorevaluar
     *
     *
     *
     ******************************************************************************************
     */
    
    
    @WebMethod(operationName = "guardarTrabajadorevaluar")
    public TarTrabajadorevaluarDto guardarTrabajadorevaluar(@WebParam(name = "tarTrabajadorevaluarDto") TarTrabajadorevaluarDto tarTrabajadorevaluarDto) {
        try {
            Respuesta res = tarTrabajadorevaluarService.guardarTrabajadorEvaluar(tarTrabajadorevaluarDto);
            if (!res.getEstado()) {
                return TarTrabajadorevaluarDto.class.cast(res);//TODO
            }
            return tarTrabajadorevaluarDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarTrabajadorevaluarDto.class.cast(ex);//TODO
        }
    }
    
    
}
