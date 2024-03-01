package edu.westga.cs3212.inventory_manager.test.homepageviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.ProductInventoryAnalytics;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;
import edu.westga.cs3212.inventory_manager.viewmodel.HomePageViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class TestGetComponentSummary {
	private LocalOrderManager orderManager;
    private ProductInventoryAnalytics analytics;
    
    @BeforeEach
    void setUp() {
        this.orderManager = new LocalOrderManager();
        this.analytics = new ProductInventoryAnalytics();

	    Component componentA = new Component("ComponentA", 1.0);
	    Component componentB = new Component("ComponentB", 2.0);
	    Component componentC = new Component("ComponentC", 3.0);
	    
	    Map<Component, Integer> recipe1 = new HashMap<>();
	    recipe1.put(componentA, 10);
	    recipe1.put(componentB, 5);

	    Map<Component, Integer> recipe2 = new HashMap<>();
	    recipe2.put(componentA, 15);
	    recipe2.put(componentC, 20);
	    
	    Product product1 = new Product("Product1", 100.0, 150.0, recipe1);
	    Product product2 = new Product("Product2", 200.0, 250.0, recipe2);

        Order order1 = new Order();
        order1.addItem(product1, 5);
        Order order2 = new Order();
        order2.addItem(product2, 3);
        
        this.orderManager.addOrder(order1);
        this.orderManager.addOrder(order2);
    }

	@Test
	void test() {

		StringProperty testComponentSummaryTextArea = new SimpleStringProperty();
		StringProperty testProductSummaryTextArea = new SimpleStringProperty();
		StringProperty testOrderSummaryTextArea = new SimpleStringProperty();

		HomePageViewModel homeVM = new HomePageViewModel();

		homeVM.getComponentSumarryTextArea().bindBidirectional(testComponentSummaryTextArea);
		homeVM.getOrderSumarryTextArea().bindBidirectional(testOrderSummaryTextArea);
		homeVM.getProductSumarryTextArea().bindBidirectional(testProductSummaryTextArea);
		
		homeVM.getComponentSummary();
		homeVM.getProductSummary();
		homeVM.getOrderSummary();

	}

}
