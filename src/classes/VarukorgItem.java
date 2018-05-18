package classes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;

public class VarukorgItem extends AnchorPane {

    private VarukorgController varukorgController;

    @FXML
    private ImageView shoppingCartProductImage;

    @FXML
    private Label shoppingCartProductNameLbl, shoppingCartProductPriceLbl, shoppingCartProductTotalPriceLbl, shoppingCartTotalPriceLbl;

    @FXML
    private Button minusBtn, plusBtn;

    @FXML
    private TextField antalTxtF;

    private String stringValue;
    private Product product;


    public VarukorgItem(Product product, VarukorgController varukorgController) {
        this.varukorgController = varukorgController;
        //int amount = ProductController.productToAmountMap.get(product);
        //antalTxtF.textProperty().set(amount + "");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/varukorgProdukt.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.shoppingCartProductImage.setImage(IMatDataHandler.getInstance().getFXImage(product));
        this.shoppingCartProductNameLbl.textProperty().set(product.getName() + "");
        this.shoppingCartProductPriceLbl.textProperty().set(product.getPrice() + " kr");
        this.shoppingCartProductTotalPriceLbl.textProperty().set(product.getPrice() * totalAmount(product) + " kr");
        antalTxtF.textProperty().set("" + (ProductController.productToAmountMap.get(product)).intValue());
        this.product = product;
    }



    public void clickedMnsBtn() {
        stringValue = antalTxtF.textProperty().get();
        int intValue = Integer.valueOf(stringValue);

        if (intValue > 0) {
            intValue--;
            antalTxtF.textProperty().set(intValue + "");
            ProductController.productToAmountMap.put(product, (double) intValue);
            antalTxtF.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold");

        }

        if (intValue == 0){
            antalTxtF.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold");
        }

        this.updateItemTotalPrice();
        varukorgController.updateTotalPriceLabel();
    }

    public void clickedPlsBtn() {
        String stringValue = antalTxtF.textProperty().get();
        int intValue = Integer.valueOf(stringValue);
        if (intValue < 99) {
            intValue++;
            antalTxtF.textProperty().set(intValue + "");
            ProductController.productToAmountMap.put(product, (double) intValue);
            antalTxtF.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold");
        }

        if (intValue == 0)
            antalTxtF.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold");

        this.updateItemTotalPrice();
        varukorgController.updateTotalPriceLabel();
    }

    private double totalAmount (Product product){
        double a = 0;

        for(Map.Entry<Product, Double> entry: ProductController.productToAmountMap.entrySet()){
            a = entry.getValue();
        }

        return a;
    }

    private void updateItemTotalPrice(){
        this.shoppingCartProductTotalPriceLbl.textProperty().set( roundInString(product.getPrice() * totalAmount(product) )+ " kr");
    }

    private String roundInString(double d){
        return String.format("%.2f", d);
    }

}
