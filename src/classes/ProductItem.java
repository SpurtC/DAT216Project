package classes;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductItem extends AnchorPane {
    @FXML
    private Text rubrikTxt, prisTxt;
    @FXML
    private ImageView varaImg;
    @FXML
    private Button minusBtn, plusBtn;
    @FXML
    private TextField antalTxtF;
    @FXML
    private ImageView favoriteHeartImg;

    private int antal;
    private int numberOfItems;
    private ShoppingCart shoppingCart;
    private ShoppingItem shoppingItem;
    private Product product;
    private String stringValue;

    public ProductItem(Product product) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/product.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);



        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.rubrikTxt.setText(product.getName());
        this.prisTxt.setText(product.getPrice() + " kr / " + product.getUnitSuffix());
        this.varaImg.setImage(IMatDataHandler.getInstance().getFXImage(product));
        this.antalTxtF.setText(antal + "");
        this.product = product;

        if(IMatDataHandler.getInstance().isFavorite(product)) {
            favoriteHeartImg.setImage(new Image("images/hjartaRod.png"));
        }

        antalTxtF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                charLimiter(antalTxtF, 3);
                if(newValue.equals("")){
                    newValue = "0";
                }

                double antal = Double.parseDouble(newValue);

                if(!product.getUnitSuffix().equals("kg")){
                    int round = (int) antal;
                    System.out.println(round + "");
                    antalTxtF.textProperty().set(round + "");
                }

                if (antal <= 0){
                    ProductController.productToAmountMap.put(product, antal);
                    antalTxtF.setStyle("-fx-control-inner-background: white; -fx-font-size: 20 px; -fx-font-weight: bold");
                }

                else {
                    antalTxtF.setStyle("-fx-control-inner-background: #ebd8ff; -fx-font-size: 20 px; -fx-font-weight: bold");
                    ProductController.productToAmountMap.put(product, antal);
                }



            }
        });
    }

    public void clickedMnsBtn() {
        stringValue = antalTxtF.textProperty().get();
        int intValue = Integer.valueOf(stringValue);

        if (intValue > 0) {
            intValue--;
            antalTxtF.textProperty().set(intValue + "");
        }
    }

    public void clickedPlsBtn() {
        String stringValue = antalTxtF.textProperty().get();
        int intValue = Integer.valueOf(stringValue);
        if (intValue < 99) {
            intValue++;
            antalTxtF.textProperty().set(intValue + "");
        }
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


    public void onFavoriteHeartClicked() {
        if(!IMatDataHandler.getInstance().isFavorite(product)) {
            IMatDataHandler.getInstance().addFavorite(product);
            favoriteHeartImg.setImage(new Image("images/hjartaRod.png"));
        }

        else {
            IMatDataHandler.getInstance().removeFavorite(product);
            favoriteHeartImg.setImage(new Image("images/hjartaVit.png"));
        }
    }

}
