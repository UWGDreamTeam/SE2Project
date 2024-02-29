package edu.westga.cs3212.inventory_manager.viewmodel;

import java.util.List;

import edu.westga.cs3212.inventory_manager.model.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * The OrderPageViewModel class represents the view model for the OrderPage view.
 * It manages the interaction between the OrderPage view and the underlying data model.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public class OrderPageViewModel {

	private LocalOrderManager orderManager;
	private ObjectProperty<Order> selectedOrder;
	
	/**
     * Constructs a new OrderPageViewModel.
     * 
     * @precondition none
     * @postcondition this.orderManager != null
     */
	public OrderPageViewModel() {
		this.orderManager = new LocalOrderManager();
		this.selectedOrder = new SimpleObjectProperty<Order>();
	}
	
	/**
     *  Retrieves a list of open orders from the order manager.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return a list of incomplete orders
     */
	public List<Order> getIncompleteOrders() {
		return this.orderManager.getOrdersByCompletionStatus(CompletionStatus.INCOMPLETE);
	}
	
	public ObjectProperty<Order> getSelectedOrderProperty() {
		return this.selectedOrder;
	}
	
	/**
     * Retrieves a list of completed orders from the order manager.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return a list of completed orders
     */
	public List<Order> getCompleteOrders() {
		return this.orderManager.getOrdersByCompletionStatus(CompletionStatus.COMPLETE);
	}

	public void fulfillSelectedOrder(Order selectedOrder2) {
		this.orderManager.setOrderCompletionStatus(selectedOrder2, CompletionStatus.COMPLETE);
	}
	
}
