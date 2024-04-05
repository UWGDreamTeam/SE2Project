package edu.westga.cs3212.inventory_manager.viewmodel.home;

import java.util.Map;

import edu.westga.cs3212.inventory_manager.Main;
import edu.westga.cs3212.inventory_manager.model.analytics.ComponentInventoryAnalytics;
import edu.westga.cs3212.inventory_manager.model.analytics.OrderAnalytics;
import edu.westga.cs3212.inventory_manager.model.analytics.ProductInventoryAnalytics;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HomePageViewModel {

	private StringProperty componentSummaryTextArea;
	private StringProperty productSummaryTextArea;
	private StringProperty orderSummaryTextArea;

	/**
	 * Initializes a new instance of the HomePageViewModel. Prepares the
	 * StringProperties for binding to the UI text areas that display the
	 * summaries.
	 */
	public HomePageViewModel() {
		this.componentSummaryTextArea = new SimpleStringProperty();
		this.productSummaryTextArea = new SimpleStringProperty();
		this.orderSummaryTextArea = new SimpleStringProperty();
	}

	/**
	 * Gets the StringProperty for the component summary text area.
	 *
	 * @return The StringProperty bound to the component summary text area in
	 *         the UI.
	 */
	public StringProperty getComponentSumarryTextArea() {
		return this.componentSummaryTextArea;
	}

	/**
	 * Gets the StringProperty for the product summary text area.
	 *
	 * @return The StringProperty bound to the product summary text area in the
	 *         UI.
	 */
	public StringProperty getProductSumarryTextArea() {
		return this.productSummaryTextArea;
	}

	/**
	 * Gets the StringProperty for the order summary text area.
	 *
	 * @return The StringProperty bound to the order summary text area in the
	 *         UI.
	 */
	public StringProperty getOrderSumarryTextArea() {
		return this.orderSummaryTextArea;
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
	 * Generates a summary of the most used components and updates the component
	 * summary text area. The summary includes the top 5 most used components
	 * based on their usage in product recipes.
	 */
	public void getComponentSummary() {
		ComponentInventoryAnalytics componentInventoryAnalytics = new ComponentInventoryAnalytics();
		Map<Component, Integer> componentSummary = componentInventoryAnalytics
				.getMostUsedComponents(5);

		StringBuilder componentSummaryStringBuilder = new StringBuilder(
				"The top 5 most used components are: \n");

		for (Map.Entry<Component, Integer> entry : componentSummary
				.entrySet()) {
			componentSummaryStringBuilder
					.append("Component Name: " + entry.getKey().getName())
					.append("    ")
					.append("Number of Recipes: " + entry.getValue())
					.append("\n");
		}

		this.componentSummaryTextArea
				.set(componentSummaryStringBuilder.toString());
	}

	/**
	 * Generates a summary of the most requested products and updates the
	 * product summary text area. The summary includes the top 5 most requested
	 * products based on order frequency.
	 */
	public void getProductSummary() {
		ProductInventoryAnalytics productInventoryAnalytics = new ProductInventoryAnalytics();
		Map<Product, Integer> products = productInventoryAnalytics
				.getMostUsedProducts(5);
		StringBuilder productSummaryStringBuilder = new StringBuilder(
				"The top 5 most requested products are: \n");
		for (Map.Entry<Product, Integer> entry : products.entrySet()) {
			productSummaryStringBuilder
					.append("Product Name: " + entry.getKey().getName())
					.append("    ")
					.append("Number of Products Ordered: " + entry.getValue())
					.append("\n");
		}

		this.productSummaryTextArea.set(productSummaryStringBuilder.toString());
	}

	/**
	 * Generates a summary of order analytics, including total orders, sales,
	 * production costs, profits, completed orders, and orders in progress.
	 * Updates the order summary text area with this information.
	 */
	public void getOrderSummary() {
		OrderAnalytics orderInventoryAnalytics = new OrderAnalytics();
		StringBuilder summary = new StringBuilder();
		summary.append("Order Analytics Summary:\n");
		summary.append("Total Orders: ")
				.append(orderInventoryAnalytics.getOrdersCount()).append("\n");
		summary.append("Total Sales of Completed Orders: $")
				.append(String.format("%.2f",
						orderInventoryAnalytics.getOrdersSalesTotal()))
				.append("\n");
		summary.append("Total Production Cost of Completed Orders: $")
				.append(String.format("%.2f",
						orderInventoryAnalytics.getOrdersProductionCostTotal()))
				.append("\n");
		summary.append("Total Profit of Completed Orders: $")
				.append(String.format("%.2f",
						orderInventoryAnalytics.getOrdersProfitTotal()))
				.append("\n");
		summary.append("Orders Completed: ")
				.append(orderInventoryAnalytics.getOrdersCompletedCount())
				.append("\n");
		summary.append("Orders In Progress: ")
				.append(orderInventoryAnalytics.getOrdersInProgressCount())
				.append("\n");

		this.orderSummaryTextArea.set(summary.toString());
	}

}
