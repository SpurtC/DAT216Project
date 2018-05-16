package classes;

import javafx.fxml.FXML;

public class BetalningController extends Controller {


    @FXML
    public void onGoToConfirmationClicked() {
        MainWindowController.spManager.showPane("../fxml/confirmation.fxml");
    }

    @FXML
    public void onGoBackToLeveranstidClicked() {
        MainWindowController.spManager.showPane("../fxml/leveranstid.fxml");
    }

    @Override
    public void init() {

    }
}
