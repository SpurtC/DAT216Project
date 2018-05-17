package classes;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainWindowController extends Controller{

    @FXML private StackPane stackPaneMain;
    @FXML private Pane handlaPane, kundtjanstPane, mittKontoPane, varukorgPane;

    List<String> fxmlFileList = new ArrayList<>();
    static Map<String, Pane> stringPaneMap = new HashMap<>();

    public static SPManager spManager;
    public static CSSManager cssManager = new CSSManager(stringPaneMap);

    public List<String> getFxmlFilesList() {
        return fxmlFileList;
    }

    @Override
    public void init() {
        makeAfxmlList();
        makeAMap();

        spManager = new SPManager(stackPaneMain, fxmlFileList);
    }

    @Override
    public void opened() {
        spManager.showPane("../fxml/framsida.fxml");
        cssManager.changeCSS("handlaPane", "upperPaneFill", "upperPaneFillPressed");
    }

    public void makeAMap () {
        stringPaneMap.put("handlaPane", handlaPane );
        stringPaneMap.put("kundtjanstPane", kundtjanstPane );
        stringPaneMap.put("mittKontoPane", mittKontoPane );
        stringPaneMap.put("varukorgPane", varukorgPane );
    }

    private void makeAfxmlList() {
        fxmlFileList.add("../fxml/betalning.fxml");
        fxmlFileList.add("../fxml/confirmation.fxml");
        fxmlFileList.add("../fxml/framsida.fxml");
        fxmlFileList.add("../fxml/kundinformation.fxml");
        fxmlFileList.add("../fxml/mittKontoMain.fxml");
        fxmlFileList.add("../fxml/varukorg.fxml");
        fxmlFileList.add("../fxml/leveranstid.fxml");
        fxmlFileList.add("../fxml/betalning.fxml");
    }

    @FXML
    public void onKundtjanstClicked() {
        spManager.showPane("../fxml/kundinformation.fxml");
        cssManager.changeCSS("kundtjanstPane", "upperPaneFill", "upperPaneFillPressed");
    }

    @FXML
    public void onHandlaClicked() {
        spManager.showPane("../fxml/framsida.fxml");
        cssManager.changeCSS("handlaPane", "upperPaneFill", "upperPaneFillPressed");
    }

    @FXML
    public void onMittKontoClicked() {
        spManager.showPane("../fxml/mittKontoMain.fxml");
        cssManager.changeCSS("mittKontoPane", "upperPaneFill", "upperPaneFillPressed");
    }

    @FXML
    public void onVarukorgClicked () {
        spManager.showPane("../fxml/varukorg.fxml");
        cssManager.changeCSS("varukorgPane", "upperPaneFill", "upperPaneFillPressed");
    }
}

