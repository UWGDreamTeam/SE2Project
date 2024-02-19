package edu.westga.cs3212.inventory_manager.viewmodel;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.Component;
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
	
	/** The name. */
	private StringProperty name;
    
    /** The cost. */
    private StringProperty cost;
    
    /** The quantity. */
    private StringProperty quantity;
    
    /** The component inventory. */
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
     * Adds the.
     *
     * @return true, if successful
     */
    public boolean add() {
    	String name = this.name.getValue();
    	Double cost = Double.parseDouble(this.cost.get());
    	int quantity = Integer.parseInt(this.quantity.get());
    	
    	Item newComponent = new Component("ID001", name);
    	
    	newComponent.setUnitCost(cost);
    	newComponent.setQuantity(quantity);
    	
    	return this.componentInventory.addNewItem(newComponent);
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
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(StringProperty name) {
		this.name = name;
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
	 * Sets the cost.
	 *
	 * @param cost the new cost
	 */
	public void setCost(StringProperty cost) {
		this.cost = cost;
	}


	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public StringProperty getQuantity() {
		return this.quantity;
	}


	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(StringProperty quantity) {
		this.quantity = quantity;
	}


	/**
	 * Gets the component inventory.
	 *
	 * @return the component inventory
	 */
	public LocalComponentInventory getComponentInventory() {
		return this.componentInventory;
	}


	/**
	 * Sets the component inventory.
	 *
	 * @param componentInventory the new component inventory
	 */
	public void setComponentInventory(LocalComponentInventory componentInventory) {
		this.componentInventory = componentInventory;
	}
    
    

}
