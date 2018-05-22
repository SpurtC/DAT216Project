package classes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinaFavoriterController extends Controller {

    @FXML
    public FlowPane favoriteFlowPane;

    private Map<String, ProductItem> productsListItemMap = new HashMap<>();

    @FXML
    Label noFavoritessLbl;


    public void onContinueShopMyFavoritesClicked () {
        MainWindowController.spManager.showPane("../fxml/framsida.fxml");
        MainWindowController.cssManager.changeCSS("handlaPane", "upperPaneFill", "upperPaneFillPressed");
    }

    private void updateFlowPane(List<Product> list) {
        favoriteFlowPane.getChildren().clear();
        for (Product aProduct : list){
            ProductItem productItem = new ProductItem(aProduct);
            productsListItemMap.put(aProduct.getName(), productItem);
            favoriteFlowPane.getChildren().add(productsListItemMap.get(aProduct.getName()));
        }
        
        if(IMatDataHandler.getInstance().favorites().size() == 0 ) {
            noFavoritessLbl.setVisible(true);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void opened() {
        noFavoritessLbl.setVisible(false);
        updateFlowPane(IMatDataHandler.getInstance().favorites());
    }


}
