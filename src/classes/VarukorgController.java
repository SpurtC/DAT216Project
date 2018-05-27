package classes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ImageView forwardImage;

    @FXML
    private Button shoppingCartForwardButton, emptyBasketButton;

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
        for(Product product: ProductController.productToAmountMap.keySet()){
            ProductController.productToAmountMap.put(product, 0.0);
        }
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
            emptyBasketButton.setDisable(true);
            forwardImage.setVisible(false);
        }else{
            noProductsInBasketLabel.setVisible(false);
            shoppingCartForwardButton.setDisable(false);
            emptyBasketButton.setDisable(false);
            forwardImage.setVisible(true);
        }
    }

    public void checkIfEmpty2(){
        int antal = 0;

        for(Product entry : ProductController.productToAmountMap.keySet()){
            antal += ProductController.productToAmountMap.get(entry);
        }
        if(antal == 0){
            shoppingCartForwardButton.setDisable(true);
            forwardImage.setVisible(false);
        }else{
            shoppingCartForwardButton.setDisable(false);
            forwardImage.setVisible(true);
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
