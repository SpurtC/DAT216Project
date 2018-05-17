package classes;

import javafx.fxml.FXML;

public class MinaFavoriterController extends Controller {

    @FXML
    public void onContinueShopMyFavoritesClicked () {
        MainWindowController.spManager.showPane("../fxml/framsida.fxml");
        MainWindowController.cssManager.changeCSS("handlaPane", "upperPaneFill", "upperPaneFillPressed");
    }

    @Override
    public void init() {

    }


}
