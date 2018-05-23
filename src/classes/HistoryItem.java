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
import java.util.List;

public class HistoryItem extends AnchorPane{

    @FXML
    Label dateLbl, numberLbl, priceLbl;

    @FXML
    Button moreDetailsButton;

    private IMatDataHandler iMatDataHandler =  IMatDataHandler.getInstance();


    HistoryItem(Order order){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/tidigareKopLista.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.dateLbl.textProperty().set(order.getDate() + "");
        getItems(order);
        this.numberLbl.textProperty().set(getAmountOfItems(order));
        this.priceLbl.textProperty().set(getTotalPrice(order));

    }

    private String getTotalPrice (Order order){
        List<ShoppingItem> list = order.getItems();
        double total = 0;
        for(ShoppingItem aShoppingItem : list){
            total = aShoppingItem.getAmount() + aShoppingItem.getProduct().getPrice();
        }
        return total + "";
    }

    private String getAmountOfItems (Order order){
        List<ShoppingItem> list = order.getItems();
        int antal = list.size();
        return antal + "";
    }

    private void getItems (Order order){
        List<ShoppingItem> list = order.getItems();
        for(ShoppingItem a: list){
            System.out.println(a.getProduct().getName());
        }
    }
}
