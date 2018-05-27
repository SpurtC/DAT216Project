package classes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
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
    private Button shoppingCartForwardButton;

    @FXML
    private Label shoppingCartTotalPriceLbl, noProductsInBasketLabel;

    public void updateShoppingCartFlowPane() {
        shoppingCartFlowPane.getChildren().clear();
        for (Product aProduct : ProductController.productToAmountMap.keySet()){
            if(ProductController.productToAmountMap.get(aProduct) > 0){
                shoppingCartFlowPane.getChildren().add(new VarukorgItem(aProduct, this));
            }
        }
        checkIfEmpty();
    }

    public void clearBasket(){
        ProductController.productToAmountMap.clear();
        shoppingCartFlowPane.getChildren().clear();
        shoppingCartTotalPriceLbl.textProperty().set("0 kr");
        checkIfEmpty();
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

    public void checkIfEmpty(){
        int antal = 0;

        for(Product entry : ProductController.productToAmountMap.keySet()){
            antal += ProductController.productToAmountMap.get(entry);
        }
        if(antal == 0){
            noProductsInBasketLabel.setVisible(true);
            shoppingCartForwardButton.setDisable(true);
        }else{
            noProductsInBasketLabel.setVisible(false);
            shoppingCartForwardButton.setDisable(false);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void opened() {
        checkIfEmpty();
        updateShoppingCartFlowPane();
        updateTotalPriceLabel();
    }
}
