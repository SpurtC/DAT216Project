package classes;

import java.util.List;

public class FavoriteHandler {

    public List<String> favoriteProductList;

    private FavoriteHandler() {}

    private static class FavoriteProductHolder {
        private static FavoriteHandler instance = new FavoriteHandler();
    }

    public static FavoriteHandler getInstance() {
        return FavoriteProductHolder.instance;
    }

}
