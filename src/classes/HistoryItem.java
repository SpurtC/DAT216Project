package classes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class HistoryItem extends AnchorPane{

    @FXML
    Label dateLbl;

    @FXML
    Button moreDetailsButton;

    private IMatDataHandler iMatDataHandler =  IMatDataHandler.getInstance();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");


    HistoryItem(Order order){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/tidigareKopLista.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.dateLbl.textProperty().set(dateFormat.format(order.getDate()));
    }

    private String getTotalPrice (Order order){
        List<ShoppingItem> list = order.getItems();
        double total = 0;
        for(ShoppingItem aShoppingItem : list){
            total += aShoppingItem.getAmount() * aShoppingItem.getProduct().getPrice();
        }
        return total + "";
    }

    private String getAmountOfItems (Order order){
        List<ShoppingItem> list = order.getItems();
        int antal = 0;
        for(ShoppingItem s: list){
            antal += s.getAmount();
        }

        return antal + "";
    }
}
