package classes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController extends Controller{

    @FXML private StackPane stackPaneMain;

    List<String> fxmlFileList = new ArrayList<String>();

    SPManager spManager;

    private void makeAfxmlList() {
        fxmlFileList.add("../fxml/betalning.fxml");
        fxmlFileList.add("../fxml/confirmation.fxml");
        fxmlFileList.add("../fxml/framsida.fxml");
        fxmlFileList.add("../fxml/kundinformation.fxml");
        fxmlFileList.add("../fxml/minaFavoriter.fxml");
        fxmlFileList.add("../fxml/mittKonto.fxml");
        fxmlFileList.add("../fxml/tidigareKop.fxml");
        fxmlFileList.add("../fxml/varukorg.fxml");
    }

    public List<String> getFxmlFilesList() {
        return fxmlFileList;
    }

    @Override
    public void init() {
        makeAfxmlList();

        System.out.println("Main");

        spManager = new SPManager(stackPaneMain, fxmlFileList);
        spManager.showPane("../fxml/framsida.fxml");
    }

    @FXML
    public void onKundtjanstClicked() {
        spManager.showPane("../fxml/kundinformation.fxml");
    }

    @FXML
    public void onHandlaClicked() {
        spManager.showPane("../fxml/framsida.fxml");
    }

    @FXML
    public void onMittKontoClicked() {
        spManager.showPane("../fxml/mittKonto.fxml");
    }

    @FXML
    public void onVarukorgClicked() {
        spManager.showPane("../fxml/varukorg.fxml");
    }
}

