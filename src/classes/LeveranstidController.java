package classes;

import javafx.fxml.FXML;

public class LeveranstidController extends Controller {


    @FXML
    public void onGoForwardToPaymentClicked() {
        MainWindowController.spManager.showPane("../fxml/betalning.fxml");
    }

    @FXML
    public void onGoBackToShoppingCartClicked() {
        MainWindowController.spManager.showPane("../fxml/varukorg.fxml");
    }

    @Override
    public void init() {

    }
}
