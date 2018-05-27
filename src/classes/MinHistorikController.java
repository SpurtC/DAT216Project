package classes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MinHistorikController extends Controller {

    private static MinHistorikController instance = null;

    @FXML
    private FlowPane historyFlowPane, itemFlowPane;

    @FXML
    Label messageLbl;

    @FXML
    public Button addOrderButton;

    private HashMap<Date, HistoryItem> dateHistoryItemHashMap = new HashMap<>();
    private HashMap<String, HistoryItemProduct> stringHistoryItemProductHashMap = new HashMap<>();

    private IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();
    Date getDate = null;

    CSSManager cssManager = new CSSManager(dateHistoryItemHashMap, "Katt");

    @FXML
    public void onContinueShopPreviewPaymentClicked () {
        MainWindowController.spManager.showPane("../fxml/framsida.fxml");
        MainWindowController.cssManager.changeCSS("handlaPane", "upperPaneFill", "upperPaneFillPressed");
    }

    private void updateFlowPane(List<Order> orderList){
        historyFlowPane.getChildren().clear();
        for(Order aOrder: orderList) {
            HistoryItem historyItem = new HistoryItem(aOrder, this);
            dateHistoryItemHashMap.put(aOrder.getDate(), historyItem);
            historyFlowPane.getChildren().add(dateHistoryItemHashMap.get(aOrder.getDate()));
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
        cssManager.changeCSSHistory(order.getDate(), "fillHistoryItem", "fillHistoryItemClicked");

    }

    public void addOrderToShoppingCart() {
        HistoryItem historyItem = dateHistoryItemHashMap.get(getDate);
        List<ShoppingItem> shoppingItemList = historyItem.order.getItems();
        for(ShoppingItem shoppingItem: shoppingItemList){
            if(shoppingItem.getAmount() != 0){
                ProductController.productToAmountMap.put(shoppingItem.getProduct(), shoppingItem.getAmount());
            }
        }
        messageLbl.textProperty().set("Produkterna har lagts till i din varukorg");
        messageLbl.setVisible(true);
    }


    @Override
    public void init() {

    }

    public void opened() {
        updateFlowPane(iMatDataHandler.getOrders());
        itemFlowPane.getChildren().clear();
        getDate = null;
        addOrderButton.setDisable(true);
        messageLbl.setVisible(false);
    }
}
