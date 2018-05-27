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
import java.lang.management.MonitorInfo;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class HistoryItem extends AnchorPane{

    @FXML
    Label dateLbl;

    @FXML
    Button moreDetailsButton;

    private IMatDataHandler iMatDataHandler =  IMatDataHandler.getInstance();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:dd:ss");
    Order order;
    private MinHistorikController minHistorikController;


    HistoryItem(Order order, MinHistorikController minHistorikController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/tidigareKopLista.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.dateLbl.textProperty().set(dateFormat.format(order.getDate()));
        this.order = order;
        this.minHistorikController = minHistorikController;
    }

    public void showItems(){
        minHistorikController.addOrderButton.setDisable(false);
        minHistorikController.getDate = order.getDate();
        minHistorikController.updateItemFlowPane(order);
        minHistorikController.messageLbl.setVisible(false);
    }
}
