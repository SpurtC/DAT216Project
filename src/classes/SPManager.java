package classes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class SPManager {

    private Map<String, Page> map = new HashMap<>();

    public SPManager (StackPane stackPane, List<String> list) {
        for (String fxml : list) {
            Page page = loadFXML(fxml);
            map.put(fxml, page);
            stackPane.getChildren().add(page.PANE);
        }

        for( Page page: map.values()) {
            if(page.CONTROLLER != null) {
                page.CONTROLLER.init();
            }
        }
    }

    private Page loadFXML (String fxml) {                           //Load the right stackpane

        System.out.println("Loading" + fxml);

        ResourceBundle bundle = java.util.ResourceBundle.getBundle("iMat");

        FXMLLoader loader = new FXMLLoader(SPManager.class.getResource(fxml));
        Region root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(fxml + "; Controller is null: " + (loader.getController() == null) + "; Root is null: " + (root == null));

        return new Page((Pane) root, loader.getController());
    }

    public void showPane (String fxml) {            //Show right pane to front
        Pane pane = map.get(fxml).PANE;
        pane.toFront();

        for(Page otherPage : map.values()){
             if(otherPage.PANE != pane) {
                 otherPage.PANE.setOpacity(0.5);
             }
        }

        pane.setOpacity(1.0);
    }

    private class Page{
        public Page(Pane pane, Controller controller){
            this.PANE = pane;
            this.CONTROLLER = controller;
        }

        public final Pane PANE;
        public final Controller CONTROLLER;
    }

}
