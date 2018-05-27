package classes;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

public class RootController extends Controller{

    @FXML private StackPane stackPane;

    private SPManager spManager;
    private MainWindowController mainWindowController;

    List<String> rootFxmlfileList = new ArrayList<>();

    private void makeRootList () {
        rootFxmlfileList.add("/fxml/mainWindow.fxml");
    }

    @Override
    public void init() {
        makeRootList();

        spManager = new SPManager(stackPane, rootFxmlfileList);
        spManager.showPane("/fxml/mainWindow.fxml");
    }
}
