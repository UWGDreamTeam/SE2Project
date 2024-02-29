package edu.westga.cs3212.inventory_manager.model;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;

public class OrderAnalytics {

	private LocalOrderManager orderManager;
	
	public OrderAnalytics() {
		this.orderManager = new LocalOrderManager();
	}
	
	public int getOrdersCount() {
		return this.orderManager.getOrders().size();
	}
	
	public double getOrdersSalesTotal() {
		double total = 0;
		for (Order order : this.orderManager.getOrders()) {
			if (order.getCompletionStatus() == CompletionStatus.COMPLETE) {
				total += order.getSalePrice();
			}
		}
		return total;
	}
	
	public double getOrdersProductionCostTotal() {
		double total = 0;
		for (Order order : this.orderManager.getOrders()) {
			if  (order.getCompletionStatus() == CompletionStatus.COMPLETE) {
				total += order.getProductionCost();
			}
		}
		return total;
	}
	
	public double getOrdersProfitTotal() {
		return this.getOrdersSalesTotal() - this.getOrdersProductionCostTotal();
	}
	
	public int getOrdersCompletedCount() {
		return this.orderManager.getOrdersByCompletionStatus(CompletionStatus.COMPLETE).size();
	}
	
	public int getOrdersInProgressCount() {
		return this.orderManager.getOrdersByCompletionStatus(CompletionStatus.INCOMPLETE).size();
	}
}
