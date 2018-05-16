package classes;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class ProductItem extends AnchorPane {
    @FXML private Text rubrikTxt, prisTxt;
    @FXML private ImageView varaImg;
    @FXML private Button minusBtn, plusBtn;
    @FXML private TextField antalTxtF;
    private int antal;

    public ProductItem(Product product){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/product.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.rubrikTxt.setText(product.getName());
        this.prisTxt.setText(product.getPrice() + "");
        this.varaImg.setImage(IMatDataHandler.getInstance().getFXImage(product));
        this.antalTxtF.setText(antal + "");
    }

    public void clickedMnsBtn() {
        if (antal > 0) {
            antal--;
            this.antalTxtF.setText(antal + "");
        }
    }

    public void clickedPlsBtn() {
        if (antal < 99) {
            antal++;
            this.antalTxtF.setText(antal + "");
        }
    }
}
