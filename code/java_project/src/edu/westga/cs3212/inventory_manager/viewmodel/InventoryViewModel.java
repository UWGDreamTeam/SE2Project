package edu.westga.cs3212.inventory_manager.viewmodel;

import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.local_impl.Component;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

/**
 * The Class InventoryViewModel.
 * 
 * @author Group 5
 * @version Spring 2024
 */
public class InventoryViewModel {
	
	private ObjectProperty<Item> selectedComponent;
	private ObservableList<Item> listOfComponents;

	private LocalComponentInventory componentsInventory;
	private LocalProductInventory productInventory;
	
	
	/**
	 * Instantiates a new inventory view model.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public InventoryViewModel() {
		this.selectedComponent = new SimpleObjectProperty<Item>();
		this.listOfComponents = new SimpleListProperty<Item>();
		
		this.componentsInventory = new LocalComponentInventory();
		this.productInventory = new LocalProductInventory();
	}
	
	public ObservableList<Item> getObservableComponentList(){
		return FXCollections.observableArrayList(this.componentsInventory.getListOfItems());
	}
 	
	
	/**
	 * Adds the component.
	 */
	public void addComponent() {
		//TODO
	}
	
	public void removeComponent() {
		this.componentsInventory.removeItem(this.selectedComponent.getValue());
	}

	public ObjectProperty<Item> getSelectedComponent() {
		return this.selectedComponent;
	}

	public ObservableList<Item> getListOfComponents() {
		return this.listOfComponents;
	}

	public LocalComponentInventory getComponentsInventory() {
		return this.componentsInventory;
	}

	public LocalProductInventory getProductInventory() {
		return this.productInventory;
	}
	
	
}
