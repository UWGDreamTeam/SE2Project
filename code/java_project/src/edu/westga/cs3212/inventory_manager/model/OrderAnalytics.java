package edu.westga.cs3212.inventory_manager.model;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;

/**
 * Provides analytics related to orders within the inventory management system.
 * This class utilizes the LocalOrderManager to aggregate and compute various
 * metrics such as total sales, production costs, profits, and counts of orders
 * in different completion statuses.
 *
 * @author Group 1
 * @version Spring 2024
 */
public class OrderAnalytics {

	private LocalOrderManager orderManager;

	/**
	 * Initializes a new instance of OrderAnalytics with its own LocalOrderManager.
	 * 
	 * @precondition none
	 * @postcondition a new LocalOrderManager instance is associated with this
	 *                instance
	 */
	public OrderAnalytics() {
		this.orderManager = new LocalOrderManager();
	}

	/**
	 * Gets the total number of orders.
	 * 
	 * @return The total count of orders.
	 * @precondition none
	 * @postcondition none
	 */
	public int getOrdersCount() {
		return this.orderManager.getOrders().size();
	}

	/**
	 * Calculates the total sales value from completed orders.
	 * 
	 * @return The sum of sale prices from all completed orders.
	 * @precondition none
	 * @postcondition none
	 */
	public double getOrdersSalesTotal() {
		double total = 0;
		for (Order order : this.orderManager.getOrders()) {
			if (order.getCompletionStatus() == CompletionStatus.COMPLETE) {
				total += order.getSalePrice();
			}
		}
		return total;
	}

	/**
	 * Calculates the total production cost from completed orders.
	 * 
	 * @return The sum of production costs from all completed orders.
	 * @precondition none
	 * @postcondition none
	 */
	public double getOrdersProductionCostTotal() {
		double total = 0;
		for (Order order : this.orderManager.getOrders()) {
			if (order.getCompletionStatus() == CompletionStatus.COMPLETE) {
				total += order.getProductionCost();
			}
		}
		return total;
	}

	/**
	 * Calculates the total profit from completed orders. The profit is determined
	 * by subtracting the total production costs from the total sales.
	 * 
	 * @return The total profit from all completed orders.
	 * @precondition none
	 * @postcondition none
	 */
	public double getOrdersProfitTotal() {
		return this.getOrdersSalesTotal() - this.getOrdersProductionCostTotal();
	}

	/**
	 * Gets the count of orders that are marked as completed.
	 * 
	 * @return The number of completed orders.
	 * @precondition none
	 * @postcondition none
	 */
	public int getOrdersCompletedCount() {
		return this.orderManager.getOrdersByCompletionStatus(CompletionStatus.COMPLETE).size();
	}

	/**
	 * Gets the count of orders that are in progress (not completed).
	 * 
	 * @return The number of in-progress orders.
	 * @precondition none
	 * @postcondition none
	 */
	public int getOrdersInProgressCount() {
		return this.orderManager.getOrdersByCompletionStatus(CompletionStatus.INCOMPLETE).size();
	}

	/**
	 * Clears all orders from the system.
	 * 
	 * @precondition none
	 * @postcondition all orders are removed from the system
	 */
	public void clearOrders() {
		this.orderManager.clearOrders();
	}
}
