package classes;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MinHistorikController extends Controller {

    @FXML
    private FlowPane historyFlowPane;

    private HashMap<Date, HistoryItem> stringHistoryItemHashMap = new HashMap<>();

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    @FXML
    public void onContinueShopPreviewPaymentClicked () {
        MainWindowController.spManager.showPane("../fxml/framsida.fxml");
        MainWindowController.cssManager.changeCSS("handlaPane", "upperPaneFill", "upperPaneFillPressed");
    }


    private void updateFlowPane(List<Order> orderList){
        historyFlowPane.getChildren().clear();
        for(Order aOrder: orderList){
            HistoryItem historyItem = new HistoryItem(aOrder);
            stringHistoryItemHashMap.put(aOrder.getDate(), historyItem);
            historyFlowPane.getChildren().add(stringHistoryItemHashMap.get(aOrder.getDate()));
        }
    }

    @Override
    public void init() {

    }

    public void opened() {
        updateFlowPane(iMatDataHandler.getOrders());
    }
}
