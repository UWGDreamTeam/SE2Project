package edu.westga.cs3212.inventory_manager.test.homepageviewmodel;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.analytics.ProductInventoryAnalytics;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;
import edu.westga.cs3212.inventory_manager.model.local_impl.OrderManager;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.OrderInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Order;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;
import edu.westga.cs3212.inventory_manager.viewmodel.home.HomeViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class TestGetComponentSummary {
	
	@BeforeEach
	void setUp() {
		ComponentInventory.clearInventory();
		ProductInventory.clearInventory();
		OrderInventory.clearOrders();

		Component componentA = new Component("ComponentA", 1.0);
		String componentIDA = ComponentInventory.addComponent("ComponentA", 1.0, 10);
		componentA.setID(componentIDA);
		Component componentB = new Component("ComponentB", 2.0);
		String componentIDB = ComponentInventory.addComponent("ComponentB", 2.0, 20);
		componentB.setID(componentIDB);
		Component componentC = new Component("ComponentC", 3.0);
		String componentIDC = ComponentInventory.addComponent("ComponentC", 3.0, 30);
		componentC.setID(componentIDC);

		Map<Component, Integer> recipe1 = new HashMap<>();
		recipe1.put(componentA, 10);
		recipe1.put(componentB, 5);

		Map<Component, Integer> recipe2 = new HashMap<>();
		recipe2.put(componentA, 15);
		recipe2.put(componentC, 20);

		Product product1 = new Product("Product1", 100.0, 150.0, recipe1);
		String productID1 = ProductInventory.addProduct("Product1", 100.0, recipe1, 0);
		product1.setID(productID1);
		Product product2 = new Product("Product2", 200.0, 250.0, recipe2);
		String productID2 = ProductInventory.addProduct("Product2", 200.0, recipe2, 0);
		product2.setID(productID2);
		
		Map<Product, Integer> products = new HashMap<>();
		products.put(product1, 10);
		products.put(product2, 20);

		OrderInventory.createOrder(products, CompletionStatus.INCOMPLETE);
		OrderInventory.createOrder(products, CompletionStatus.INCOMPLETE);
	}

	@Test
	void test() {

		StringProperty testComponentSummaryTextArea = new SimpleStringProperty();
		StringProperty testProductSummaryTextArea = new SimpleStringProperty();
		StringProperty testOrderSummaryTextArea = new SimpleStringProperty();

		HomeViewModel homeVM = new HomeViewModel();

		homeVM.getComponentSumarryTextArea().bindBidirectional(testComponentSummaryTextArea);
		homeVM.getOrderSumarryTextArea().bindBidirectional(testOrderSummaryTextArea);
		homeVM.getProductSumarryTextArea().bindBidirectional(testProductSummaryTextArea);

		homeVM.getComponentSummary();
		homeVM.getProductSummary();
		homeVM.getOrderSummary();
	}

}
