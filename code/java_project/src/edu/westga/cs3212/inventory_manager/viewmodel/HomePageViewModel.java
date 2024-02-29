package edu.westga.cs3212.inventory_manager.viewmodel;

import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.ComponentInventoryAnalytics;
import edu.westga.cs3212.inventory_manager.model.OrderAnalytics;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.ProductInventoryAnalytics;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HomePageViewModel {
	
	private StringProperty componentSummaryTextArea;
	private StringProperty productSummaryTextArea;
	private StringProperty orderSummaryTextArea;
	
	
	
	public HomePageViewModel() {
		this.componentSummaryTextArea = new SimpleStringProperty();
		this.productSummaryTextArea = new SimpleStringProperty();
		this.orderSummaryTextArea = new SimpleStringProperty();
	}
	
	
	public StringProperty getComponentSumarryTextArea() {
		return componentSummaryTextArea;
	}

	public StringProperty getProductSumarryTextArea() {
		return productSummaryTextArea;
	}

	public StringProperty getOrderSumarryTextArea() {
		return orderSummaryTextArea;
	}

	public void getComponentSummary() {
		ComponentInventoryAnalytics componentInventoryAnalytics = new ComponentInventoryAnalytics();
	    Map<Component, Integer> componentSummary = componentInventoryAnalytics.getMostUsedComponents(5);
	    
	    StringBuilder componentSummaryStringBuilder = new StringBuilder("The top 5 most used components are: \n");
	    
	    for (Map.Entry<Component, Integer> entry : componentSummary.entrySet()) {
	        componentSummaryStringBuilder.append("Component Name: " + entry.getKey().getName())
	                                      .append("    ")
	                                      .append("Number of Recipes: " + entry.getValue())
	                                      .append("\n");
	    }
	    
	    this.componentSummaryTextArea.set(componentSummaryStringBuilder.toString());
	}
	
	public void getProductSummary() {
		ProductInventoryAnalytics productInventoryAnalytics = new ProductInventoryAnalytics();
		Map<Product, Integer> products = productInventoryAnalytics.getMostUsedProducts(5);
		StringBuilder productSummaryStringBuilder = new StringBuilder("The top 5 most requested products are: \n");
		for (Map.Entry<Product, Integer> entry : products.entrySet()) {
			productSummaryStringBuilder.append("Product Name: " + entry.getKey().getName()).append("    ").append("Number of Products Ordered: " + entry.getValue())
					.append("\n");
		}

		this.productSummaryTextArea.set(productSummaryStringBuilder.toString());
	}
	
	public void getOrderSummary() {
		OrderAnalytics orderInventoryAnalytics = new OrderAnalytics();
		StringBuilder summary = new StringBuilder();
        summary.append("Order Analytics Summary:\n");
        summary.append("Total Orders: ").append(orderInventoryAnalytics.getOrdersCount()).append("\n");
        summary.append("Total Sales of Completed Orders: $").append(orderInventoryAnalytics.getOrdersSalesTotal()).append("\n");
        summary.append("Total Production Cost of Completed Orders: $").append(orderInventoryAnalytics.getOrdersProductionCostTotal()).append("\n");
        summary.append("Total Profit of Completed Orders: $").append(orderInventoryAnalytics.getOrdersProfitTotal()).append("\n");
        summary.append("Orders Completed: ").append(orderInventoryAnalytics.getOrdersCompletedCount()).append("\n");
        summary.append("Orders In Progress: ").append(orderInventoryAnalytics.getOrdersInProgressCount()).append("\n");
        
        this.orderSummaryTextArea.set(summary.toString());
	}
	
}
