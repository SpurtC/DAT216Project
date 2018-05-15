package classes;

import javafx.fxml.FXML;
import se.chalmers.cse.dat216.project.*;

import java.awt.*;

public class VarukorgItem {
    //SEMLAN SUGER!!!

    ShoppingCart shoppingCart;

    public int antal;

    @FXML TextField antalTxtF;

    public void clickedPlusBtn() {

        if (antal > 0) {
            antal++;
            this.antalTxtF.setText(antal + "");

          //  shoppingCart.addItem(ShoppingItem sci);
        }
    }
}
