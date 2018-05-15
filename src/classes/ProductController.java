package classes;

import javafx.scene.control.TextField;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;


public class ProductController implements Initializable{

    private ProductCategory category;
    private IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();


    private List<Product> allProducts = iMatDataHandler.getProducts();
    private List<Product> currentList = new ArrayList<>();
    private Map<String, ProductItem> productsListItemMap = new HashMap<>();

    @FXML private Pane minaFavoriterCtg, breadCtg, drinksCtg, fruitCtg, meatCtg, dairyCtg, sweetsCtg, dryCtg, nutCtg;
    @FXML FlowPane resultFlowPane;
    @FXML TextField searchBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Product> products = iMatDataHandler.getProducts();
        updateFlowPane(products);
    }

    public void updateFlowPane(List<Product> list) {
        resultFlowPane.getChildren().clear();
        for (Product aProduct : list){
            ProductItem productItem = new ProductItem(aProduct);
            productsListItemMap.put(aProduct.getName(), productItem);
            resultFlowPane.getChildren().add(productsListItemMap.get(aProduct.getName()));
        }
    }

    public void clickedMinaFavoriter() {
        currentList.clear();
        for(int i = 0; i < allProducts.size(); i++){
            if (iMatDataHandler.isFavorite(allProducts.get(i))){
                currentList.add(allProducts.get(i));
            }
        }
    }

    public void clickedBreadCtg() {
        currentList.clear();
        for(int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getCategory() == ProductCategory.BREAD){
                currentList.add(allProducts.get(i));
            }
        }
        updateFlowPane(currentList);
    }

    public void clickedDrinksCtg() {
        currentList.clear();
        for(int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getCategory() == ProductCategory.COLD_DRINKS || allProducts.get(i).getCategory() == ProductCategory.HOT_DRINKS){
                currentList.add(allProducts.get(i));
            }
        }
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
        updateFlowPane(currentList);
    }

    public void clickedDairyCtg() {
        currentList.clear();
        for(int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getCategory() == ProductCategory.DAIRIES){
                currentList.add(allProducts.get(i));
            }
        }
        updateFlowPane(currentList);
    }

    public void clickedSweetsCtg() {
        currentList.clear();
        for(int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getCategory() == ProductCategory.SWEET){
                currentList.add(allProducts.get(i));
            }
        }
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
        updateFlowPane(currentList);
    }

    public void searchBar() {
        //Skriv Skit Här
    }

    public void changeToHandla() throws IOException {
        FXMLLoader.load(getClass().getResource("/fxml/kundinformation.fxml"));
    }

   /* super.setOnMouseClicked(event -> {
        productController.changeToHandla()
    });*/
}

