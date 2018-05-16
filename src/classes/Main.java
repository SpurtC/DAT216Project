package classes;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;


public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        ResourceBundle bundle = java.util.ResourceBundle.getBundle("iMat");

        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/root.fxml"), bundle);
       // Parent root = (Parent) FXMLLoader.load(Main.class.getResource("/fxml/framsida.fxml"));

        FXMLLoader loader = new FXMLLoader(SPManager.class.getResource("/fxml/root.fxml"));
        Region root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 1440, 800);

        stage.setTitle(bundle.getString("application.name"));
        stage.setScene(scene);
        stage.show();
        Controller controller = loader.getController();
        controller.init();


    }
           /* @Override
            public void stop*/


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { launch(args); }


}