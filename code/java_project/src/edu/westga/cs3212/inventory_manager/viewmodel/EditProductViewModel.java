package edu.westga.cs3212.inventory_manager.viewmodel;

import java.util.ArrayList;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EditProductViewModel {

	
	private Product product;
	private StringProperty name;
	private StringProperty productionCost;
	private StringProperty sellingPrice;
	private StringProperty quantity;
	private ObjectProperty<Component> selectedComponent;
	private LocalProductInventory productInventory;
	private LocalComponentInventory componentInventory;
	
	public EditProductViewModel(LocalProductInventory productInventory, LocalComponentInventory componentInventory) {
		this.componentInventory = componentInventory;
		this.productInventory = productInventory;
		this.name = new SimpleStringProperty();
		this.productionCost = new SimpleStringProperty();
		this.sellingPrice = new SimpleStringProperty();
		this.quantity = new SimpleStringProperty();
		this.selectedComponent = new SimpleObjectProperty<Component>();
		this.productInventory = new LocalProductInventory();
	}
	
	public void setProduct(Item product) {
        this.product = (Product)product;
        this.name.setValue(product.getName());
        this.productionCost.setValue(String.valueOf(product.getProductionCost()));
        this.sellingPrice.setValue(String.valueOf(this.product.getSalePrice()));
		try {
			this.quantity.setValue(String.valueOf(this.productInventory.getQuantityOfItem(this.product)));
        } catch (NullPointerException e) {
            this.quantity.setValue("0");
        }
	}

	public Map<Component, Integer> getRecipe() {
		return this.product.getRecipe();
	}
	
	public StringProperty getName() {
		return this.name;
	}
	
	public StringProperty getProductionCost() {
		return this.productionCost;
	}
	
	public StringProperty getSellingPrice() {
		return this.sellingPrice;
	}
	
	public StringProperty getQuantity() {
		return this.quantity;
	}
	
	public ObjectProperty<Component> getSelectedComponentProperty() {
		return this.selectedComponent;
	}
	
	public void updateProduct(Map<Component, Integer> recipe) {
		this.product.setName(this.name.getValue());
		this.product.setProductionCost(Double.parseDouble(this.productionCost.getValue().trim()));
		this.product.setSalePrice(Double.parseDouble(this.sellingPrice.getValue().trim()));
		this.product.setRecipe(recipe);
		this.productInventory.editItem(product);
		int quantity = Integer.parseInt(this.quantity.getValue().trim());
		this.productInventory.setQuantityOfItem(product, quantity);
	}

	public ObservableList<Component> getObservableComponentList() {
		ArrayList<Component> items = new ArrayList<Component>();
		for (Item item : this.componentInventory.getItems()) {
			items.add((Component) item);
		}
		return FXCollections.observableArrayList(items);
	}
}
