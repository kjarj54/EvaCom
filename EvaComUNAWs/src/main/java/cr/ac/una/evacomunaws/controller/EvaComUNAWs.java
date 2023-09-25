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
            return TarUsuarioDto.class.cast(ex).getModificado();
        }
    }

    @WebMethod(operationName = "logIn")
    public TarUsuarioDto logIn(@WebParam(name = "usuario") String usuario, @WebParam(name = "clave") String clave) {
        try {
            Respuesta res = tarUsuarioService.validarUsuario(usuario, clave);
            if (!res.getEstado()) {
                return TarUsuarioDto.class.cast(res);
            }
            TarUsuarioDto pruebaDto = (TarUsuarioDto) res.getResultado("Usuario");
            return pruebaDto;
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarUsuarioDto.class.cast(ex);
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
            tarUsuarioService.correoActivacion(usuarioDto);
            return usuarioDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarUsuarioDto.class.cast(ex);//TODO
        }
    }

    @WebMethod(operationName = "eliminarUsuario")
    public TarUsuarioDto eliminarUsuario(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarUsuarioService.eliminarUsuario(id);
            if (!res.getEstado()) {
                return TarUsuarioDto.class.cast(res);//TODO
            }
            TarUsuarioDto tarUsuarioDto = (TarUsuarioDto) res.getResultado("");
            return tarUsuarioDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarUsuarioDto.class.cast(ex);//TODO
        }
    }

    @WebMethod(operationName = "recuperarClave")
    public Boolean recuperarClave(@WebParam(name = "correo") String correo) {
        try {
            Respuesta res = tarUsuarioService.recuperarClave(correo);
            if (!res.getEstado()) {
                return TarUsuarioDto.class.cast(res).getModificado();//TODO
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarUsuarioDto.class.cast(ex).getModificado();//TODO
        }
    }

    @WebMethod(operationName = "activacionUsu")
    public String activacionUsu(@WebParam(name = "usuId") Long usuId) {
        try {
            Respuesta res = tarUsuarioService.activacionCuenta(usuId);
            if (!res.getEstado()) {
                return TarUsuarioDto.class.cast(res).toString();//TODO
            }
            return "<html><body><h1>Activation Successful</h1></body></html>";
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarUsuarioDto.class.cast(ex).toString();//TODO
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
            TarParametrosDto tarParametrosDto = (TarParametrosDto) res.getResultado("TarParametros");
            return tarParametrosDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarParametrosDto.class.cast(ex);//TODO
        }
    }

    @WebMethod(operationName = "eliminarParametros")
    public TarParametrosDto eliminarParametros(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarParametrosService.eliminarParametros(id);
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

    @WebMethod(operationName = "getCaracteristicaClass")
    public TarCaracteristicaDto getCaracteristicaClass(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarCaracteristicaService.getCaracteristica(id);
            if (!res.getEstado()) {
                return TarCaracteristicaDto.class.cast(res);//TODO
            }
            TarCaracteristicaDto tarCaracteristicaDto = (TarCaracteristicaDto) res.getResultado("Caracteristica");
            return tarCaracteristicaDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarCaracteristicaDto.class.cast(ex);//TODO
        }
    }

    @WebMethod(operationName = "eliminarCaracteristica")
    public TarCaracteristicaDto eliminarCaracteristica(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarCaracteristicaService.eliminarCaracteristica(id);
            if (!res.getEstado()) {
                return TarCaracteristicaDto.class.cast(res);//TODO
            }
            TarCaracteristicaDto tarCaracteristicaDto = (TarCaracteristicaDto) res.getResultado("");
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

    @WebMethod(operationName = "getCompetenciaClass")
    public TarCompetenciaDto getCompetenciaClass(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarCompetenciaService.getCompetencia(id);
            if (!res.getEstado()) {
                return TarCompetenciaDto.class.cast(res);//TODO
            }
            TarCompetenciaDto tarCompetenciaDto = (TarCompetenciaDto) res.getResultado("Competencia");
            return tarCompetenciaDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarCompetenciaDto.class.cast(ex);//TODO
        }
    }

    @WebMethod(operationName = "eliminarCompetencia")
    public TarCompetenciaDto eliminarCompetencia(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarCompetenciaService.eliminarCompetencia(id);
            if (!res.getEstado()) {
                return TarCompetenciaDto.class.cast(res);//TODO
            }
            TarCompetenciaDto tarCompetenciaDto = (TarCompetenciaDto) res.getResultado("");
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

    @WebMethod(operationName = "getCompetenciaevaluarClass")
    public TarCompetenciaevaluarDto getCompetenciaevaluarClass(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarCompetenciaevaluarService.getCompetenciaEvaluar(id);
            if (!res.getEstado()) {
                return TarCompetenciaevaluarDto.class.cast(res);//TODO
            }
            TarCompetenciaevaluarDto tarCompetenciaevaluarDto = (TarCompetenciaevaluarDto) res.getResultado("CompetenciaEvaluar");
            return tarCompetenciaevaluarDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarCompetenciaevaluarDto.class.cast(ex);//TODO
        }
    }

    @WebMethod(operationName = "eliminarCompetenciaevaluar")
    public TarCompetenciaevaluarDto eliminarCompetenciaevaluar(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarCompetenciaevaluarService.eliminarCompetenciaEvaluar(id);
            if (!res.getEstado()) {
                return TarCompetenciaevaluarDto.class.cast(res);//TODO
            }
            TarCompetenciaevaluarDto tarCompetenciaevaluarDto = (TarCompetenciaevaluarDto) res.getResultado("");
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

    @WebMethod(operationName = "getEvaluadorClass")
    public TarEvaluadorDto getEvaluadorClass(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarEvaluadorService.getEvaluador(id);
            if (!res.getEstado()) {
                return TarEvaluadorDto.class.cast(res);//TODO
            }
            TarEvaluadorDto tarEvaluadorDto = (TarEvaluadorDto) res.getResultado("Evaluador");
            return tarEvaluadorDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarEvaluadorDto.class.cast(ex);//TODO
        }
    }

    @WebMethod(operationName = "eliminarEvaluador")
    public TarEvaluadorDto eliminarEvaluador(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarEvaluadorService.eliminarEvaluador(id);
            if (!res.getEstado()) {
                return TarEvaluadorDto.class.cast(res);//TODO
            }
            TarEvaluadorDto tarEvaluadorDto = (TarEvaluadorDto) res.getResultado("");
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

    @WebMethod(operationName = "getProcesoevaluacionClass")
    public TarProcesoevaluacionDto getProcesoevaluacionClass(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarProcesoevaluacionService.getProcesoEvaluacion(id);
            if (!res.getEstado()) {
                return TarProcesoevaluacionDto.class.cast(res);//TODO
            }
            TarProcesoevaluacionDto tarProcesoevaluacionDto = (TarProcesoevaluacionDto) res.getResultado("TarProcesoevaluacion");
            return tarProcesoevaluacionDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarProcesoevaluacionDto.class.cast(ex);//TODO
        }
    }

    @WebMethod(operationName = "eliminarProcesoevaluacion")
    public TarProcesoevaluacionDto eliminarProcesoevaluacion(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarProcesoevaluacionService.eliminarProcesoEvaluacion(id);
            if (!res.getEstado()) {
                return TarProcesoevaluacionDto.class.cast(res);//TODO
            }
            TarProcesoevaluacionDto tarProcesoevaluacionDto = (TarProcesoevaluacionDto) res.getResultado("");
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

    @WebMethod(operationName = "getPuestoClass")
    public TarPuestoDto getPuestoClass(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarPuestoService.getPuesto(id);
            if (!res.getEstado()) {
                return TarPuestoDto.class.cast(res);//TODO
            }
            TarPuestoDto tarCaracteristicaDto = (TarPuestoDto) res.getResultado("Puesto");
            return tarCaracteristicaDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarPuestoDto.class.cast(ex);//TODO
        }
    }

    @WebMethod(operationName = "eliminarPuesto")
    public TarPuestoDto eliminarPuesto(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarPuestoService.eliminarPuesto(id);
            if (!res.getEstado()) {
                return TarPuestoDto.class.cast(res);//TODO
            }
            TarPuestoDto tarPuestoDto = (TarPuestoDto) res.getResultado("");
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

    @WebMethod(operationName = "getTrabajadorevaluarClass")
    public TarTrabajadorevaluarDto getTrabajadorevaluarClass(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarTrabajadorevaluarService.getTrabajadorEvaluar(id);
            if (!res.getEstado()) {
                return TarTrabajadorevaluarDto.class.cast(res);//TODO
            }
            TarTrabajadorevaluarDto tarCaracteristicaDto = (TarTrabajadorevaluarDto) res.getResultado("");
            return tarCaracteristicaDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarTrabajadorevaluarDto.class.cast(ex);//TODO
        }
    }

    @WebMethod(operationName = "eliminarTrabajadorevaluar")
    public TarTrabajadorevaluarDto eliminarTrabajadorevaluar(@WebParam(name = "id") Long id) {
        try {
            Respuesta res = tarTrabajadorevaluarService.eliminarTrabajadorEvaluar(id);
            if (!res.getEstado()) {
                return TarTrabajadorevaluarDto.class.cast(res);//TODO
            }
            TarTrabajadorevaluarDto tarTrabajadorevaluarDto = (TarTrabajadorevaluarDto) res.getResultado("TrabajadorEvaluar");
            return tarTrabajadorevaluarDto;//TODO
        } catch (Exception ex) {
            Logger.getLogger(EvaComUNAWs.class.getName()).log(Level.SEVERE, null, ex);
            return TarTrabajadorevaluarDto.class.cast(ex);//TODO
        }
    }

}
