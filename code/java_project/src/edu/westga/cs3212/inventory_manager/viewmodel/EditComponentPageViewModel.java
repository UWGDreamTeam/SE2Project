package edu.westga.cs3212.inventory_manager.viewmodel;

import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EditComponentPageViewModel {
	
	private StringProperty name;
    private StringProperty cost;
    private StringProperty quantity;
    
    private ObjectProperty<Item> selectedComponent;
    
    private LocalComponentInventory componentInventory;
    
    public EditComponentPageViewModel() {
    	
    	this.componentInventory = new LocalComponentInventory();
    	this.selectedComponent = new SimpleObjectProperty<Item>();
    	
    	this.name = new SimpleStringProperty();
    	this.cost = new SimpleStringProperty();
    	this.quantity = new SimpleStringProperty();
    	
    }
    
    public void setSelectedComponent(Item item) {
        this.selectedComponent.set(item);
        
        this.name.set(item.getName());
        this.cost.set(String.valueOf(item.getUnitCost()));
        this.quantity.set(String.valueOf(item.getQuantity()));
    }
    
    public boolean update() {
    	String newName = this.name.getValue();
    	Double newCost = Double.parseDouble(this.cost.getValue());
    	int newQuantity = Integer.parseInt(this.quantity.getValue());
    	
    	this.selectedComponent.getValue().setName(newName);
    	this.selectedComponent.getValue().setQuantity(newQuantity);
    	this.selectedComponent.getValue().setUnitCost(newCost);
    	
    	return this.componentInventory.editItem(this.selectedComponent.getValue().getId(), this.selectedComponent.getValue());
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
