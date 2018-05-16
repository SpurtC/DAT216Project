package classes;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MittKontoMainController extends Controller{

    @FXML
    Pane minaUppgifterPane, tidigareKopPane, minaFavoriterPane;

    @FXML
    StackPane stackPaneMittKonto;

    List<String> fxmlFileListMittKonto = new ArrayList<>();
    Map<String, Pane> stringPaneMapMittKonto = new HashMap<>();

    SPManager spManager;


    public void makeAnFxmlList () {
        fxmlFileListMittKonto.add("../fxml/minaUppgifter.fxml");
        fxmlFileListMittKonto.add("../fxml/tidigareKop.fxml");
        fxmlFileListMittKonto.add("../fxml/minaFavoriter.fxml");
    }

    public void makeAMapMittKonto () {
        stringPaneMapMittKonto.put("minaUppgifterPane", minaUppgifterPane );
        stringPaneMapMittKonto.put("tidigareKopPane", tidigareKopPane );
        stringPaneMapMittKonto.put("minaFavoriterPane", minaFavoriterPane );
    }

    @FXML
    public void onMinaFavoriterClicked() {
        spManager.showPane("../fxml/minaFavoriter.fxml");
    }

    @FXML
    public void onTidigareKopClicked() {
        spManager.showPane("../fxml/tidigareKop.fxml");
    }

    @FXML
    public void onMinaUppgifterClicked() {
        spManager.showPane("../fxml/minaUppgifter.fxml");
    }

    @Override
    public void init() {
        makeAnFxmlList();
        makeAMapMittKonto();
        spManager = new SPManager(stackPaneMittKonto, fxmlFileListMittKonto);
    }

    @Override
    public void opened() {
        spManager.showPane("../fxml/minaUppgifter.fxml");

    }
}
