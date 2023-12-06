package hr.javafx.production;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MenuBarController {
    public void showItemSearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ItemScreen.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.width, HelloApplication.height);
            HelloApplication.getMainStage().setTitle("Item");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showCategorySearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CategoryScreen.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.width, HelloApplication.height);
            HelloApplication.getMainStage().setTitle("Category");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showFactorySearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FactoryScreen.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.width, HelloApplication.height);
            HelloApplication.getMainStage().setTitle("Factory");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public void showStoreSearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("StoreScreen.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.width, HelloApplication.height);
            HelloApplication.getMainStage().setTitle("Store");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
