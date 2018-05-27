package classes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    private ImageView shoppingCartProductImage, trashCanImg;

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

        this.product = product;

        this.shoppingCartProductImage.setImage(IMatDataHandler.getInstance().getFXImage(product));
        this.shoppingCartProductNameLbl.textProperty().set(product.getName() + "");
        this.shoppingCartProductPriceLbl.textProperty().set(product.getPrice() + " kr/ " + product.getUnitSuffix());
        updateItemTotalPrice();

        if(!product.getUnitSuffix().equals("kg")){
            antalTxtF.textProperty().set("" + (ProductController.productToAmountMap.get(product).intValue()));
        }
        else{
            antalTxtF.textProperty().set("" + (ProductController.productToAmountMap.get(product)));
        }

        antalTxtF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                charLimiter(antalTxtF, 3);
                if(newValue.equals("")){
                    newValue = "0";
                }

                char [] charArray = newValue.toCharArray();
                for(Character character: charArray){
                    if (!character.equals('.') && !Character.isDigit(character)){
                        return;
                    }
                }

                double antal = Double.parseDouble(newValue);

                if(!product.getUnitSuffix().equals("kg")){
                    int round = (int) antal;
                    antalTxtF.textProperty().set(round + "");
                }

                ProductController.productToAmountMap.put(product, antal);
                antalTxtF.setStyle("-fx-control-inner-background: white; -fx-font-size: 20 px; -fx-font-weight: bold");
            }
        });


        antalTxtF.textProperty().addListener((observable, oldValue, newValue) -> {
            updateItemTotalPrice();
        });
    }

    public void clickedMnsBtn() {
        stringValue = antalTxtF.textProperty().get();
        double doubleValue = Double.valueOf(stringValue);

        if (doubleValue > 0) {
            doubleValue--;
            antalTxtF.textProperty().set(doubleValue + "");
            ProductController.productToAmountMap.put(product, (double) doubleValue);
            antalTxtF.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold");

        }

        if (doubleValue == 0){
            antalTxtF.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold");
        }

        this.updateItemTotalPrice();
        varukorgController.updateTotalPriceLabel();
        varukorgController.checkIfEmpty2();
    }

    public void clickedPlsBtn() {
        String stringValue = antalTxtF.textProperty().get();
        double doubleValue = Double.valueOf(stringValue);
        if (doubleValue < 99) {
            doubleValue++;
            antalTxtF.textProperty().set(doubleValue + "");
            ProductController.productToAmountMap.put(product, (double) doubleValue);
            antalTxtF.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold");
        }

        if (doubleValue == 0)
            antalTxtF.setStyle("-fx-font-size: 20 px; -fx-font-weight: bold");

        this.updateItemTotalPrice();
        varukorgController.updateTotalPriceLabel();
        varukorgController.checkIfEmpty2();
    }

    public void clickedTrashCan(){
        ProductController.productToAmountMap.put(product, 0.0);
        this.updateItemTotalPrice();
        varukorgController.updateTotalPriceLabel();
        varukorgController.updateShoppingCartFlowPane();
    }

    private double totalAmount (Product product){
        return ProductController.productToAmountMap.get(product) == null ? 0 : ProductController.productToAmountMap.get(product);
    }

    private void updateItemTotalPrice(){
        this.shoppingCartProductTotalPriceLbl.textProperty().set( roundInString(product.getPrice() * totalAmount(product) )+ " kr");
    }

    private String roundInString(double d){
        return String.format("%.2f", d);
    }

    private void charLimiter(TextField textField, int maxLength){
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (newValue != null && newValue.length() > maxLength ) {
                    String s = newValue.substring(0, maxLength);
                    textField.textProperty().set(s);
                }
            }
        });
    }

    public void trashCanMouseEntered(){
        trashCanImg.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "images/trash_can_icon_hover.png")));
    }


    public void trashCanMouseExited(){
            trashCanImg.setImage(new Image(getClass().getClassLoader().getResourceAsStream("images/trash_can_icon.png")));
    }

}
