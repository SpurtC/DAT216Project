package classes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RootController extends Controller{

    @FXML private StackPane stackPane;

    private SPManager spManager;
    private MainWindowController mainWindowController;

    List<String> rootFxmlfileList = new ArrayList<>();

    private void makeRootList () {
        rootFxmlfileList.add("/fxml/mainWindow.fxml");
      //  rootFxmlfileList.add("/fxml/confirm.fxml");
    }

    @Override
    public void init() {
        makeRootList();

        System.out.println("Root");

        spManager = new SPManager(stackPane, rootFxmlfileList);
        System.out.println("SPManager created");
        spManager.showPane("/fxml/mainWindow.fxml");
    }
}
