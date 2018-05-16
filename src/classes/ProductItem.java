package classes;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
        this.prisTxt.setText(product.getPrice() + " kr");
        this.varaImg.setImage(IMatDataHandler.getInstance().getFXImage(product));
        this.antalTxtF.setText(antal + "");
    }

    public void clickedMnsBtn() {
        if (antal > 0) {
            antal--;
            this.antalTxtF.setText(antal + "");
            antalTxtF.setStyle("-fx-control-inner-background: #99e482; -fx-font-size: 20 px; -fx-font-weight: bold");
        }
        if (antal == 0)
            antalTxtF.setStyle("-fx-control-inner-background: white; -fx-font-size: 20 px; -fx-font-weight: bold");
    }

    public void clickedPlsBtn() {
        if (antal < 99) {
            antal++;
            this.antalTxtF.setText(antal + "");
            antalTxtF.setStyle("-fx-control-inner-background: #99e482; -fx-font-size: 20 px; -fx-font-weight: bold");
        }
        if (antal == 0)
            antalTxtF.setStyle("-fx-control-inner-background: white; -fx-font-size: 20 px; -fx-font-weight: bold");
    }
}
