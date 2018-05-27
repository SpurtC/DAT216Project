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

public class ProductItem extends AnchorPane {
    @FXML
    private Text rubrikTxt, prisTxt;
    @FXML
    private ImageView varaImg;
    @FXML
    private Button minusBtn, plusBtn;
    @FXML
    TextField antalTxtF;
    @FXML
    private ImageView favoriteHeartImg;

    private int numberOfItems;
    private ShoppingCart shoppingCart;
    private ShoppingItem shoppingItem;
    Product product;
    private String stringValue;

    ProductItem(Product product) {

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
        this.product = product;
        this.antalTxtF.textProperty().set("");

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

                char [] charArray = newValue.toCharArray();
                StringBuilder stringBuilder = new StringBuilder();
                for(Character character: charArray){
                    if (character.equals('.') || Character.isDigit(character)){
                        stringBuilder.append(character);
                    }
                }

                if(stringBuilder.length() != antalTxtF.textProperty().get().length()){
                    antalTxtF.textProperty().set("0");
                    return;
                }


                double antal = Double.parseDouble(stringBuilder.toString());

                if(!product.getUnitSuffix().equals("kg")){
                    int round = (int) antal;
                    antalTxtF.textProperty().set(round + "");
                }



                if (antal <= 0){
                    ProductController.productToAmountMap.put(product, antal);
                    antalTxtF.setStyle("-fx-control-inner-background: white; -fx-font-size: 20 px; -fx-font-weight: bold");

                }
                else {
                    antalTxtF.setStyle("-fx-control-inner-background: rgba(111,13,174,0.75); -fx-font-size: 20 px; -fx-font-weight: bold");
                    ProductController.productToAmountMap.put(product, antal);
                    antalTxtF.setStyle("-fx-control-inner-background: #b9aeff; -fx-font-size: 20 px; -fx-font-weight: bold; -fx-text-inner-color: black;");
                }
            }
        });

        this.antalTxtF.textProperty().set(ProductController.productToAmountMap.containsKey(product) ? String.valueOf(ProductController.productToAmountMap.get(product).intValue()) : "0");

        ProductController.productItemList.add(this);
    }

    public void clickedMnsBtn() {
        stringValue = antalTxtF.textProperty().get();
        double doubleValue = Double.valueOf(stringValue);

        if (doubleValue > 0) {
            doubleValue--;
            antalTxtF.textProperty().set(doubleValue + "");
        }
    }

    public void clickedPlsBtn() {
        double doubleValue;

        if(antalTxtF.textProperty().get().equals("")){
            doubleValue = 0;
        }

        else{
            String stringValue = antalTxtF.textProperty().get();
            doubleValue = Double.valueOf(stringValue);
        }


        if (doubleValue < 999) {
            doubleValue++;
            antalTxtF.textProperty().set(doubleValue + "");
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

    public void favoriteHeartMouseEntered(){
        favoriteHeartImg.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "images/hjartaPink.png")));
    }


    public void favoriteHeartMouseExited(){
        if(IMatDataHandler.getInstance().isFavorite(product)) {
            favoriteHeartImg.setImage(new Image("images/hjartaRod.png"));
        }
        else {
            favoriteHeartImg.setImage(new Image("images/hjartaVit.png"));
        }
    }
}
