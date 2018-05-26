package classes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

    private IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();
    private ShoppingItem shoppingItem;

    HistoryItemProduct(ShoppingItem shoppingItem) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/product.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.nameLbl.textProperty().set(shoppingItem.getProduct().getName());
        this.priceLbl.textProperty().set(shoppingItem.getProduct().getPrice() + "");
        this.antalLbl.textProperty().set(shoppingItem.getAmount() + "");
        this.shoppingItem = shoppingItem;


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










}
