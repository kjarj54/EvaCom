package cr.ac.una.evacomuna;

import cr.ac.una.evacomuna.util.FlowController;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;
import org.apache.commons.compress.utils.IOUtils;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage, null);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/cr/ac/una/evacomuna/resources/LogoUNArojo.png")));
        stage.setTitle("EvaComUNA");
        FlowController.getInstance().goMain();

//        try { // Call Web Service Operation
//            cr.ac.una.evacomunaws.controller.EvaComUNAWs_Service service = new cr.ac.una.evacomunaws.controller.EvaComUNAWs_Service();
//            cr.ac.una.evacomunaws.controller.EvaComUNAWs port = service.getEvaComUNAWsPort();
//            // TODO initialize WS operation arguments here
//            cr.ac.una.evacomunaws.controller.TarParametrosDto tarParametrosDto = new cr.ac.una.evacomunaws.controller.TarParametrosDto();
//            // TODO process result here
//            tarParametrosDto.setParClave("cine1234");
//            tarParametrosDto.setParEmail("CineUna123@outlook.com");
//            tarParametrosDto.setParNombre("ajsdkjhas");      
//            String rutaArchivo = "D:/carpetaU/Segundo Semestre 2023/Progra3/Tarea1/EvaCom/plantilla.html";
//            FileInputStream fis = new FileInputStream(rutaArchivo);
//            BufferedInputStream bis = new BufferedInputStream(fis);
//
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            byte[] buffer = new byte[1024]; // Tamaño del buffer
//
//            int bytesRead;
//            while ((bytesRead = bis.read(buffer)) != -1) {
//                bos.write(buffer, 0, bytesRead);
//            }
//
//            byte[] bytes = bos.toByteArray();
//            tarParametrosDto.setParHtml(bytes);
//            
//            File file = new File("D:\\carpetaU\\Segundo Semestre 2023\\Progra3\\Tarea1\\EvaCom\\imagen.jpg");
//            
//            byte[] imageBytes = SaveImage(file);
//            tarParametrosDto.setParLogo(imageBytes);
//            
//            cr.ac.una.evacomunaws.controller.TarParametrosDto result = port.guardarParametros(tarParametrosDto);
//            System.out.println("Result = " + result);
//        } catch (Exception ex) {
//            // TODO handle custom exceptions here
//        }

    }

    public static void main(String[] args) {
        launch();
    }

    private static byte[] SaveImage(File file) throws IOException {
        FileInputStream fiStream = new FileInputStream(file.getAbsolutePath());
        byte[] imageInBytes = IOUtils.toByteArray(fiStream);
        return imageInBytes;
    }
    
}
