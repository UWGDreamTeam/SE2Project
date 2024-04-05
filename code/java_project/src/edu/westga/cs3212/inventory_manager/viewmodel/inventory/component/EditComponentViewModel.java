package edu.westga.cs3212.inventory_manager.viewmodel.inventory.component;

import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Item;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EditComponentViewModel {

	private StringProperty name;
	private StringProperty cost;
	private StringProperty quantity;

	private ObjectProperty<Item> selectedComponent;

	/**
	 * Initializes a new instance of the EditComponentViewModel class. Sets up
	 * property bindings and initializes the connection to the component
	 * inventory for data retrieval and updates.
	 */
	public EditComponentViewModel() {

		this.selectedComponent = new SimpleObjectProperty<Item>();
		this.name = new SimpleStringProperty();
		this.cost = new SimpleStringProperty();
		this.quantity = new SimpleStringProperty();

	}

	/**
	 * Sets the currently selected component for editing, updating the
	 * ViewModel's properties to reflect the component's current values.
	 *
	 * @param item
	 *            The component to be edited, represented as an Item object.
	 */
	public void setSelectedComponent(Item item) {
		this.selectedComponent.set(item);

		this.name.set(item.getName());
		this.cost.set(String.valueOf(item.getProductionCost()));
		this.quantity.set(
				String.valueOf(ComponentInventory.getQuantity(item.getID())));
	}

	/**
	 * Applies the changes made to the component's details in the ViewModel back
	 * to the inventory. Updates the component's name, cost, and quantity based
	 * on the values entered by the user.
	 *
	 * @return true if the component was successfully updated in the inventory,
	 *         false otherwise.
	 */
	public boolean update() {
		String newName = this.name.getValue();
		Double newCost = Double.parseDouble(this.cost.getValue());
		int newQuantity = Integer.parseInt(this.quantity.getValue());
		String id = this.selectedComponent.get().getID();
		ComponentInventory.updateComponent(id, newName, newCost, newQuantity);
		return true;
	}

	/**
	 * Gets the name property of the component being edited. This property is
	 * bound to the corresponding input field in the UI.
	 *
	 * @return A StringProperty representing the component's name.
	 */
	public StringProperty getName() {
		return this.name;
	}

	/**
	 * Gets the cost property of the component being edited. This property is
	 * bound to the corresponding input field in the UI.
	 *
	 * @return A StringProperty representing the component's cost.
	 */
	public StringProperty getCost() {
		return this.cost;
	}

	/**
	 * Gets the quantity property of the component being edited. This property
	 * is bound to the corresponding input field in the UI.
	 *
	 * @return A StringProperty representing the component's quantity in the
	 *         inventory.
	 */
	public StringProperty getQuantity() {
		return this.quantity;
	}
}
