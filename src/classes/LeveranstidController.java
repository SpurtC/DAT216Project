package classes;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;


public class LeveranstidController extends Controller {

    final ToggleGroup RADIOGROUP = new ToggleGroup();

    @FXML
    private RadioButton monTenRBtn, monTwelveRBtn, monFourteenRBtn, monSixteenRBtn, monEighteenRBtn,
                        tueTenRBtn, tueTwelveRBtn, tueFourteenRBtn, tueSixteenRBtn, tueEighteenRBtn,
                        wedTenRBtn, wedTwelveRBtn, wedFourteenRBtn, wedSixteenRBtn, wedEighteenRBtn,
                        thuTenRBtn, thuTwelveRBtn, thuFourteenRBtn, thuSixteenRBtn, thuEighteenRBtn,
                        friTenRBtn, friTwelveRBtn, friFourteenRBtn, friSixteenRBtn, friEighteenRBtn;


    @FXML
    public void onGoForwardToPaymentClicked() {
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

    @Override
    public void init() {
        makeAToggleGroup();
    }
}
