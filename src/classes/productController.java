package classes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class productController {
    @FXML private Text rubrikTxt, prisTxt;
    @FXML private ImageView varaImg;
    @FXML private Button minusBtn, plusBtn;
    @FXML private TextField antalTxtF;
    private int antal;

    public productController(Product product){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/produkt.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.rubrikTxt.setText(product.getName());
        this.prisTxt.setText(product.getPrice() + "");
        this.varaImg.setImage(getFXImage(product));
        this.antalTxtF.setText(antal + "");

       
    }

    public Image getFXImage(Product product) {
        return new Image("file:" + product.getImageName());
    }









}
