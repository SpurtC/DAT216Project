package classes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;

import java.util.Map;


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

    @FXML
    private Label shoppingCartTotalPriceLbl;

    public void updateShoppingCartFlowPane() {
        shoppingCartFlowPane.getChildren().clear();
        for (Product aProduct : ProductController.productToAmountMap.keySet()){
            System.out.println(ProductController.productToAmountMap.get(aProduct));
            if(ProductController.productToAmountMap.get(aProduct) > 0){
                shoppingCartFlowPane.getChildren().add(new VarukorgItem(aProduct, this));
            }
        }
    }

    public void updateTotalPriceLabel(){
        shoppingCartTotalPriceLbl.textProperty().set(roundInString(addAllProducts())+ " kr");
    }

    private double addAllProducts(){
        double a = 0;

        for(Map.Entry<Product, Double> entry: ProductController.productToAmountMap.entrySet()){
            a += entry.getKey().getPrice() * entry.getValue();
        }

        return a;
    }

    private String roundInString(double d){
        return String.format("%.2f", d);
    }

    @Override
    public void init() {

    }

    @Override
    public void opened() {
        updateShoppingCartFlowPane();
        updateTotalPriceLabel();
    }
}
