package classes;

import javafx.fxml.FXML;

public class TidigareKopController extends Controller {

    @FXML
    public void onContinueShopPreviewPaymentClicked () {
        MainWindowController.spManager.showPane("../fxml/framsida.fxml");
        MainWindowController.cssManager.changeCSS("handlaPane");
    }


    @Override
    public void init() {

    }
}