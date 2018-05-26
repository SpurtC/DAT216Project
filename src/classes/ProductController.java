package classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.*;
import java.util.List;


public class ProductController extends Controller implements Initializable{

    private IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();


    private List<Product> allProducts = iMatDataHandler.getProducts();
    private List<Product> currentList = new ArrayList<>();
    private Map<String, ProductItem> productsListItemMap = new HashMap<>();

    private CSSManager cssManager = new CSSManager(stringPaneMapProduct);

    private static Map<String, Pane> stringPaneMapProduct = new HashMap<>();



    /**
     * Om ni vill rensa varukorgen: productToAmountMap.clear()
     * Om ni vill lägga till en ny eller uppdatera: productToAmountMap.put(product, amount)
     */
    public static ObservableMap<Product, Double> productToAmountMap;

    @FXML
    private Pane minaFavoriterCtgPane, breadCtgPane, drinksCtgPane, fruitCtgPane, meatCtgPane, dairyCtgPane, sweetsCtgPane, dryCtgPane, nutCtgPane, ekoCtgPane;

    @FXML
    FlowPane resultFlowPane;

    @FXML
    TextField searchBar;

    @FXML
    Label noFavoritessLbl;

    static{
        productToAmountMap = FXCollections.observableMap(new HashMap<>());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Product> products = iMatDataHandler.getProducts();
        updateFlowPane(products);
    }

    public void search(){
        currentList.clear();
        for(int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getName().toLowerCase().startsWith(searchBar.getText().toLowerCase()) ||
                    allProducts.get(i).getName().toLowerCase().contains(searchBar.getText().toLowerCase())){
                currentList.add(allProducts.get(i));
            }
        }
        updateFlowPane(currentList);
    }

    private void updateFlowPane(List<Product> list) {
        resultFlowPane.getChildren().clear();
        for (Product aProduct : list){
            ProductItem productItem = new ProductItem(aProduct);
            productsListItemMap.put(aProduct.getName(), productItem);
            resultFlowPane.getChildren().add(productsListItemMap.get(aProduct.getName()));
            noFavoritessLbl.setVisible(false);
        }
    }

    public void clickedMinaFavoriter() {
        noFavoritessLbl.setVisible(false);
        currentList.clear();
        for(int i = 0; i < allProducts.size(); i++){
            if (iMatDataHandler.isFavorite(allProducts.get(i))){
                currentList.add(allProducts.get(i));
            }
        }
        cssManager.changeCSS( "minaFavoriterCtgPane", "ctgPaneFill", "ctgPaneFillClicked");
        updateFlowPane(iMatDataHandler.favorites());

        if(iMatDataHandler.favorites().size() == 0) {
            noFavoritessLbl.setVisible(true);
        }
    }

    public void clickedViewAllCtg() {
        currentList.clear();
        for(int i = 0; i < allProducts.size(); i++) {
            currentList.add(allProducts.get(i));
        }
        //cssManager.changeCSS( "allProductsCtgPane", "ctgPaneFill", "ctgPaneFillClicked");
        updateFlowPane(currentList);
    }

    public void clickedBreadCtg() {
        currentList.clear();
        for(int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getCategory() == ProductCategory.BREAD){
                currentList.add(allProducts.get(i));
            }
        }
        cssManager.changeCSS( "breadCtgPane", "ctgPaneFill", "ctgPaneFillClicked");
        updateFlowPane(currentList);
    }

    public void clickedDrinksCtg() {
        currentList.clear();
        for(int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getCategory() == ProductCategory.COLD_DRINKS || allProducts.get(i).getCategory() == ProductCategory.HOT_DRINKS){
                currentList.add(allProducts.get(i));
            }
        }
        cssManager.changeCSS( "drinksCtgPane", "ctgPaneFill", "ctgPaneFillClicked");
        updateFlowPane(currentList);
    }

    public void clickedFruitCtg() {
        currentList.clear();
        for(int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getCategory() == ProductCategory.CABBAGE ||
                    allProducts.get(i).getCategory() == ProductCategory.CITRUS_FRUIT ||
                    allProducts.get(i).getCategory() == ProductCategory.EXOTIC_FRUIT ||
                    allProducts.get(i).getCategory() == ProductCategory.MELONS ||
                    allProducts.get(i).getCategory() == ProductCategory.FRUIT ||
                    allProducts.get(i).getCategory() == ProductCategory.ROOT_VEGETABLE ||
                    allProducts.get(i).getCategory() == ProductCategory.VEGETABLE_FRUIT){
                currentList.add(allProducts.get(i));
            }
        }
        cssManager.changeCSS( "fruitCtgPane", "ctgPaneFill", "ctgPaneFillClicked");
        updateFlowPane(currentList);
    }

    public void clickedNutCtg() {
        currentList.clear();
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getCategory() == ProductCategory.NUTS_AND_SEEDS ||
                    allProducts.get(i).getCategory() == ProductCategory.HERB ||
                    allProducts.get(i).getCategory() == ProductCategory.BERRY ||
                    allProducts.get(i).getCategory() == ProductCategory.POD){
                currentList.add(allProducts.get(i));
            }
        }
        cssManager.changeCSS( "nutCtgPane", "ctgPaneFill", "ctgPaneFillClicked");
        updateFlowPane(currentList);
    }

    public void clickedMeatCtg() {
        currentList.clear();
        for(int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getCategory() == ProductCategory.MEAT ||
                    allProducts.get(i).getCategory() == ProductCategory.FISH){
                currentList.add(allProducts.get(i));
            }
        }
        cssManager.changeCSS( "meatCtgPane", "ctgPaneFill", "ctgPaneFillClicked");
        updateFlowPane(currentList);
    }

    public void clickedDairyCtg() {
        currentList.clear();
        for(int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getCategory() == ProductCategory.DAIRIES){
                currentList.add(allProducts.get(i));
            }
        }
        cssManager.changeCSS( "dairyCtgPane", "ctgPaneFill", "ctgPaneFillClicked");
        updateFlowPane(currentList);
    }

    public void clickedSweetsCtg() {
        currentList.clear();
        for(int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getCategory() == ProductCategory.SWEET){
                currentList.add(allProducts.get(i));
            }
        }
        cssManager.changeCSS( "sweetsCtgPane", "ctgPaneFill", "ctgPaneFillClicked");
        updateFlowPane(currentList);
    }

    public void clickedDryCtg() {
        currentList.clear();
        for(int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getCategory() == ProductCategory.PASTA ||
                    allProducts.get(i).getCategory() == ProductCategory.FLOUR_SUGAR_SALT ||
                    allProducts.get(i).getCategory() == ProductCategory.POTATO_RICE){
                currentList.add(allProducts.get(i));
            }
        }
        cssManager.changeCSS( "dryCtgPane", "ctgPaneFill", "ctgPaneFillClicked");
        updateFlowPane(currentList);
    }

    public void clickedEcoCtg() {
        currentList.clear();
        for(int i = 0; i < allProducts.size(); i++) {
            if(allProducts.get(i).isEcological()) {
                currentList.add(allProducts.get(i));
            }
        }
        cssManager.changeCSS( "ekoCtgPane", "ctgPaneFill", "ctgPaneFillClicked");
        updateFlowPane(currentList);
    }

    public void makeAMapProduct () {
        stringPaneMapProduct.put("minaFavoriterCtgPane", minaFavoriterCtgPane );
        stringPaneMapProduct.put("breadCtgPane", breadCtgPane );
        stringPaneMapProduct.put("drinksCtgPane", drinksCtgPane );
        stringPaneMapProduct.put("fruitCtgPane", fruitCtgPane );
        stringPaneMapProduct.put("meatCtgPane", meatCtgPane );
        stringPaneMapProduct.put("dairyCtgPane", dairyCtgPane );
        stringPaneMapProduct.put("minaFavoriterCtgPane", minaFavoriterCtgPane );
        stringPaneMapProduct.put("sweetsCtgPane", sweetsCtgPane );
        stringPaneMapProduct.put("dryCtgPane", dryCtgPane );
        stringPaneMapProduct.put("nutCtgPane", nutCtgPane );
        stringPaneMapProduct.put("ekoCtgPane", ekoCtgPane );
    }

    @Override
    public void init() {
        makeAMapProduct();
        noFavoritessLbl.setVisible(false);
    }
}

