/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.service;

import cr.ac.una.evacomunaws.model.TarPuesto;
import cr.ac.una.evacomunaws.model.TarPuestoDto;
import cr.ac.una.evacomunaws.model.TarUsuario;
import cr.ac.una.evacomunaws.model.TarUsuarioDto;
import cr.ac.una.evacomunaws.util.CodigoRespuesta;
import cr.ac.una.evacomunaws.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.security.SecureRandom;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
@Stateless
@LocalBean
public class TarUsuarioService {

    private static final Logger LOG = Logger.getLogger(TarUsuarioService.class.getName());
    @PersistenceContext(unitName = "EvaComUNAPU")
    private EntityManager em;

    public Respuesta validarUsuario(String usuUsu, String usuClave) {
        try {
            Query qryActividad = em.createNamedQuery("TarUsuario.findByUsuClaveUsuario", TarUsuario.class);
            qryActividad.setParameter("usuUsu", usuUsu);
            qryActividad.setParameter("usuClave", usuClave);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", new TarUsuarioDto((TarUsuario) qryActividad.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un usuario con las credenciales ingresadas.", "validarUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario " + ex.getMessage());
        }
    }

    public Respuesta guardarUsuario(TarUsuarioDto tarUsuarioDto) {
        try {
            TarUsuario usuario;
            if (tarUsuarioDto.getUsuId() != null && tarUsuarioDto.getUsuId() > 0) {
                usuario = em.find(TarUsuario.class, tarUsuarioDto.getUsuId());
                if (usuario == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el usuario a modificar.", "guardarCaracteristica NoResultException");
                }
                usuario.actualizar(tarUsuarioDto);
                if (tarUsuarioDto.getPuestoDto() != null) {
                    TarPuesto puesto = em.find(TarPuesto.class, tarUsuarioDto.getPuestoDto().getPueId());
                    usuario.setPueId(puesto);
                }
                usuario = em.merge(usuario);
            } else {
                usuario = new TarUsuario(tarUsuarioDto);
                em.persist(usuario);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "TarUsuario", new TarUsuarioDto(usuario));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el usuario.", "guardarUsuario " + ex.getMessage());
        }
    }

    public Respuesta eliminarUsuario(Long id) {
        try {
            TarUsuario caracteristica;
            if (id != null && id > 0) {
                caracteristica = em.find(TarUsuario.class, id);
                if (caracteristica == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el usuario a eliminar.", "eliminarUsuario NoResultException");
                }
                em.remove(caracteristica);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el usuario a eliminar.", "eliminarUsuario NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el usuario porque tiene relaciones con otros registros.", "eliminarUsuario " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la caracteristica.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el usuario.", "eliminarUsuario " + ex.getMessage());
        }
    }

    public Respuesta getUsuarios() {
        try {
            Query qryUsuario = em.createNamedQuery("TarUsuario.findAll", TarUsuario.class);
            List<TarUsuario> usuarios = qryUsuario.getResultList();
            List<TarUsuarioDto> usuarioDto = new ArrayList<>();
            for (TarUsuario usuario : usuarios) {
                usuarioDto.add(new TarUsuarioDto(usuario));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", usuarioDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen usuarios con los criterios ingresados.", "getUsuarios NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar usuarios.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar usuarios.", "getUsuarios " + ex.getMessage());
        }
    }

    public Respuesta getUsuario(Long usuId) {
        try {
            Query qryCaracteristica = em.createNamedQuery("TarUsuario.findByUsuId", TarUsuario.class);
            qryCaracteristica.setParameter("usuId", usuId);

            TarUsuario tarUsuario = (TarUsuario) qryCaracteristica.getSingleResult();

            TarUsuarioDto dto = new TarUsuarioDto(tarUsuario);
            if (tarUsuario.getPueId() != null) {
                dto.setPuestoDto(new TarPuestoDto(tarUsuario.getPueId()));
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", dto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un usuario con el código ingresado.", "getUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "getUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "getUsuario " + ex.getMessage());
        }
    }
    
    public Respuesta activacionCuenta(Long usuId) {
        try {
            Query qryActividad = em.createNamedQuery("ProClientes.findByUsuId", TarUsuario.class);
            qryActividad.setParameter("usuId", usuId);
            TarUsuarioDto tarUsuariosDto = new TarUsuarioDto((TarUsuario) qryActividad.getSingleResult());
            tarUsuariosDto.setUsuActivo("A");
            TarUsuario tarUsuario;
            if (tarUsuariosDto.getUsuId()!= null && tarUsuariosDto.getUsuId() > 0) {
                tarUsuario = em.find(TarUsuario.class, tarUsuariosDto.getUsuId());
                if (tarUsuario == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el usuario a modificar.", "activacionCuenta NoResultException");
                }
                tarUsuario.actualizar(tarUsuariosDto);
                tarUsuario = em.merge(tarUsuario);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el usuario.", "activacionCuenta " + ex.getMessage());
        }
    }
    
    
    public Respuesta getUsuariosSinParametros() {
        try {
            Query qryUsuarios = em.createNamedQuery("ProClientes.findAll", TarUsuario.class);
            List<TarUsuario> usuarios = qryUsuarios.getResultList();

            List<TarUsuarioDto> clientesDto = new ArrayList<>();
            for (TarUsuario usuario : usuarios) {
                clientesDto.add(new TarUsuarioDto(usuario));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuarios", clientesDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen usuario con los criterios ingresados.", "getUsuariosSinParametros NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "getUsuariosSinParametros " + ex.getMessage());
        }
    }
    
    public Respuesta recuperarClave(String usuCorreo) {
        try {
            Query qryActividad = em.createNamedQuery("ProClientes.findByUsuCorreo", TarUsuario.class);
            qryActividad.setParameter("usuCorreo", usuCorreo);
            TarUsuarioDto tarUsuarioDto = new TarUsuarioDto((TarUsuario) qryActividad.getSingleResult());
            recuClave(tarUsuarioDto);
            TarUsuario tarUsuario;
            if (tarUsuarioDto.getUsuId()!= null && tarUsuarioDto.getUsuId() > 0) {
                tarUsuario = em.find(TarUsuario.class, tarUsuarioDto.getUsuId());
                if (tarUsuario == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el usuario a modificar.", "recuperarClave NoResultException");
                }
                tarUsuario.actualizar(tarUsuarioDto);
                tarUsuario = em.merge(tarUsuario);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el usuario.", "recuperarClave " + ex.getMessage());
        }
    }
    
    
    public static String generateRandomPassword(int len) {
        // Rango ASCII – alfanumérico (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // cada iteración del bucle elige aleatoriamente un carácter del dado
        // rango ASCII y lo agrega a la instancia `StringBuilder`
        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }
    
    
    public Respuesta recuClave(TarUsuarioDto tarUsuarioDto){
       int len = 8;
        //System.out.println(generateRandomPassword(len));

        try {
            //setea las propiedades del smtp para poder enviar los emails
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.outlook.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);

            //proporciona el correo y contrasena del correo con el que va a ser enviado
            String correoRemitente = "CineUna123@outlook.com";
            String passwordRemitente = "cine1234";
            //Optine el correo al cual va a ser enviado el mensaje
            String correoReceptor = tarUsuarioDto.getUsuCorreo();
            String asunto = "EvaComUNA";
            
            
            //Llama al metodo de creacion de contrasena y se la manda a la persona y luego se la setea para que la cambie
            String claveRestaurada = generateRandomPassword(len);
            
            String mensaje = "<div role=\"region\" tabindex=\"-1\" aria-label=\"Cuerpo del mensaje\" class=\"ulb23 UxthP GNqVo yxtKT allowTextSelection\">\n"
                    + "	<div>\n"
                    + "		<style type=\"text/css\">\n"
                    + "			<!--\n"
                    + "			.rps_ddee h1 {\n"
                    + "				color: #262626;\n"
                    + "				font-family: \"Enriqueta\", serif;\n"
                    + "				font-size: 30px;\n"
                    + "				line-height: 30px;\n"
                    + "				margin: 0 0 30px\n"
                    + "			}\n"
                    + "\n"
                    + "			.rps_ddee h2 {\n"
                    + "				color: #262626;\n"
                    + "				font-family: \"Enriqueta\", serif;\n"
                    + "				font-size: 18px;\n"
                    + "				font-weight: normal;\n"
                    + "				line-height: 18px;\n"
                    + "				margin: 5px;\n"
                    + "				padding: 5px 0 0\n"
                    + "			}\n"
                    + "\n"
                    + "			.rps_ddee p {\n"
                    + "				color: #333333;\n"
                    + "				font-family: Georgia, serif;\n"
                    + "				font-size: 16px;\n"
                    + "				line-height: 26px;\n"
                    + "				margin: 0 0 26px\n"
                    + "			}\n"
                    + "\n"
                    + "			.rps_ddee table {\n"
                    + "				border-collapse: collapse\n"
                    + "			}\n"
                    + "\n"
                    + "			.rps_ddee th,\n"
                    + "			.rps_ddee td {\n"
                    + "				text-align: left;\n"
                    + "				padding: 8px\n"
                    + "			}\n"
                    + "			-->\n"
                    + "		</style>\n"
                    + "		<div class=\"rps_ddee\">\n"
                    + "			<div>\n"
                    + "				<div style=\"width:600px; height:auto; margin:0px auto; border-radius:4px; border:1px lightgray solid\">\n"
                    + "					<div style=\"height:110px; background-color:#1A1A1A\">\n"
                    + "						<div style=\"top:35%; margin-left:40px\"><span\n"
                    + "								style=\"color:#FFFFFF; font-family:Enriqueta,serif; font-size:30px; font-weight:600\">Solicitud\n"
                    + "								de contraseña</span>\n"
                    + "						</div>\n"
                    + "					</div>\n"
                    + "					<div style=\"height: 200px; background-color: rgb(51, 51, 51) !important; padding: 40px;\"\n"
                    + "						data-ogsb=\"rgb(255, 255, 255)\">\n"
                    + "						<div style=\"height: 100%; border-radius: 10px; border: 1px solid transparent; background-color: rgb(63, 63, 63) !important;\"\n"
                    + "							data-ogsb=\"rgb(229, 229, 229)\">\n"
                    + "							<div style=\"color:#FFFFFF;margin:30px\">Hola por parte de EvaComUNA se generar una nueva clave!<br aria-hidden=\"true\"><br aria-hidden=\"true\">Su\n"
                    + "								clave nueva es: " + claveRestaurada + "<br aria-hidden=\\\"true\\\"><br\n"
                    + "									aria-hidden=\\\"true\\\">Atte: EvaComUNA:\n"
                    + "							</div>\n"
                    + "						</div>\n"
                    + "					</div>\n"
                    + "				</div>\n"
                    + "			</div>\n"
                    + "		</div>\n"
                    + "	</div>\n"
                    + "</div>";

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoRemitente));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
            message.setSubject(asunto);
            message.setText(mensaje, "ISO-8859-1", "html");

            Transport t = session.getTransport("smtp");
            t.connect(correoRemitente, passwordRemitente);
            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            t.close();
            
            tarUsuarioDto.setUsuClave(claveRestaurada);
            tarUsuarioDto.setUsuTempclave(claveRestaurada);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "","");

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el cliente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el cliente.", "recuClave " + ex.getMessage());
        } 
    }
    
}
