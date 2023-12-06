package hr.javafx.production;

import hr.java.production.model.Category;
import hr.java.production.model.Discount;
import hr.java.production.model.Factory;
import hr.java.production.model.Item;
import hr.java.production.utility.FileUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ItemSearchController {

    @FXML
    private TextField itemNameTextField;
    @FXML
    private ComboBox<Category> itemCategoryComboBox;
    @FXML
    private TableView<Item> itemTableView;
    @FXML
    private TableColumn<Item, String> itemNameTableColumn;
    @FXML
    private TableColumn<Item, String> itemCategoryTableColumn;
    @FXML
    private TableColumn<Item, String> itemVolumeTableColumn;
    @FXML
    private TableColumn<Item, String> itemSellingPiceTableColumn;
    @FXML
    private TableColumn<Item, String> itemProductionCostTableColumn;

    public void initialize(){
        itemNameTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
        itemCategoryTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getCategory().getName()));
        itemVolumeTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getVolume().toString()));
        itemProductionCostTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getProductionCost().toString()));
        itemSellingPiceTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getSellingPrice().toString()));

        Category allCategories = new Category(999L, "Any category", "Any");
        List<Category> categories = FileUtils.getCategoryFromFile();
        categories.add(allCategories);
        itemCategoryComboBox.setItems(FXCollections.observableArrayList(categories));
        itemCategoryComboBox.getSelectionModel().selectLast();
    }

    public void itemSearch() {
        List<Category> categories = FileUtils.getCategoryFromFile();
        List<Item> items = FileUtils.getItemsFromFile(categories);

        String itemName = itemNameTextField.getText();
        Category selectedCategory = itemCategoryComboBox.getValue();

        Stream<Item> filteredStream = items.stream();
        filteredStream = filteredStream.filter(i -> i.getName().toLowerCase().contains(itemName.toLowerCase()));
        if (selectedCategory != null && !selectedCategory.getName().equals("Any category")) {
            filteredStream = filteredStream.filter(i -> i.getCategory().equals(selectedCategory));
        }

        itemTableView.setItems(FXCollections.observableArrayList(filteredStream.collect(Collectors.toList())));
    }
}
