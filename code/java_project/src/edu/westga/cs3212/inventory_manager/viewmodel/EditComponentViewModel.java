package edu.westga.cs3212.inventory_manager.viewmodel;

import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EditComponentViewModel {
	
	private StringProperty name;
    private StringProperty cost;
    private StringProperty quantity;
    
    private ObjectProperty<Item> selectedComponent;
    
    private LocalComponentInventory componentInventory;
    
    public EditComponentViewModel() {
    	
    	this.componentInventory = new LocalComponentInventory();
    	this.selectedComponent = new SimpleObjectProperty<Item>(); 
    	this.name = new SimpleStringProperty();
    	this.cost = new SimpleStringProperty();
    	this.quantity = new SimpleStringProperty();
    	
    }
    
    public void setSelectedComponent(Item item) {
        this.selectedComponent.set(item);
        
        this.name.set(item.getName());
        this.cost.set(String.valueOf(item.getProductionCost()));
        this.quantity.set(String.valueOf(componentInventory.getQuantityOfItem(item)));
    }
    
    public boolean update() {
    	String newName = this.name.getValue();
    	Double newCost = Double.parseDouble(this.cost.getValue());
    	int newQuantity = Integer.parseInt(this.quantity.getValue());
    	Item item = this.selectedComponent.getValue();
    	
    	item.setName(newName);
    	item.setProductionCost(newCost);
    	componentInventory.setQuantityOfItem(item, newQuantity);
    	
    	return this.componentInventory.editItem(item);
    }

	public StringProperty getName() {
		return this.name;
	}

	public StringProperty getCost() {
		return this.cost;
	}

	public StringProperty getQuantity() {
		return this.quantity;
	}
}
