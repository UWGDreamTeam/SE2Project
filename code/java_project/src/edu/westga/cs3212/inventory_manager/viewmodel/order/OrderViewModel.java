package edu.westga.cs3212.inventory_manager.viewmodel.order;

import java.util.List;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.Main;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.OrderInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.warehouse.Order;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * The OrderPageViewModel class represents the view model for the OrderPage
 * view. It manages the interaction between the OrderPage view and the
 * underlying data model.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public class OrderViewModel {

	private ObjectProperty<Order> selectedOrder;

	/**
	 * Initializes a new instance of OrderPageViewModel. Sets up the connection
	 * to the order management system and initializes properties for UI
	 * interaction.
	 */
	public OrderViewModel() {
		this.selectedOrder = new SimpleObjectProperty<Order>();
	}

	/**
	 * Retrieves a list of all orders that are currently marked as incomplete.
	 *
	 * @return A List of Order objects with a status of
	 *         CompletionStatus.INCOMPLETE.
	 */
	public List<Order> getIncompleteOrders() {
		
		return OrderInventory
				.getOrdersByCompletionStatus(CompletionStatus.INCOMPLETE);
	}

	/**
	 * Gets the property that tracks the currently selected order in the UI.
	 * This property allows for binding to UI elements and listening for changes
	 * in order selection.
	 *
	 * @return An ObjectProperty wrapping the currently selected Order object.
	 */
	public ObjectProperty<Order> getSelectedOrderProperty() {
		return this.selectedOrder;
	}

	/**
	 * Retrieves a list of all orders that have been marked as complete.
	 *
	 * @return A List of Order objects with a status of
	 *         CompletionStatus.COMPLETE.
	 */
	public List<Order> getCompleteOrders() {
		return OrderInventory
				.getOrdersByCompletionStatus(CompletionStatus.COMPLETE);
	}

	/**
	 * Marks the provided order as complete within the order management system.
	 * This method is typically invoked in response to a user action indicating
	 * that an order has been fulfilled.
	 *
	 * @param selectedOrder
	 *            The order to be marked as complete.
	 * @precondition none
	 * @postcondition selectedOrder2's completion status is set to
	 *                CompletionStatus.COMPLETE
	 */
	public void fulfillSelectedOrder(Order selectedOrder) {
		Map<Product, Integer> products = selectedOrder.getItems();
		OrderInventory.updateOrder(selectedOrder.getID(), products,
				CompletionStatus.COMPLETE);
	}
	
	/**
	 * Determines if the user is a manager.
	 * 
	 * @return true if the user is a manager, false otherwise
	 */
	public boolean isManager() {
		return Main.isLoggedInEmployeeManager();
	}

	/**
	 * Clears all orders from the order management system. This operation might
	 * be used for resetting the system or removing all order records.
	 *
	 * @precondition none
	 * @postcondition All orders are removed from the order management system.
	 */
	public void clearOrders() {
		OrderInventory.clearOrders();
	}

}
