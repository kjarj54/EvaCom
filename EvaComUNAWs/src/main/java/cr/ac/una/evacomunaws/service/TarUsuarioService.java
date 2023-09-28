/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.service;

import cr.ac.una.evacomunaws.model.TarEvaluador;
import cr.ac.una.evacomunaws.model.TarEvaluadorDto;
import cr.ac.una.evacomunaws.model.TarParametrosDto;
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
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

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
            TarUsuario tarUsuario = (TarUsuario) qryActividad.getSingleResult();

            TarUsuarioDto dto = new TarUsuarioDto(tarUsuario);
            if (tarUsuario.getPueId() != null) {
                dto.setPuestoDto(new TarPuestoDto(tarUsuario.getPueId()));
            }
            if (!tarUsuario.getTarEvaluadorList().isEmpty()) {
                List<TarEvaluadorDto> evaluadorDtoList = new ArrayList<>();

                for (TarEvaluador tarEvaluador : tarUsuario.getTarEvaluadorList()) {
                    // Convert TarEvaluador to TarEvaluadorDto
                    TarEvaluadorDto tarEvaluadorDto = new TarEvaluadorDto(tarEvaluador);

                    // Add tarEvaluadorDto to the list
                    evaluadorDtoList.add(tarEvaluadorDto);
                }

                dto.getTarEvaluadorList().addAll(evaluadorDtoList);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", dto);

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

                if (tarUsuarioDto.getPuestoDto() != null) {
                    TarPuesto puesto = em.find(TarPuesto.class, tarUsuarioDto.getPuestoDto().getPueId());
                    usuario.setPueId(puesto);
                }
                if (!tarUsuarioDto.getTarEvaluadorList().isEmpty()) {
                    List<TarEvaluador> evaluadorList = new ArrayList<>();

                    for (TarEvaluadorDto tarEvaluadorDto : tarUsuarioDto.getTarEvaluadorList()) {
                        TarEvaluador tarEvaluador = new TarEvaluador(tarEvaluadorDto);
                        evaluadorList.add(tarEvaluador);
                    }

                    // Add the new evaluators to the user entity's list
                    usuario.getTarEvaluadorList().addAll(evaluadorList);
                }
                usuario.actualizar(tarUsuarioDto);
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
            TarUsuario usuario;
            if (id != null && id > 0) {
                usuario = em.find(TarUsuario.class, id);
                if (usuario == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el usuario a eliminar.", "eliminarUsuario NoResultException");
                }
                em.remove(usuario);
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
            if (!tarUsuario.getTarEvaluadorList().isEmpty()) {
                List<TarEvaluadorDto> evaluadorDtoList = new ArrayList<>();

                for (TarEvaluador tarEvaluador : tarUsuario.getTarEvaluadorList()) {
                    // Convert TarEvaluador to TarEvaluadorDto
                    TarEvaluadorDto tarEvaluadorDto = new TarEvaluadorDto(tarEvaluador);

                    // Add tarEvaluadorDto to the list
                    evaluadorDtoList.add(tarEvaluadorDto);
                }

                dto.getTarEvaluadorList().addAll(evaluadorDtoList);
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
            Query qryActividad = em.createNamedQuery("TarUsuario.findByUsuId", TarUsuario.class);
            qryActividad.setParameter("usuId", usuId);
            TarUsuarioDto tarUsuariosDto = new TarUsuarioDto((TarUsuario) qryActividad.getSingleResult());
            tarUsuariosDto.setUsuActivo("A");
            TarUsuario tarUsuario;
            if (tarUsuariosDto.getUsuId() != null && tarUsuariosDto.getUsuId() > 0) {
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
            Query qryUsuarios = em.createNamedQuery("TarUsuario.findAll", TarUsuario.class);
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

    public Respuesta recuperarClave(String usuCorreo,TarParametrosDto parametrosDto) {
        try {
            Query qryActividad = em.createNamedQuery("TarUsuario.findByUsuCorreo", TarUsuario.class);
            qryActividad.setParameter("usuCorreo", usuCorreo);
            TarUsuarioDto tarUsuarioDto = new TarUsuarioDto((TarUsuario) qryActividad.getSingleResult());
            recuClave(tarUsuarioDto, parametrosDto);
            TarUsuario tarUsuario;
            if (tarUsuarioDto.getUsuId() != null && tarUsuarioDto.getUsuId() > 0) {
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

    public Respuesta correoActivacion(TarUsuarioDto tarUsuarioDto,TarParametrosDto parametrosDto) {
        try {
            //setea las propiedades del smtp para poder enviar los emails
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.outlook.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");


            Session session = Session.getDefaultInstance(props);

            //proporciona el correo y contrasena del correo con el que va a ser enviado
            String correoRemitente = parametrosDto.getParEmail();//"CineUna123@outlook.com";
            String passwordRemitente = parametrosDto.getParClave();//"cine1234";
            String correoReceptor = tarUsuarioDto.getUsuCorreo();
            String asunto = "EvaComUNA";

            //Mensaje que va a ser enviado
            String mensaje = mensajeEmail(tarUsuarioDto, parametrosDto.getParHtml(), parametrosDto.getParLogo(), parametrosDto.getParNombre());

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoRemitente));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
            message.setSubject(asunto);
            message.setText(mensaje, "ISO-8859-1", "html");

            Transport t = session.getTransport("smtp");
            t.connect(correoRemitente, passwordRemitente);
            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            t.close();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el cliente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el cliente.", "guardarCliente " + ex.getMessage());
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

    public Respuesta recuClave(TarUsuarioDto tarUsuarioDto,TarParametrosDto parametrosDto) {
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

            //Llamada a los parametros
  
            //proporciona el correo y contrasena del correo con el que va a ser enviado
            String correoRemitente = parametrosDto.getParEmail();//"CineUna123@outlook.com";
            String passwordRemitente = parametrosDto.getParClave();//"cine1234";
            //Optine el correo al cual va a ser enviado el mensaje
            String correoReceptor = tarUsuarioDto.getUsuCorreo();
            String asunto = "EvaComUNA";

            //Llama al metodo de creacion de contrasena y se la manda a la persona y luego se la setea para que la cambie
            String claveRestaurada = generateRandomPassword(len);

            String mensaje = mensajeClave(parametrosDto.getParHtml(), parametrosDto.getParLogo(), parametrosDto.getParNombre(), claveRestaurada);

            //Envio del mensaje
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
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "");

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el cliente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el cliente.", "recuClave " + ex.getMessage());
        }
    }

    public String obtenerIp() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        return ip.getHostAddress();
    }

    public String mensajeClave(byte[] html, byte[] logo, String nombre, String claveRestaurada) throws IOException {
        
        String base64Image = convertirABase64(logo);
        String mensaje = convertirBytesAHTML(html);
        String recuperacionMensaje = "Hola por parte de "+nombre+" se generar una nueva clave! La clave nueva es: " + claveRestaurada + "  Atte: " + nombre;

        mensaje = mensaje.replace("{Insertar nombre de la empresa}", nombre);

        mensaje = mensaje.replace("{Contenido que se le vaya a enviar}", recuperacionMensaje);
        
        mensaje = mensaje.replace("{imagen}", "data:image/png;base64,"+base64Image);

        return mensaje;
    }

    public static String convertirBytesAHTML(byte[] bytes) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(bytes);
        String html = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        return html;
    }
    
    public static String convertirABase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public String mensajeEmail(TarUsuarioDto tarUsuarioDto, byte[] html, byte[] logo, String nombre) throws UnknownHostException, IOException {
        
        String base64Image = convertirABase64(logo);
        String mensaje = convertirBytesAHTML(html);
        String activacionMensaje = "<p style=\"font-size: 14px; line-height: 180%;\"><span style=\"font-size: 18px; line-height: 32.4px; color: #000000;\">"
        + "<span style=\"font-size: 18px; line-height: 32.4px; color: #000000;\">"
        + "<span style=\"line-height: 32.4px; font-family: Montserrat, sans-serif; font-size: 18px;\">"
        + "Presione el link para activar su cuenta: "
        + "<a href=\"http://" + obtenerIp() + ":8080/EvaComUNAWs/activacion.html?id=" + tarUsuarioDto.getUsuId() + "\">"
        + "http://" + obtenerIp() + ":8080/EvaComUNAWs/activacion.html?id=" + tarUsuarioDto.getUsuId()
        + "</a>"
        + "</span>"
        + "</span>"
        + "</span>"
        + "</p>";
        mensaje = mensaje.replace("{Insertar nombre de la empresa}", nombre);
        mensaje = mensaje.replace("{Contenido que se le vaya a enviar}", activacionMensaje);
        mensaje = mensaje.replace("{imagen}", "data:image/png;base64,"+base64Image);
        
        return mensaje;
    }
}
