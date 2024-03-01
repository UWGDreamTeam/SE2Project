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

public class AddProductViewModel {

	
	    private ObjectProperty<Component> selectedComponent;
	    private LocalComponentInventory componentInventory;
	    private StringProperty name;
	    private StringProperty productionCost;
	    private StringProperty sellingPrice;
	    private StringProperty quantity;
	    private LocalProductInventory productInventory;
	    
	    
		public AddProductViewModel(LocalComponentInventory componentInventory, LocalProductInventory productInventory) {
			this.componentInventory = componentInventory;
			this.productInventory = productInventory;
			this.name = new SimpleStringProperty();
			this.productionCost = new SimpleStringProperty();
			this.sellingPrice = new SimpleStringProperty();
			this.quantity = new SimpleStringProperty();
			this.selectedComponent = new SimpleObjectProperty<Component>();
		}
		
		public ObjectProperty<Component> getSelectedComponentProperty() {
			return this.selectedComponent;
		}

		public ObservableList<Component> getObservableComponentList() {
			ArrayList<Component> items = new ArrayList<Component>();
			for (Item item : this.componentInventory.getItems()) {
				items.add((Component)item);
			}
			return FXCollections.observableArrayList(items);
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
	
		public void addProduct(Map<Component, Integer> recipe) {
			String productName = this.name.getValue();
			Double productionCost = Double.parseDouble(this.productionCost.getValue().trim());
			Double sellingPrice = Double.parseDouble(this.sellingPrice.getValue().trim());
			int quantity = Integer.parseInt(this.quantity.getValue().trim());
			Product newProduct = new Product(productName, productionCost, sellingPrice, recipe);
			this.productInventory.addItem(newProduct, quantity);		
		}
	    
	    
}
