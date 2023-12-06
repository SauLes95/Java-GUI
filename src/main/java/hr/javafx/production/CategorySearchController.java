package hr.javafx.production;

import hr.java.production.model.Category;
import hr.java.production.utility.FileUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class CategorySearchController {

    @FXML
    private TextField categoryNameTextField;
    @FXML
    private TableView<Category> categoryTableView;
    @FXML
    private TableColumn<Category, String> categroyNameTableColumn;
    @FXML
    private TableColumn<Category, String> categroyDescriptionTableColumn;

    public void initialize(){
        categroyNameTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
        categroyDescriptionTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getDescription()));
    }
    public void CategorySearch(){

        List<Category> categoryList = FileUtils.getCategoryFromFile();

        //ObservableList<Category> observableCategoryList =  FXCollections.observableArrayList(categoryList);
        //categoryTableView.setItems(observableCategoryList);

        String categoryName = categoryNameTextField.getText();

        List<Category> filteredCategoryList = categoryList.stream()
                .filter(category -> category.getName().toLowerCase().contains(categoryName.toLowerCase()))
                .toList();


        ObservableList<Category> observableCategoryList = FXCollections.observableArrayList(filteredCategoryList);
        categoryTableView.setItems(observableCategoryList);
    }
}