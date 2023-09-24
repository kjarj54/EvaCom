package cr.ac.una.evacomuna.controller;

import cr.ac.una.evacomuna.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P01_PrincipalViewController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Stage stage1 = FlowController.getInstance().getMainStage();
        stage1.setOnShown(event -> {
            // Luego de que la escena se muestre, llamar la siguiente pantalla
           FlowController.getInstance().goView("P02_LogInView");
        });
    }    

    @Override
    public void initialize() {
    }
    
}
