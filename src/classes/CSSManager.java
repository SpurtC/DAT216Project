package classes;

import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Map;

public class CSSManager {

    Map<String, Pane> stringPaneMap;

    

    public CSSManager (Map<String, Pane> nameToPaneMap) {
        this.stringPaneMap = nameToPaneMap;
    }

    public void changeCSS(String paneName, String notClicked, String clickedOne) {
        for(Pane pane : stringPaneMap.values()) {
             pane.getStyleClass().clear();
             pane.getStyleClass().add(notClicked);

        }
        Pane pane = stringPaneMap.get(paneName);
        pane.getStyleClass().clear();
        pane.getStyleClass().add(clickedOne);
    }
}