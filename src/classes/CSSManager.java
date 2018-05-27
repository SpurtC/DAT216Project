package classes;

import javafx.scene.layout.Pane;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSSManager {

    private Map<String, Pane> stringPaneMap;
    private Map<Date, HistoryItem> dateHistoryItemMap;

    

    public CSSManager (Map<String, Pane> nameToPaneMap) {
        this.stringPaneMap = nameToPaneMap;
    }

    CSSManager (Map<Date, HistoryItem> dateToHistoryMap, String katt) {
        this.dateHistoryItemMap = dateToHistoryMap;
    }

    public void changeCSS(String paneName, String notClickedCSSClass, String clickedOneCSSClass) {
        for(Pane pane : stringPaneMap.values()) {
             pane.getStyleClass().clear();
             pane.getStyleClass().add(notClickedCSSClass);

        }
        Pane pane = stringPaneMap.get(paneName);
        pane.getStyleClass().clear();
        pane.getStyleClass().add(clickedOneCSSClass);
    }


    public void changeCSSHistory (Date dateHistoryItem, String notClickedCSSClass, String clickedOneCSSClass) {
        for(Pane pane : dateHistoryItemMap.values()) {
            pane.getStyleClass().clear();
            pane.getStyleClass().add(notClickedCSSClass);
        }
        Pane pane = dateHistoryItemMap.get(dateHistoryItem);
        pane.getStyleClass().clear();
        pane.getStyleClass().add(clickedOneCSSClass);
    }
}