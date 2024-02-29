package edu.westga.cs3212.inventory_manager.model;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;

public class OrdersAnalytics {

	private LocalOrderManager orderManager;
	
	public OrdersAnalytics(OrderManager orderManager) {
		this.orderManager = new LocalOrderManager();
	}
	
	public int getOrdersCount() {
		return this.orderManager.getOrders().size();
	}
	
	public double getOrdersTotal() {
		double total = 0;
		for (Order order : this.orderManager.getOrders()) {
			total += order.get
		}
		return total;
	}
}
