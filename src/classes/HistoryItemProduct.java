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
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class HistoryItemProduct extends AnchorPane{

    @FXML
    Label nameLbl, priceLbl, antalLbl;

    @FXML
    TextField antalTxtF;

    @FXML
    ImageView picture;

    @FXML
    Button addBtn;

    private IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();
    private ShoppingItem shoppingItem;
    private double antal;

    HistoryItemProduct(ShoppingItem shoppingItem) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/tidigareKopProdukt.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.picture.setImage(IMatDataHandler.getInstance().getFXImage(shoppingItem.getProduct()));
        this.nameLbl.textProperty().set(shoppingItem.getProduct().getName());
        this.priceLbl.textProperty().set(shoppingItem.getProduct().getPrice() + " kr / " + shoppingItem.getProduct().getUnitSuffix());
        this.antalLbl.textProperty().set(shoppingItem.getAmount() + " " + shoppingItem.getProduct().getUnitSuffix());
        this.antalTxtF.textProperty().set("0");
        this.shoppingItem = shoppingItem;


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

                double antal = Double.parseDouble(newValue);

                if(!shoppingItem.getProduct().getUnitSuffix().equals("kg")){
                    int round = (int) antal;
                    antalTxtF.textProperty().set(round + "");
                }

                antalTxtF.setStyle("-fx-control-inner-background: white; -fx-font-size: 20 px; -fx-font-weight: bold");
                if (antal <= 0){
                    addBtn.setDisable(true);
                }

                else {
                    addBtn.setDisable(false);
                }

            }
        });


    }
    public void clickedMnsBtn() {
        String stringValue = antalTxtF.textProperty().get();
        int intValue = Integer.valueOf(stringValue);

        if (intValue > 0) {
            intValue--;
            antalTxtF.textProperty().set(intValue + "");
        }
    }

    public void clickedPlsBtn() {
        String stringValue = antalTxtF.textProperty().get();
        int intValue = Integer.valueOf(stringValue);
        if (intValue < 999) {
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

    public void addButton() {
        ProductController.productToAmountMap.put(shoppingItem.getProduct(),
                ProductController.productToAmountMap.get(shoppingItem.getProduct()) + Double.parseDouble(antalTxtF.textProperty().get()));
        System.out.println(antalTxtF.textProperty().get());
    }







}
