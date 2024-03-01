package edu.westga.cs3212.inventory_manager.viewmodel;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;

import java.util.UUID;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Item;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class AddComponentViewModel.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public class AddComponentViewModel {
	
	private StringProperty name;
    private StringProperty cost;
    private StringProperty quantity;
    private LocalComponentInventory componentInventory;
    
    /**
     * Instantiates a new adds the component view model.
     */
    public AddComponentViewModel() {
    	this.name = new SimpleStringProperty(); 
    	this.cost = new SimpleStringProperty();
    	this.quantity = new SimpleStringProperty();
    	
    	this.componentInventory = new LocalComponentInventory();
    }
    
    
    /**
     * Adds a new component to the system
     * 
     * @precondition none
     * @postconditno this.componentInventory.getQuantity() == this.componentInventory.getQuantity()@prev + 1
     *
     * @return true, if successfully added to the system, false otherwise
     */
    public boolean add() {
    	String itemName = this.name.getValue();
    	Double itemCost = Double.parseDouble(this.cost.get());
    	int quantityOfItem = Integer.parseInt(this.quantity.get());
    	
    	Item newComponent = new Component(itemName, itemCost);  	
    	return this.componentInventory.addItem(newComponent, quantityOfItem);
    }

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public StringProperty getName() {
		return this.name;
	}

	/**
	 * Gets the cost.
	 *
	 * @return the cost
	 */
	public StringProperty getCost() {
		return this.cost;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public StringProperty getQuantity() {
		return this.quantity;
	}
}
