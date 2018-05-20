package classes;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class ConfirmationController extends Controller {

    @FXML
    Text messageTxt, message2Txt;


    private IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();
    private DeliveryHandler deliveryHandler = DeliveryHandler.getInstance();

    @FXML
    public void onCloseConfirmationClicked () {
        MainWindowController.spManager.showPane("../fxml/framsida.fxml");
        MainWindowController.cssManager.changeCSS("handlaPane","upperPaneFill", "upperPaneFillPressed");
    }

    public void setText() {
        messageTxt.textProperty().set("Betalningsbekräftelse och kvitto har skickats till din e-post: " + iMatDataHandler.getCustomer().getEmail());
        message2Txt.textProperty().set("Varorna kommer att levereras på " + deliveryHandler.deliveryDate);
    }

    @Override
    public void init() {

    }

    public void opened() {
        setText();
    }
}
