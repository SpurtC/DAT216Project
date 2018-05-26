package classes;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MinHistorikController extends Controller {

    private static MinHistorikController instance = null;

    @FXML
    private FlowPane historyFlowPane, itemFlowPane;

    private HashMap<Date, HistoryItem> stringHistoryItemHashMap = new HashMap<>();
    private HashMap<String, HistoryItemProduct> stringHistoryItemProductHashMap = new HashMap<>();

    private IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    @FXML
    public void onContinueShopPreviewPaymentClicked () {
        MainWindowController.spManager.showPane("../fxml/framsida.fxml");
        MainWindowController.cssManager.changeCSS("handlaPane", "upperPaneFill", "upperPaneFillPressed");
    }

    private void updateFlowPane(List<Order> orderList){
        historyFlowPane.getChildren().clear();
        for(Order aOrder: orderList){
            HistoryItem historyItem = new HistoryItem(aOrder, this);
            stringHistoryItemHashMap.put(aOrder.getDate(), historyItem);
            historyFlowPane.getChildren().add(stringHistoryItemHashMap.get(aOrder.getDate()));
        }
    }

    public void updateItemFlowPane (Order order){
        itemFlowPane.getChildren().clear();
        List<ShoppingItem> shoppingItemList = order.getItems();
        for(ShoppingItem shoppingItem: shoppingItemList){
            if(shoppingItem.getAmount() != 0){
                HistoryItemProduct historyItemProduct = new HistoryItemProduct(shoppingItem);
                stringHistoryItemProductHashMap.put(shoppingItem.getProduct().getName(), historyItemProduct);
                itemFlowPane.getChildren().add(stringHistoryItemProductHashMap.get(shoppingItem.getProduct().getName()));
            }
        }
    }


    @Override
    public void init() {

    }

    public void opened() {
        updateFlowPane(iMatDataHandler.getOrders());
    }
}
