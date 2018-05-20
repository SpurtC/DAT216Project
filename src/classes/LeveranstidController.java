package classes;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;


public class LeveranstidController extends Controller {

    private final ToggleGroup RADIOGROUP = new ToggleGroup();

    private DeliveryHandler deliveryHandler = DeliveryHandler.getInstance();

    @FXML
    private RadioButton monTenRBtn, monTwelveRBtn, monFourteenRBtn, monSixteenRBtn, monEighteenRBtn,
                        tueTenRBtn, tueTwelveRBtn, tueFourteenRBtn, tueSixteenRBtn, tueEighteenRBtn,
                        wedTenRBtn, wedTwelveRBtn, wedFourteenRBtn, wedSixteenRBtn, wedEighteenRBtn,
                        thuTenRBtn, thuTwelveRBtn, thuFourteenRBtn, thuSixteenRBtn, thuEighteenRBtn,
                        friTenRBtn, friTwelveRBtn, friFourteenRBtn, friSixteenRBtn, friEighteenRBtn;


    @FXML
    public void onGoForwardToPaymentClicked() {
        saveDeliveryDate();
        MainWindowController.spManager.showPane("../fxml/betalning.fxml");
    }

    @FXML
    public void onGoBackToShoppingCartClicked() {
        MainWindowController.spManager.showPane("../fxml/varukorg.fxml");
    }

    private void makeAToggleGroup () {
        monTenRBtn.setToggleGroup(RADIOGROUP);
        monTwelveRBtn.setToggleGroup(RADIOGROUP);
        monFourteenRBtn.setToggleGroup(RADIOGROUP);
        monSixteenRBtn.setToggleGroup(RADIOGROUP);
        monEighteenRBtn.setToggleGroup(RADIOGROUP);
        tueTenRBtn.setToggleGroup(RADIOGROUP);
        tueTwelveRBtn.setToggleGroup(RADIOGROUP);
        tueFourteenRBtn.setToggleGroup(RADIOGROUP);
        tueSixteenRBtn.setToggleGroup(RADIOGROUP);
        tueEighteenRBtn.setToggleGroup(RADIOGROUP);
        wedTenRBtn.setToggleGroup(RADIOGROUP);
        wedTwelveRBtn.setToggleGroup(RADIOGROUP);
        wedFourteenRBtn.setToggleGroup(RADIOGROUP);
        wedSixteenRBtn.setToggleGroup(RADIOGROUP);
        wedEighteenRBtn.setToggleGroup(RADIOGROUP);
        thuTenRBtn.setToggleGroup(RADIOGROUP);
        thuTwelveRBtn.setToggleGroup(RADIOGROUP);
        thuFourteenRBtn.setToggleGroup(RADIOGROUP);
        thuSixteenRBtn.setToggleGroup(RADIOGROUP);
        thuEighteenRBtn.setToggleGroup(RADIOGROUP);
        friTenRBtn.setToggleGroup(RADIOGROUP);
        friTwelveRBtn.setToggleGroup(RADIOGROUP);
        friFourteenRBtn.setToggleGroup(RADIOGROUP);
        friSixteenRBtn.setToggleGroup(RADIOGROUP);
        friEighteenRBtn.setToggleGroup(RADIOGROUP);
    }

    private void saveDeliveryDate() {
        if(monTenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "måndag mellan 10 och 12";
        }

        else if(monTwelveRBtn.isSelected()){
            deliveryHandler.deliveryDate = "måndag mellan 12 och 14";
        }

        else if(monFourteenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "måndag mellan 14 och 16";
        }

        else if(monSixteenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "måndag mellan 16 och 18";
        }

        else if(monEighteenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "måndag mellan 18 och 20";
        }

        else if(tueTenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "tisdag mellan 10 och 12";
        }

        else if(tueTwelveRBtn.isSelected()){
            deliveryHandler.deliveryDate = "tisdag mellan 12 och 14";
        }

        else if(tueFourteenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "tisdag mellan 14 och 16";
        }

        else if(tueSixteenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "tisdag mellan 16 och 18";
        }

        else if(tueEighteenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "tisdag mellan 18 och 20";
        }

        else if(wedTenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "onsdag mellan 10 och 12";
        }

        else if(wedTwelveRBtn.isSelected()){
            deliveryHandler.deliveryDate = "onsdag mellan 12 och 14";
        }

        else if(wedFourteenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "onsdag mellan 14 och 16";
        }

        else if(wedSixteenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "onsdag mellan 16 och 18";
        }

        else if(wedEighteenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "onsdag mellan 18 och 20";
        }

        else if(thuTenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "torsdag mellan 10 och 12";
        }

        else if(thuTwelveRBtn.isSelected()){
            deliveryHandler.deliveryDate = "torsdag mellan 12 och 14";
        }

        else if(thuFourteenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "torsdag mellan 14 och 16";
        }

        else if(thuSixteenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "torsdag mellan 16 och 18";
        }

        else if(thuEighteenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "torsdag mellan 18 och 20";
        }

        else if(friTenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "fredag mellan 10 och 12";
        }

        else if(friTwelveRBtn.isSelected()){
            deliveryHandler.deliveryDate = "fredag mellan 12 och 14";
        }

        else if(friFourteenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "fredag mellan 14 och 16";
        }

        else if(friSixteenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "fredag mellan 16 och 18";
        }

        else if(friEighteenRBtn.isSelected()){
            deliveryHandler.deliveryDate = "fredag mellan 18 och 20";
        }
    }

    @Override
    public void init() {
        makeAToggleGroup();
    }
}
