package edu.westga.cs3212.inventory_manager.test.orderpageviewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.OrderInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Order;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;
import edu.westga.cs3212.inventory_manager.viewmodel.order.OrderViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

class TestOrderPageViewModel {

	private OrderViewModel viewModel;

	@BeforeEach
	public void setUp() {
		this.viewModel = new OrderViewModel();
		this.viewModel.clearOrders();
	}

	@Test
	public void testGetOpenOrders() {
		List<Order> openOrders = this.viewModel.getIncompleteOrders();
		assertEquals(0, openOrders.size());
	}

	@Test
	public void testGetClosedOrders() {
		List<Order> closedOrders = this.viewModel.getCompleteOrders();
		assertEquals(0, closedOrders.size());
	}

	@Test
	public void testGetSelectedOrderProperty() {
		Order newOrder = new Order();
		ObjectProperty<Order> selectedOrder = new SimpleObjectProperty<Order>(newOrder);
		
		this.viewModel.getSelectedOrderProperty().bindBidirectional(selectedOrder);
		
		assertNotNull(this.viewModel.getSelectedOrderProperty());
	}
	
	@Test
	public void testfulfillSelectedOrder() {
		String componentID = ComponentInventory.addComponent("test", 0, 10);
		Component component = ComponentInventory.getComponent(componentID);
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		String productID = ProductInventory.addProduct("test", 2, recipe, 10);
		Product product = ProductInventory.getProduct(productID);
		Map<Product, Integer> orders = new HashMap<>();
		orders.put(product, 1);
		String OrderID = OrderInventory.createOrder(orders, CompletionStatus.COMPLETE);
		Order newOrder = OrderInventory.getOrder(OrderID);
		ObjectProperty<Order> selectedOrder = new SimpleObjectProperty<Order>(newOrder);
		
		this.viewModel.getSelectedOrderProperty().bindBidirectional(selectedOrder);
		this.viewModel.fulfillSelectedOrder(newOrder);
		
		assertEquals(CompletionStatus.COMPLETE, newOrder.getCompletionStatus());
	}
}
