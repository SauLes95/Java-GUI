package hr.javafx.production;

import hr.java.production.enumeration.City;
import hr.java.production.model.Category;
import hr.java.production.model.Factory;
import hr.java.production.model.Item;
import hr.java.production.utility.FileUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FactorySearchController {

    @FXML
    private TextField factoryNameTextField;
    @FXML
    private ComboBox<City> factoryCityComboBox;
    @FXML
    private TableView<Factory> factoryTableView;
    @FXML
    private TableColumn<Factory, String> factoryNameTableColumn;
    @FXML
    private TableColumn<Factory, String> factoryCityTableColumn;
    @FXML
    private TableColumn<Factory, String> factoryAddressTableColumn;

    public void initialize(){
        factoryNameTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
        factoryAddressTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getAddress().getStreet()));
        factoryCityTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getAddress().getCity().getName()));

        City[] allCities = City.values();
        List<City> citiesList = Arrays.asList(allCities);
        factoryCityComboBox.setItems(FXCollections.observableArrayList(citiesList));
    }

    public void FactorySearch(){
        List<Category> categories = FileUtils.getCategoryFromFile();
        List<Item> items = FileUtils.getItemsFromFile(categories);
        List<Factory> factoryList = FileUtils.getFactoriesFromFile(items);

        String factoryName = factoryNameTextField.getText();
        City selectedCity = factoryCityComboBox.getValue();
        String factoryCity = selectedCity != null ? selectedCity.getName() : "";

        List<Factory> filteredByName = factoryList.stream()
                .filter(category -> category.getName().toLowerCase().contains(factoryName.toLowerCase()))
                .toList();

        List<Factory> filteredByCity = factoryList.stream()
                .filter(category -> category.getName().toLowerCase().contains(factoryCity.toLowerCase()))
                .toList();

        List<Factory> combinedFilteredList = new ArrayList<>(filteredByName);
        combinedFilteredList.retainAll(filteredByCity);

        ObservableList<Factory> observableFactoryList = FXCollections.observableArrayList(combinedFilteredList);
        factoryTableView.setItems(observableFactoryList);
    }
}
