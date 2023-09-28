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

        String mensaje = convertirBytesAHTML(html);
        String recuperacionMensaje = "Hola por parte de "+nombre+" se generar una nueva clave! La clave nueva es: " + claveRestaurada + "  Atte: " + nombre;

        mensaje = mensaje.replace("{Insertar nombre de la empresa}", nombre);

        mensaje = mensaje.replace("{Contenido que se le vaya a enviar}", recuperacionMensaje);

        return mensaje;
    }

    public static String convertirBytesAHTML(byte[] bytes) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(bytes);
        String html = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        return html;
    }

    public String mensajeEmail(TarUsuarioDto tarUsuarioDto, byte[] html, byte[] logo, String nombre) throws UnknownHostException, IOException {
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
        
        return mensaje;
                /*"<head>\n"
                + "  <!--[if gte mso 9]>\n"
                + "<xml>\n"
                + "  <o:OfficeDocumentSettings>\n"
                + "    <o:AllowPNG/>\n"
                + "    <o:PixelsPerInch>96</o:PixelsPerInch>\n"
                + "  </o:OfficeDocumentSettings>\n"
                + "</xml>\n"
                + "<![endif]-->\n"
                + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "  <meta name=\"x-apple-disable-message-reformatting\">\n"
                + "  <!--[if !mso]><!-->\n"
                + "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "  <!--<![endif]-->\n"
                + "  <title></title>\n"
                + "\n"
                + "  <style type=\"text/css\">\n"
                + "    @media only screen and (min-width: 620px) {\n"
                + "      .u-row {\n"
                + "        width: 600px !important;\n"
                + "      }\n"
                + "\n"
                + "      .u-row .u-col {\n"
                + "        vertical-align: top;\n"
                + "      }\n"
                + "\n"
                + "      .u-row .u-col-100 {\n"
                + "        width: 600px !important;\n"
                + "      }\n"
                + "\n"
                + "    }\n"
                + "\n"
                + "    @media (max-width: 620px) {\n"
                + "      .u-row-container {\n"
                + "        max-width: 100% !important;\n"
                + "        padding-left: 0px !important;\n"
                + "        padding-right: 0px !important;\n"
                + "      }\n"
                + "\n"
                + "      .u-row .u-col {\n"
                + "        min-width: 320px !important;\n"
                + "        max-width: 100% !important;\n"
                + "        display: block !important;\n"
                + "      }\n"
                + "\n"
                + "      .u-row {\n"
                + "        width: calc(100% - 40px) !important;\n"
                + "      }\n"
                + "\n"
                + "      .u-col {\n"
                + "        width: 100% !important;\n"
                + "      }\n"
                + "\n"
                + "      .u-col>div {\n"
                + "        margin: 0 auto;\n"
                + "      }\n"
                + "    }\n"
                + "\n"
                + "    body {\n"
                + "      margin: 0;\n"
                + "      padding: 0;\n"
                + "    }\n"
                + "\n"
                + "    table,\n"
                + "    tr,\n"
                + "    td {\n"
                + "      vertical-align: top;\n"
                + "      border-collapse: collapse;\n"
                + "    }\n"
                + "\n"
                + "    p {\n"
                + "      margin: 0;\n"
                + "    }\n"
                + "\n"
                + "    .ie-container table,\n"
                + "    .mso-container table {\n"
                + "      table-layout: fixed;\n"
                + "    }\n"
                + "\n"
                + "    * {\n"
                + "      line-height: inherit;\n"
                + "    }\n"
                + "\n"
                + "    a[x-apple-data-detectors='true'] {\n"
                + "      color: inherit !important;\n"
                + "      text-decoration: none !important;\n"
                + "    }\n"
                + "\n"
                + "    table,\n"
                + "    td {\n"
                + "      color: #000000;\n"
                + "    }\n"
                + "\n"
                + "    @media (max-width: 480px) {\n"
                + "      #u_content_heading_1 .v-font-size {\n"
                + "        font-size: 33px !important;\n"
                + "      }\n"
                + "\n"
                + "      #u_content_text_1 .v-container-padding-padding {\n"
                + "        padding: 40px 10px 10px !important;\n"
                + "      }\n"
                + "\n"
                + "      #u_content_text_2 .v-container-padding-padding {\n"
                + "        padding: 8px 10px 40px !important;\n"
                + "      }\n"
                + "    }\n"
                + "  </style>\n"
                + "\n"
                + "\n"
                + "\n"
                + "  <!--[if !mso]><!-->\n"
                + "  <link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "  <link href=\"https://fonts.googleapis.com/css?family=Pacifico&display=swap\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "  <link href=\"https://fonts.googleapis.com/css2?family=Anton&display=swap\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "  <!--<![endif]-->\n"
                + "\n"
                + "</head>\n"
                + "\n"
                + "<body class=\"clean-body u_body\"\n"
                + "  style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #ffffff;color: #000000\">\n"
                + "  <!--[if IE]><div class=\"ie-container\"><![endif]-->\n"
                + "  <!--[if mso]><div class=\"mso-container\"><![endif]-->\n"
                + "  <table\n"
                + "    style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #ffffff;width:100%\"\n"
                + "    cellpadding=\"0\" cellspacing=\"0\">\n"
                + "    <tbody>\n"
                + "      <tr style=\"vertical-align: top\">\n"
                + "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
                + "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #ffffff;\"><![endif]-->\n"
                + "\n"
                + "\n"
                + "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "            <div class=\"u-row\"\n"
                + "              style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #2f3031;\">\n"
                + "              <div\n"
                + "                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n"
                + "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #2f3031;\"><![endif]-->\n"
                + "\n"
                + "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "                <div class=\"u-col u-col-100\"\n"
                + "                  style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n"
                + "                  <div style=\"height: 100%;width: 100% !important;\">\n"
                + "                    <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    <div\n"
                + "                      style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n"
                + "                      <!--<![endif]-->\n"
                + "\n"
                + "                      <table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\"\n"
                + "                        cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td class=\"v-container-padding-padding\"\n"
                + "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\"\n"
                + "                              align=\"left\">\n"
                + "\n"
                + "                              <div style=\"line-height: 140%; text-align: left; word-wrap: break-word;\">\n"
                + "                                <p style=\"font-size: 14px; line-height: 140%; text-align: center;\"><span\n"
                + "                                    style=\"font-size: 40px; line-height: 56px; font-family: Anton; color: #ecf0f1;\">EvaComUNA</span>\n"
                + "                                </p>\n"
                + "                              </div>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    </div>\n"
                + "                    <!--<![endif]-->\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "                <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "            <div class=\"u-row\"\n"
                + "              style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\n"
                + "              <div\n"
                + "                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n"
                + "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: transparent;\"><![endif]-->\n"
                + "\n"
                + "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
                + "                <div class=\"u-col u-col-100\"\n"
                + "                  style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n"
                + "                  <div\n"
                + "                    style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                    <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    <div\n"
                + "                      style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                      <!--<![endif]-->\n"
                + "\n"
                + "                      <table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\"\n"
                + "                        cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td class=\"v-container-padding-padding\"\n"
                + "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:0px;font-family:arial,helvetica,sans-serif;\"\n"
                + "                              align=\"left\">\n"
                + "\n"
                + "                              <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                + "                                <tr>\n"
                + "                                  <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n"
                + "\n"
                + "                                    <img align=\"center\" border=\"0\" src=\"https://etalent.com.mx/images/e_talent_profile/evaluacion-360-imagen.jpg\"\n"
                + "                                      alt=\"Hero Image\" title=\"Hero Image\"\n"
                + "                                      style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 100%;max-width: 100%;\"\n"
                + "                                      width=\"100\" />\n"
                + "\n"
                + "                                  </td>\n"
                + "                                </tr>\n"
                + "                              </table>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    </div>\n"
                + "                    <!--<![endif]-->\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "                <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "            <div class=\"u-row\"\n"
                + "              style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #2f3031;\">\n"
                + "              <div\n"
                + "                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-image: url('%20');background-repeat: no-repeat;background-position: center top;background-color: transparent;\">\n"
                + "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-image: url('%20');background-repeat: no-repeat;background-position: center top;background-color: #2f3031;\"><![endif]-->\n"
                + "\n"
                + "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
                + "                <div class=\"u-col u-col-100\"\n"
                + "                  style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n"
                + "                  <div\n"
                + "                    style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                    <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    <div\n"
                + "                      style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                      <!--<![endif]-->\n"
                + "\n"
                + "                      <table id=\"u_content_heading_1\" style=\"font-family:arial,helvetica,sans-serif;\"\n"
                + "                        role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td class=\"v-container-padding-padding\"\n"
                + "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:30px 10px;font-family:arial,helvetica,sans-serif;\"\n"
                + "                              align=\"left\">\n"
                + "\n"
                + "                              <h1 class=\"v-font-size\"\n"
                + "                                style=\"margin: 0px; color: #ffffff; line-height: 140%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: 'Montserrat',sans-serif; font-size: 31px;\">\n"
                + "                                <strong>EMAIL DE ACTIVACION</strong>\n"
                + "                              </h1>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    </div>\n"
                + "                    <!--<![endif]-->\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "                <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "            <div class=\"u-row\"\n"
                + "              style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #fbfbfb;\">\n"
                + "              <div\n"
                + "                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n"
                + "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #fbfbfb;\"><![endif]-->\n"
                + "\n"
                + "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
                + "                <div class=\"u-col u-col-100\"\n"
                + "                  style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n"
                + "                  <div\n"
                + "                    style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                    <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    <div\n"
                + "                      style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                      <!--<![endif]-->\n"
                + "\n"
                + "                      <table id=\"u_content_text_1\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\"\n"
                + "                        cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td class=\"v-container-padding-padding\"\n"
                + "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:50px 35px 10px 40px;font-family:arial,helvetica,sans-serif;\"\n"
                + "                              align=\"left\">\n"
                + "\n"
                + "                              <div style=\"color: #000000; line-height: 180%; text-align: left; word-wrap: break-word;\">\n"
                + "                                <p style=\"font-size: 14px; line-height: 180%;\"><span\n"
                + "                                    style=\"font-size: 18px; line-height: 32.4px; color: #000000;\"><strong><span\n"
                + "                                        style=\"line-height: 32.4px; font-family: Montserrat, sans-serif; font-size: 18px;\">BIENVENID@</span></strong></span>\n"
                + "                                </p>\n"
                + "                                <p style=\"font-size: 14px; line-height: 180%;\"> </p>\n"
                + "                                <p style=\"font-size: 14px; line-height: 180%;\"><span\n"
                + "                                    style=\"font-size: 18px; line-height: 32.4px; color: #000000;\"><span\n"
                + "                                      style=\"line-height: 32.4px; font-family: Montserrat, sans-serif; font-size: 18px;\">Presione el link para activar su cuenta: http://" + obtenerIp() + ":8080/EvaComUNAWs/activacion.html?id=" + tarUsuarioDto.getUsuId() + "</span></span>\n"
                + "                                </p>\n"
                + "                                <p style=\"font-size: 14px; line-height: 180%;\"> </p>\n"
                + "                              </div>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <table id=\"u_content_text_2\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\"\n"
                + "                        cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td class=\"v-container-padding-padding\"\n"
                + "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:8px 35px 40px 40px;font-family:arial,helvetica,sans-serif;\"\n"
                + "                              align=\"left\">\n"
                + "\n"
                + "                              <div style=\"color: #000000; line-height: 180%; text-align: left; word-wrap: break-word;\">\n"
                + "                                <p style=\"font-size: 14px; line-height: 180%;\"> </p>\n"
                + "                                <p style=\"line-height: 180%; font-size: 14px;\"><br /><span\n"
                + "                                    style=\"font-size: 16px; line-height: 28.8px; font-family: Montserrat, sans-serif;\">ATTE:\n"
                + "                                    EvaComUNA,</span><br /><span style=\"font-size: 18px; line-height: 32.4px;\"><span\n"
                + "                                      style=\"line-height: 32.4px; font-family: Pacifico, cursive; font-size: 18px;\">EvaComUNA</span></span>\n"
                + "                                </p>\n"
                + "                              </div>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\"\n"
                + "                        cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td class=\"v-container-padding-padding\"\n"
                + "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:0px;font-family:arial,helvetica,sans-serif;\"\n"
                + "                              align=\"left\">\n"
                + "\n"
                + "                              <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                + "                                <tr>\n"
                + "                                  <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n"
                + "\n"
                + "                                    <img align=\"center\" border=\"0\" src=\"https://cdn.templates.unlayer.com/assets/1638520170619-12.png\"\n"
                + "                                      alt=\"border\" title=\"border\"\n"
                + "                                      style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 100%;max-width: 600px;\"\n"
                + "                                      width=\"600\" />\n"
                + "\n"
                + "                                  </td>\n"
                + "                                </tr>\n"
                + "                              </table>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    </div>\n"
                + "                    <!--<![endif]-->\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "                <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "            <div class=\"u-row\"\n"
                + "              style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #2f2f2f;\">\n"
                + "              <div\n"
                + "                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-image: url('%20');background-repeat: no-repeat;background-position: center top;background-color: transparent;\">\n"
                + "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-image: url('%20');background-repeat: no-repeat;background-position: center top;background-color: #2f2f2f;\"><![endif]-->\n"
                + "\n"
                + "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
                + "                <div class=\"u-col u-col-100\"\n"
                + "                  style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n"
                + "                  <div\n"
                + "                    style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                    <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    <div\n"
                + "                      style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                      <!--<![endif]-->\n"
                + "\n"
                + "                      <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    </div>\n"
                + "                    <!--<![endif]-->\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "                <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "            <div class=\"u-row\"\n"
                + "              style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\n"
                + "              <div\n"
                + "                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n"
                + "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: transparent;\"><![endif]-->\n"
                + "\n"
                + "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
                + "                <div class=\"u-col u-col-100\"\n"
                + "                  style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n"
                + "                  <div\n"
                + "                    style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                    <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    <div\n"
                + "                      style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                      <!--<![endif]-->\n"
                + "\n"
                + "                      <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    </div>\n"
                + "                    <!--<![endif]-->\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "                <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"
                + "          <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n"
                + "        </td>\n"
                + "      </tr>\n"
                + "    </tbody>\n"
                + "  </table>\n"
                + "  <!--[if mso]></div><![endif]-->\n"
                + "  <!--[if IE]></div><![endif]-->\n"
                + "</body>\n"
                + "\n"
                + "</html>";*/
    }
}
