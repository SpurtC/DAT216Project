package classes;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;


public class VarukorgController extends Controller{

    @FXML
    public void onContinueHandleClicked() {
        MainWindowController.spManager.showPane("../fxml/framsida.fxml");
        MainWindowController.cssManager.changeCSS("handlaPane", "upperPaneFill", "upperPaneFillPressed");
    }

    @FXML
    public void onGoForwardClicked() {
        MainWindowController.spManager.showPane("../fxml/leveranstid.fxml");
    }

    @FXML
    private FlowPane shoppingCartFlowPane;

    private void updateShoppingCartFlowPane() {
        shoppingCartFlowPane.getChildren().clear();
        for (Product aProduct : ProductController.productToAmountMap.keySet()){
            System.out.println(ProductController.productToAmountMap.get(aProduct));
            if(ProductController.productToAmountMap.get(aProduct) > 0){
                shoppingCartFlowPane.getChildren().add(new VarukorgItem(aProduct));
            }
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void opened() {
        updateShoppingCartFlowPane();
    }
}
