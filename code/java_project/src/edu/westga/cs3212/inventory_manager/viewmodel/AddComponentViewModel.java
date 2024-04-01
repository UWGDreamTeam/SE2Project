package edu.westga.cs3212.inventory_manager.viewmodel;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;
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

	/**
	 * Instantiates a new adds the component view model.
	 */
	public AddComponentViewModel() {
		this.name = new SimpleStringProperty();
		this.cost = new SimpleStringProperty();
		this.quantity = new SimpleStringProperty();
	}

	/**
	 * Adds a new component to the system
	 * 
	 * @precondition none
	 * @postconditno this.componentInventory.getQuantity() ==
	 *               this.componentInventory.getQuantity()@prev + 1
	 *
	 * @return true, if successfully added to the system, false otherwise
	 */
	public boolean add() {
		String componentName = this.name.getValue();
		Double productionCost = Double.parseDouble(this.cost.get());
		int quantity = Integer.parseInt(this.quantity.get());
		ComponentInventory.addComponent(componentName, productionCost, quantity);
		return true;
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
