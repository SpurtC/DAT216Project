package classes;

import javafx.fxml.FXML;

public class ConfirmationController extends Controller {

    @FXML
    public void onCloseConfirmationClicked () {
        MainWindowController.spManager.showPane("../fxml/framsida.fxml");
        MainWindowController.cssManager.changeCSS("handlaPane");
    }

    @Override
    public void init() {

    }
}
