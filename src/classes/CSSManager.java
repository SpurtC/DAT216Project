package classes;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import javax.swing.text.html.CSS;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSSManager {

    List<String> CSSClasses = new ArrayList<>();

    Map<String, Pane> stringPaneMap;
    

    public CSSManager (Map<String, Pane> nameToPaneMap) {
        this.stringPaneMap = nameToPaneMap;

    }

    public void changeCSS (String paneName) {
        for(Pane pane : stringPaneMap.values()) {
             pane.getStyleClass().clear();
             pane.getStyleClass().add("upperPaneFill");

        }
        Pane pane = stringPaneMap.get(paneName);
        pane.getStyleClass().clear();
        pane.getStyleClass().add("upperPaneFillHover");
    }


    public void makeCSSClassesList () {
        CSSClasses.add("upperPaneFillHover");
        CSSClasses.add("upperPaneFill");
    }
}