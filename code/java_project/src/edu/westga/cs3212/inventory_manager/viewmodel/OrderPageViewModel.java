package edu.westga.cs3212.inventory_manager.viewmodel;

import java.util.List;

import edu.westga.cs3212.inventory_manager.model.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;

/**
 * The OrderPageViewModel class represents the view model for the OrderPage view.
 * It manages the interaction between the OrderPage view and the underlying data model.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public class OrderPageViewModel {

	private LocalOrderManager orderManager;
	
	/**
     * Constructs a new OrderPageViewModel.
     * 
     * @precondition none
     * @postcondition this.orderManager != null
     */
	public OrderPageViewModel() {
		this.orderManager = new LocalOrderManager();
		this.loadOrders();
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
	
	// temporary method to test the UI
	private void loadOrders() {
		Order order1 = new Order();
		Order order2 = new Order();
		Order order3 = new Order();
		this.orderManager.setOrderCompletionStatus(order3, CompletionStatus.COMPLETE);
		
		this.orderManager.addOrder(order1);
		this.orderManager.addOrder(order2);
		this.orderManager.addOrder(order3);
	}
}