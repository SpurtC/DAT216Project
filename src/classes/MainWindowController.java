package classes;

import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import se.chalmers.cse.dat216.project.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainWindowController extends Controller{

    @FXML private StackPane stackPaneMain;
    @FXML private Pane handlaPane, kundtjanstPane, mittKontoPane, varukorgPane;
    @FXML private Label totalPriceLbl, numberOfItemsLbl;

    private ProductItem productItem;

    List<String> fxmlFileList = new ArrayList<>();
    private static Map<String, Pane> stringPaneMap = new HashMap<>();

    public static SPManager spManager;
    public static CSSManager cssManager = new CSSManager(stringPaneMap);

    public List<String> getFxmlFilesList() {
        return fxmlFileList;
    }
    CSSManager cssManagerCategory = new CSSManager(ProductController.stringPaneMapProduct);

    @Override
    public void init() {
        makeAfxmlList();
        makeAMap();

        spManager = new SPManager(stackPaneMain, fxmlFileList);

        ProductController.productToAmountMap.addListener((MapChangeListener<Product, Double>) change -> {
            updateNumberOfItems();
            updateTotalPrice();
        });

        cssManagerCategory.changeCSS("allProductsCtgPane", "ctgPaneFill", "ctgPaneFillClicked");
    }

    @Override
    public void opened() {
        spManager.showPane("../fxml/framsida.fxml");
        cssManager.changeCSS("handlaPane", "upperPaneFill", "upperPaneFillPressed");
    }

    public void makeAMap () {
        stringPaneMap.put("handlaPane", handlaPane );
        stringPaneMap.put("kundtjanstPane", kundtjanstPane );
        stringPaneMap.put("mittKontoPane", mittKontoPane );
        stringPaneMap.put("varukorgPane", varukorgPane );
    }

    private void makeAfxmlList() {
        fxmlFileList.add("../fxml/betalning.fxml");
        fxmlFileList.add("../fxml/lastConfirmation.fxml");
        fxmlFileList.add("../fxml/framsida.fxml");
        fxmlFileList.add("../fxml/kundinformation.fxml");
        fxmlFileList.add("../fxml/mittKontoMain.fxml");
        fxmlFileList.add("../fxml/varukorg.fxml");
        fxmlFileList.add("../fxml/leveranstid.fxml");
        fxmlFileList.add("../fxml/betalning.fxml");
    }

    public void updateNumberOfItems(){
        int amount = 0;

        /*for(Map.Entry<Product, Double> entry: ProductController.productToAmountMap.entrySet()){
            if(entry.getKey().getUnitSuffix().equals("kg") && entry.getValue() > 0){
                System.out.println("Nu är jag här");
            }
            else{
                amount += entry.getValue();
                System.out.println("inte här");
            }
        }*/

        for(Map.Entry<Product, Double> entry: ProductController.productToAmountMap.entrySet()){
            amount += entry.getValue();
        }

        numberOfItemsLbl.textProperty().set(amount + " st");
    }

    public void updateTotalPrice(){
        double price = 0;

        price = addAllProducts();

        totalPriceLbl.textProperty().set(roundInString(price) + " kr");
    }

    private double addAllProducts(){
        double a = 0;

        for(Map.Entry<Product, Double> entry: ProductController.productToAmountMap.entrySet()){
            a += entry.getKey().getPrice() * entry.getValue();
        }

        return a;
    }

    private String roundInString(double d){
        return String.format("%.2f", d);
    }

    @FXML
    public void onKundtjanstClicked() {
        spManager.showPane("../fxml/kundinformation.fxml");
        cssManager.changeCSS("kundtjanstPane", "upperPaneFill", "upperPaneFillPressed");
    }

    @FXML
    public void onHandlaClicked() {
        spManager.showPane("../fxml/framsida.fxml");
        cssManager.changeCSS("handlaPane", "upperPaneFill", "upperPaneFillPressed");
    }

    @FXML
    public void onMittKontoClicked() {
        spManager.showPane("../fxml/mittKontoMain.fxml");
        cssManager.changeCSS("mittKontoPane", "upperPaneFill", "upperPaneFillPressed");

    }

    @FXML
    public void onVarukorgClicked () {
        spManager.showPane("../fxml/varukorg.fxml");
        cssManager.changeCSS("varukorgPane", "upperPaneFill", "upperPaneFillPressed");
    }
}

