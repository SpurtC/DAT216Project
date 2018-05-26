package classes;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LastConfirmationController extends Controller {

    @FXML
    Text messageTxt, message2Txt;

    private IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();
    private DeliveryHandler deliveryHandler = DeliveryHandler.getInstance();

    @FXML
    public void onCloseConfirmationClicked () {
        MainWindowController.spManager.showPane("../fxml/framsida.fxml");
        MainWindowController.cssManager.changeCSS("handlaPane","upperPaneFill", "upperPaneFillPressed");
    }

    private void saveShoppingCart() {
        addShoppingCartToListOfOrders();

    }

    private void addShoppingCartToListOfOrders() {

        for (Product aProduct : ProductController.productToAmountMap.keySet()) {
            iMatDataHandler.getShoppingCart().addProduct(aProduct, ProductController.productToAmountMap.get(aProduct));
            System.out.println(aProduct);
        }
        iMatDataHandler.placeOrder();
        ProductController.productToAmountMap.clear();
    }

    @Override
    public void init() {

    }

    public void opened() {
        messageTxt.textProperty().set("Betalningsbekräftelse och kvitto har skickats till din e-post: " + iMatDataHandler.getCustomer().getEmail());
        message2Txt.textProperty().set("Varorna kommer att levereras på " + deliveryHandler.deliveryDate);
        saveShoppingCart();
    }
}
