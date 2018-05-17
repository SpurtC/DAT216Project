package classes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.Map;

public class VarukorgItem extends AnchorPane {

    @FXML
    ImageView shoppingCartProductImage;

    @FXML
    Label shoppingCartProductNameLbl, shoppingCartProductPriceLbl, shoppingCartProductTotalPriceLbl;

    @FXML
    private Button minusBtn, plusBtn;
    @FXML
    private TextField antalTxtF;

    private String stringValue;


    public VarukorgItem(Product product) {
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
        this.shoppingCartProductNameLbl.setText(product.getName() + "");
        this.shoppingCartProductPriceLbl.setText(product.getPrice() + " kr");
        this.shoppingCartProductTotalPriceLbl.setText(product.getPrice() * totalAmount(product) + " kr");
    }



    public void clickedMnsBtn() {
        stringValue = antalTxtF.textProperty().get();
        int intValue = Integer.valueOf(stringValue);

        if (intValue > 0) {
            intValue--;
            antalTxtF.textProperty().set(intValue + "");
            antalTxtF.setStyle("-fx-control-inner-background: #99e482; -fx-font-size: 20 px; -fx-font-weight: bold");

        }

        if (intValue == 0){
            antalTxtF.setStyle("-fx-control-inner-background: white; -fx-font-size: 20 px; -fx-font-weight: bold");
        }
    }

    public void clickedPlsBtn() {
        String stringValue = antalTxtF.textProperty().get();
        int intValue = Integer.valueOf(stringValue);
        if (intValue < 99) {
            intValue++;
            antalTxtF.textProperty().set(intValue + "");
            antalTxtF.setStyle("-fx-control-inner-background: #99e482; -fx-font-size: 20 px; -fx-font-weight: bold");
        }

        if (intValue == 0)
            antalTxtF.setStyle("-fx-control-inner-background: white; -fx-font-size: 20 px; -fx-font-weight: bold");
    }

    private double totalAmount (Product product){
        double a = 0;
        for(Map.Entry<Product, Double> entry: ProductController.productToAmountMap.entrySet()){
            a = entry.getValue();
        }
        return a;
    }
}
