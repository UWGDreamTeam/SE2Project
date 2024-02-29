package edu.westga.cs3212.inventory_manager.viewmodel;

import java.util.ArrayList;

import edu.westga.cs3212.inventory_manager.model.ComponentInventoryAnalytics;
import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.chart.XYChart;

public class HomePageViewModel {
	
	private StringProperty componentSumarryTextArea;
	private StringProperty productSumarryTextArea;
	private StringProperty orderSumarryTextArea;
	private ComponentInventoryAnalytics componentInventoryAnalytics;
	
	
	
	public HomePageViewModel() {
		this.componentSumarryTextArea = new SimpleStringProperty();
		this.productSumarryTextArea = new SimpleStringProperty();
		this.orderSumarryTextArea = new SimpleStringProperty();
	}
	
	
	public StringProperty getComponentSumarryTextArea() {
		return componentSumarryTextArea;
	}

	public StringProperty getProductSumarryTextArea() {
		return productSumarryTextArea;
	}

	public StringProperty getOrderSumarryTextArea() {
		return orderSumarryTextArea;
	}



	public void getComponetSumarry() {
		Compon
	}
	
	
}
